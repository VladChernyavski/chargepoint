package by.cniitu.chargepoint.service.websocket;

import by.cniitu.chargepoint.model.web.map.WebInformation;
import by.cniitu.chargepoint.service.ChargePointService;
import by.cniitu.chargepoint.service.mock.SendGeneralInformation;
import by.cniitu.chargepoint.util.JsonUtil;
import by.cniitu.chargepoint.util.MapUtil;
import lombok.Getter;
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

    @Getter
    private static ServerWebSocket serverWebSocket;

    public ServerWebSocket(@Value("${websocket.web.port}") int port) {
        super(new InetSocketAddress(port));
        serverWebSocket = this;
        new SendGeneralInformation(this).start();
    }

    @PostConstruct
    public void startServerSocket() {
        this.start();
    }

    @Override
    public void onStart() {
        System.out.println("ServerWebSocket onStart");
    }

    static public Map<Integer, Set<WebSocket>> userWebSockets = new HashMap<>();

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        broadcast(JsonUtil.getJsonString(new WebInformation("data",
                MapUtil.MapPointListToMapPointToList(new LinkedList<>(ChargePointService.chargePointsMap.values())))));
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

        Integer userId = -1;

        if(s.equals("givedata")){
            broadcast(JsonUtil.getJsonString(new WebInformation("data",
                    MapUtil.MapPointListToMapPointToList(new LinkedList<>(ChargePointService.chargePointsMap.values())))));
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

    public void broadcastUpdate(Set<Integer> updatesIds){

        broadcast(JsonUtil.getJsonString(new WebInformation("update", MapUtil.MapPointListToMapPointToList(
                new LinkedList<>(ChargePointService.chargePointsMap.entrySet().stream().filter(
                        e -> updatesIds.contains(e.getKey())).collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue)).values())))));
    }

}
