package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import by.cniitu.chargepoint.service.enums.UserActionEnum;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@EnableScheduling
public class UserActionService {

    @Autowired
    ServerWebSocket serverWebSocket;

    // if type of the connector is busy and the charge process is finished -> it was finished by server (user), not by chargePoint
    // if type of the connector is reserved and the reservation process is finished -> it was finished by server (user), not by chargePoint
    static final Map<UserActionEnum, ConnectorStatus> actionNameToConnectorStatus = new HashMap<>();

    static final Map<UserActionEnum, ConnectorStatus> actionNameToNextConnectorStatusAfterFinish = new HashMap<>();

    static{
        actionNameToConnectorStatus.put(UserActionEnum.CHARGE, ConnectorStatus.BUSY);
        actionNameToConnectorStatus.put(UserActionEnum.RESERVE, ConnectorStatus.RESERVED);

        actionNameToNextConnectorStatusAfterFinish.put(UserActionEnum.CHARGE, ConnectorStatus.CONNECTED);
        actionNameToNextConnectorStatusAfterFinish.put(UserActionEnum.RESERVE, ConnectorStatus.WORK);
    }

    // TODO save changes in mongoDB
    public static Map<Integer, UserActionTo> userActionMap = new HashMap<>();

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
            Connector connector = ChargePointService.chargePointsMap.get(chargePointId).getConnectors().get(userActionTo.getConnectorId());
            ConnectorStatus conStatus = connector.getStatus();
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
                    connector.setStatus(actionNameToNextConnectorStatusAfterFinish.get(userActionEnum));
                    serverWebSocket.broadcastUpdate(chargePointId);
                }

                // TODO write the finish of transaction to database
            }
        }

        for(Integer finishedUserId : finishedUserIds){
            userActionMap.remove(finishedUserId);
        }

    }

}
