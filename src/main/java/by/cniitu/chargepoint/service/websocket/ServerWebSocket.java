package by.cniitu.chargepoint.service.websocket;

import by.cniitu.chargepoint.model.web.*;
import by.cniitu.chargepoint.service.mock.SendGeneralInformation;
import by.cniitu.chargepoint.util.JsonUtil;
import lombok.Getter;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

        // send to web all the data about charge points
        List<MapPoint> mapPoints = Arrays.asList(new MapPoint(1, new Properties("заправка 1", "Проспект партизанский 2", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.581451, 53.885061))),

                new MapPoint(2, new Properties("заправка 2", "Библиотека", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.646493, 53.931800))),

                new MapPoint(3, new Properties("заправка 3", "Скала", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.470398, 53.908437))));

        WebInformation webInformation = new WebInformation("data", mapPoints);

        broadcast(JsonUtil.getJsonString(webInformation));
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Socket onClose " + webSocket);
    }

    // TODO there will be some operations connected with the charging process
    @Override
    public void onMessage(WebSocket webSocket, String s) {

        // TODO there will be some commands to charge points

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

}
