package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import by.cniitu.chargepoint.service.enums.UserActionEnum;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableScheduling
public class UserActionService {

    final ChargePointService chargePointService;
    final ConnectorService connectorService;
    final ConnectorStatusService connectorStatusService;
    final TransactionService transactionService;

    // if type of the connector is busy and the charge process is finished -> it was finished by server (user), not by chargePoint
    // if type of the connector is reserved and the reservation process is finished -> it was finished by server (user), not by chargePoint
    final Map<UserActionEnum, ConnectorStatus> actionNameToConnectorStatus = new HashMap<>();
    final Map<UserActionEnum, ConnectorStatus> actionNameToNextConnectorStatusAfterFinish = new HashMap<>();

    public UserActionService(ChargePointService chargePointService, ConnectorService connectorService,
                             ConnectorStatusService connectorStatusService, TransactionService transactionService) {
        this.chargePointService = chargePointService;
        this.connectorService = connectorService;
        this.connectorStatusService = connectorStatusService;
        this.transactionService = transactionService;

        actionNameToConnectorStatus.put(UserActionEnum.CHARGE, ConnectorStatus.BUSY);
        actionNameToConnectorStatus.put(UserActionEnum.RESERVE, ConnectorStatus.RESERVED);

        actionNameToNextConnectorStatusAfterFinish.put(UserActionEnum.CHARGE, ConnectorStatus.CONNECTED);
        actionNameToNextConnectorStatusAfterFinish.put(UserActionEnum.RESERVE, ConnectorStatus.WORK);
    }

    // TODO save changes in mongoDB
    private final Map<Integer, UserActionTo> userActionMap = new HashMap<>();

    public void put(Integer userId, UserActionTo userActionTo){
        userActionMap.put(userId, userActionTo);
    }

    public boolean containsKey(Integer userId){
        return userActionMap.containsKey(userId);
    }

    public void finish(Integer userId){
        userActionMap.get(userId).finish();
    }

    @Scheduled(fixedRate = 1000)
    public void broadcastUserActions(){

        Set<Integer> finishedUserIds = new HashSet<>();

        for(Map.Entry<Integer, UserActionTo> userActionEntry: userActionMap.entrySet()){
            UserActionTo userActionTo = userActionEntry.getValue().nextSecond();
            Integer userId = userActionEntry.getKey();
            Set<WebSocket> webSocketSet = ServerWebSocket.userWebSockets.get(userId);
            if(webSocketSet == null)
                continue;

            Integer chargePointId = userActionTo.getChargePointId();
            ConnectorEntity connector = null;
            try {
                connector = connectorService.getConnector(chargePointId, userActionTo.getConnectorId());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            if(connector == null){
                userActionTo.finish();
                continue;
            }
            ConnectorStatus conStatus = connector.getStatus().getName();
            UserActionEnum userActionEnum = userActionTo.getType();
            boolean rightStatus = conStatus == actionNameToConnectorStatus.get(userActionEnum);
            if (!rightStatus){
                // finish reserve session if the status of the connector is not reserved
                // finish charge session if the status of the connector is not busy
                userActionTo.finish();
            }

            Set<WebSocket> socketsToRemove = new HashSet<>();

            for(WebSocket webSocket : webSocketSet){
                try {
                    webSocket.send(JsonUtil.getJsonString(userActionTo));
                } catch (WebsocketNotConnectedException ex){
                    socketsToRemove.add(webSocket);
                }
            }

            for(WebSocket webSocket : socketsToRemove){
                webSocketSet.remove(webSocket);
            }

            if(userActionTo.shouldBeFinished()){
                finishedUserIds.add(userId);

                // if the charge is finished and the connector status is busy set connector status to connected
                // if the reservation and connector status is reserved is finished set connector status to work
                if(rightStatus){
                    // save changes to database
                    connector.setStatus(connectorStatusService.connectorStatusToConnectorStatusEntity(
                            actionNameToNextConnectorStatusAfterFinish.get(userActionEnum))
                    );
                    connectorService.save(connector);
                    chargePointService.broadcastUpdate(chargePointId);
                }

                // TODO write the finish of transaction to database
                transactionService.stopTransaction(userActionTo, userId);

            }
        }

        for(Integer finishedUserId : finishedUserIds){
            userActionMap.remove(finishedUserId);
        }

    }

}
