package by.cniitu.chargepoint.service.websocket;

import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.model.web.map.WebInformation;
import by.cniitu.chargepoint.util.JsonUtil;
import by.cniitu.chargepoint.util.MapUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServerWebSocket extends WebSocketServer {

    public ServerWebSocket(@Value("${websocket.web.port}") int port) {
        super(new InetSocketAddress(port));
    }

    @PostConstruct
    public void startServerSocket() {
        this.start();
    }

    // all charge points
    // it must be in this service
    // TODO save changes in mongoDB
    Map<Integer, MapPoint> chargePointsMap = new HashMap<>();

    @Override
    public void onStart() {
        System.out.println("ServerWebSocket onStart");
    }

    static public Map<Integer, Set<WebSocket>> userWebSockets = new HashMap<>();

    // TODO make filters. Users can have different filters.
    // {"statusfree":true,"statusempty":false,"typefast":true,"typeslow":false,"Type2":true,"J1772":true}

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        broadcast(JsonUtil.getJsonString(new WebInformation("data",
                MapUtil.MapPointListToMapPointToList(new LinkedList<>(chargePointsMap.values())))));
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Socket onClose " + webSocket);

        // TODO cancel from userWebSockets

    }

    // TODO protect the connection
    // TODO there will be some operations connected with the charging process
    @Override
    public void onMessage(WebSocket webSocket, String s) {

        // TODO there will be some commands to charge points

        System.out.println("s = " + s);

        int userId = -1;

        if(s.equals("giveData")){
            broadcast(JsonUtil.getJsonString(new WebInformation("data",
                    MapUtil.MapPointListToMapPointToList(new LinkedList<>(chargePointsMap.values())))));
        } else if(s.startsWith("{\"id\":\"")){
            userId = Integer.parseInt(s.substring(7, s.length() - 2));
        } else if (s.startsWith("{\"id\":")){
            userId = Integer.parseInt(s.substring(6, s.length() - 1));
        }

        if(userId != -1) {
            Set<WebSocket> webSockets = userWebSockets.get(userId);
            if (webSockets == null) {
                webSockets = new HashSet<>();
                webSockets.add(webSocket);
                userWebSockets.put(userId, webSockets);
            }
            webSockets.add(webSocket);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    public void broadcastUpdate(Set<Integer> updateIds){

        broadcast(JsonUtil.getJsonString(new WebInformation("update", MapUtil.MapPointListToMapPointToList(
                new LinkedList<>(chargePointsMap.entrySet().stream().filter(
                        e -> updateIds.contains(e.getKey())).collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue)).values())))));
    }

    public void broadcastUpdate(Integer updateId){

        Set<Integer> updatesIds = new HashSet<>();
        updatesIds.add(updateId);
        broadcastUpdate(updatesIds);

    }

    public void put(MapPoint mapPoint){
        chargePointsMap.put(mapPoint.getId(), mapPoint);
    }

    // TODO use it
    public void remove(Integer chargePointId){
        chargePointsMap.remove(chargePointId);
    }

}
