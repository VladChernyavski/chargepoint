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
import java.util.Collections;
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

    // send to web all the data about charge points
    static public List<MapPoint> mapPoints = Arrays.asList(new MapPoint(1, new Properties("заправка 1", "27.6416015625, 53.97183955821782", "00:00 - 24:00"),
                    Collections.singletonList(new Connector(1, "work", "AC3/Type2", "12kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.6416015625, 53.97183955821782))),

            new MapPoint(2, new Properties("заправка 2", "27.733612060546875, 53.97305115985005", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC3/Type2", "12kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC3/Type2", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.733612060546875, 53.97305115985005))),

            new MapPoint(3, new Properties("заправка 3", "27.540664672851562, 53.893819129552305", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.540664672851562, 53.893819129552305))),

            new MapPoint(4, new Properties("заправка 4", "27.6416015625, 53.97183955821782", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.6416015625, 53.97183955821782))),

            new MapPoint(5, new Properties("заправка 5", "27.713699340820312, 53.95527747164856", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC1/J1772", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.713699340820312, 53.95527747164856))),

            new MapPoint(6, new Properties("заправка 6", "27.805709838867188, 53.879654718999255", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.805709838867188, 53.879654718999255))),

            new MapPoint(7, new Properties("заправка 7", "27.743911743164062, 53.814841989541314", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC3/Type2", "12kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.743911743164062, 53.814841989541314))),

            new MapPoint(8, new Properties("заправка 8", "27.5482177734375, 53.80186739998563", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC1/J1772", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.5482177734375, 53.80186739998563))),

            new MapPoint(9, new Properties("заправка 9", "27.38616943359375, 53.824164995581235", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.38616943359375, 53.824164995581235))),

            new MapPoint(10, new Properties("заправка 10", "27.384109497070312, 53.91930297491356", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.384109497070312, 53.91930297491356))),

            new MapPoint(11, new Properties("заправка 11", "27.522811889648438, 53.960933558166715", "01:00 - 24:00"),
                    Collections.singletonList(new Connector(1, "service", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.522811889648438, 53.960933558166715))),

            new MapPoint(12, new Properties("заправка 12", "27.59765625, 54.010189840172714", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.59765625, 54.010189840172714))),

            new MapPoint(13, new Properties("заправка 13", "27.568130493164062, 54.03721564638805", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.568130493164062, 54.03721564638805))),

            new MapPoint(14, new Properties("заправка 14", "27.587356567382812, 53.86589040962872", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC3/Type2", "25kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.587356567382812, 53.86589040962872))),

            new MapPoint(15, new Properties("заправка 15", "27.475433349609375, 53.93830460778626", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.475433349609375, 53.93830460778626))),

            new MapPoint(16, new Properties("заправка 16", "27.456207275390625, 53.88410690807865", "00:00 - 24:00"),
                    Collections.singletonList(new Connector(1, "work", "AC1/J1772", "12kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.456207275390625, 53.88410690807865))),

            new MapPoint(17, new Properties("заправка 17", "27.518692016601562, 53.86508060329478", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC3/Type2", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC3/Type2", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.518692016601562, 53.86508060329478))),

            new MapPoint(18, new Properties("заправка 18", "27.620315551757812, 53.94396299650727", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.620315551757812, 53.94396299650727))),

            new MapPoint(19, new Properties("заправка 19", "27.642288208007812, 53.91040562094631", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.642288208007812, 53.91040562094631))),

            new MapPoint(20, new Properties("заправка 20", "27.562637329101562, 53.93102840881328", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC3/Type2", "25kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.562637329101562, 53.93102840881328))),

            new MapPoint(21, new Properties("заправка 21", "27.489852905273438, 53.91687661183859", "06:00 - 24:00"),
                    Collections.singletonList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.489852905273438, 53.91687661183859))),

            new MapPoint(22, new Properties("заправка 22", "27.49465942382812, 53.84199453239005", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.49465942382812, 53.84199453239005))),

            new MapPoint(23, new Properties("заправка 23", "27.651901245117188, 53.86386586440115", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC3/Type2", "25kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.651901245117188, 53.86386586440115))),

            new MapPoint(24, new Properties("заправка 24", "27.65533447265625, 53.888558623056724", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.65533447265625, 53.888558623056724))),

            new MapPoint(25, new Properties("заправка 25", "27.694473266601562, 53.89826981020947", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.694473266601562, 53.89826981020947))),

            new MapPoint(26, new Properties("заправка 26", "27.737045288085938, 53.93102840881328", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC1/J1772", "25kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.737045288085938, 53.93102840881328))),

            new MapPoint(27, new Properties("заправка 27", "27.739791870117188, 53.882892721712686", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.739791870117188, 53.882892721712686))),

            new MapPoint(28, new Properties("заправка 28", "27.66357421875, 53.8282178295217", "00:00 - 24:00"),
                    Arrays.asList(new Connector(1, "work", "AC1/J1772", "12kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "work", "AC1/J1772", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.66357421875, 53.8282178295217))),

            new MapPoint(29, new Properties("заправка 29", "27.588043212890625, 53.835917133888245", "01:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "12kW", new Tariffs(6.66, 14.48)),
                            new Connector(2, "service", "AC3/Type2", "25kW", new Tariffs(5.99, 0.56))),
                    new Geometry("Point", Arrays.asList(27.588043212890625, 53.835917133888245))),

            new MapPoint(30, new Properties("заправка 30", "27.34840393066406, 53.880059483052605", "06:00 - 24:00"),
                    Arrays.asList(new Connector(1, "service", "AC1/J1772", "100kW", new Tariffs(5.99, 0.56)),
                            new Connector(2, "service", "AC1/J1772", "100kW", new Tariffs(6.66, 14.48))),
                    new Geometry("Point", Arrays.asList(27.34840393066406, 53.880059483052605))));

    WebInformation webInformation = new WebInformation("data", mapPoints);

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

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

        System.out.println("s = " + s);

        if(s.equals("givedata")){
            broadcast(JsonUtil.getJsonString(webInformation));
        }

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

}
