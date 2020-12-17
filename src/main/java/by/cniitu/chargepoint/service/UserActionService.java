package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
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
            for(WebSocket webSocket : webSocketSet){
                try {
                    webSocket.send(JsonUtil.getJsonString(userActionTo));
                } catch (WebsocketNotConnectedException ex){
                    webSocketSet.remove(webSocket);
                }
            }

            if(userActionTo.getType().equals(UserActionTo.userActionTypes.get(0))){

            }

            // TODO if the charge is finished and the connector status is busy set connector status to connected
            // TODO if the reservation and connector status is reserved is finished set connector status to work

            // TODO finish reserve session if the status of the connector is not reserved
            // TODO finish charge session if the status of the connector is not busy

            if(userActionTo.shouldBeFinished()){
                finishedUserIds.add(userId);
                // TODO write the finish of transaction to database
            }
        }

        for(Integer finishedUserId : finishedUserIds){
            userActionMap.remove(finishedUserId);
        }

    }

}
