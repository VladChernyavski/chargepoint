package by.cniitu.chargepoint.service.mock;

import by.cniitu.chargepoint.model.web.*;
import by.cniitu.chargepoint.model.web.Properties;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class SendGeneralInformation extends Thread {

    ServerWebSocket serverWebSocket;

    static Map<String, String> nextStatus = new HashMap<>();

    static{
        nextStatus.put("alert", "service");
        nextStatus.put("service", "build");
        nextStatus.put("build", "busy");
        nextStatus.put("busy", "work");
        nextStatus.put("work", "connected");
        nextStatus.put("connected", "reserved");
        nextStatus.put("reserved", "alert");
    }


    @Override
    public void run() {

        // even coordinates can be updated

        List<MapPoint> mapPoints = Arrays.asList(new MapPoint(1, new Properties("заправка 1", "27.6416015625, 53.97183955821782", "00:00 - 24:00"),
                        Collections.singletonList(new Connector(1, "work", "standard", "12kW")),
                        new Geometry("Point", Arrays.asList(27.6416015625, 53.97183955821782))),

                new MapPoint(2, new Properties("заправка 2", "27.733612060546875, 53.97305115985005", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.733612060546875, 53.97305115985005))),

                new MapPoint(3, new Properties("заправка 3", "27.540664672851562, 53.893819129552305", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.540664672851562, 53.893819129552305))),

                new MapPoint(4, new Properties("заправка 4", "27.6416015625, 53.97183955821782", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.6416015625, 53.97183955821782))),

                new MapPoint(5, new Properties("заправка 5", "27.713699340820312, 53.95527747164856", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.713699340820312, 53.95527747164856))),

                new MapPoint(6, new Properties("заправка 6", "27.805709838867188, 53.879654718999255", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.805709838867188, 53.879654718999255))),

                new MapPoint(7, new Properties("заправка 7", "27.743911743164062, 53.814841989541314", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.743911743164062, 53.814841989541314))),

                new MapPoint(8, new Properties("заправка 8", "27.5482177734375, 53.80186739998563", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.5482177734375, 53.80186739998563))),

                new MapPoint(9, new Properties("заправка 9", "27.38616943359375, 53.824164995581235", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.38616943359375, 53.824164995581235))),

                new MapPoint(10, new Properties("заправка 10", "27.384109497070312, 53.91930297491356", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.384109497070312, 53.91930297491356))),

                new MapPoint(11, new Properties("заправка 11", "27.522811889648438, 53.960933558166715", "01:00 - 24:00"),
                        Collections.singletonList(new Connector(1, "service", "standard", "12kW")),
                        new Geometry("Point", Arrays.asList(27.522811889648438, 53.960933558166715))),

                new MapPoint(12, new Properties("заправка 12", "27.59765625, 54.010189840172714", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.59765625, 54.010189840172714))),

                new MapPoint(13, new Properties("заправка 13", "27.568130493164062, 54.03721564638805", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.568130493164062, 54.03721564638805))),

                new MapPoint(14, new Properties("заправка 14", "27.587356567382812, 53.86589040962872", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.587356567382812, 53.86589040962872))),

                new MapPoint(15, new Properties("заправка 15", "27.475433349609375, 53.93830460778626", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.475433349609375, 53.93830460778626))),

                new MapPoint(16, new Properties("заправка 16", "27.456207275390625, 53.88410690807865", "00:00 - 24:00"),
                        Collections.singletonList(new Connector(1, "work", "standard", "12kW")),
                        new Geometry("Point", Arrays.asList(27.456207275390625, 53.88410690807865))),

                new MapPoint(17, new Properties("заправка 17", "27.518692016601562, 53.86508060329478", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.518692016601562, 53.86508060329478))),

                new MapPoint(18, new Properties("заправка 18", "27.620315551757812, 53.94396299650727", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.620315551757812, 53.94396299650727))),

                new MapPoint(19, new Properties("заправка 19", "27.642288208007812, 53.91040562094631", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.642288208007812, 53.91040562094631))),

                new MapPoint(20, new Properties("заправка 20", "27.562637329101562, 53.93102840881328", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.562637329101562, 53.93102840881328))),

                new MapPoint(21, new Properties("заправка 21", "27.489852905273438, 53.91687661183859", "06:00 - 24:00"),
                        Collections.singletonList(new Connector(1, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.489852905273438, 53.91687661183859))),

                new MapPoint(22, new Properties("заправка 22", "27.49465942382812, 53.84199453239005", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.49465942382812, 53.84199453239005))),

                new MapPoint(23, new Properties("заправка 23", "27.651901245117188, 53.86386586440115", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.651901245117188, 53.86386586440115))),

                new MapPoint(24, new Properties("заправка 24", "27.65533447265625, 53.888558623056724", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.65533447265625, 53.888558623056724))),

                new MapPoint(25, new Properties("заправка 25", "27.694473266601562, 53.89826981020947", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.694473266601562, 53.89826981020947))),

                new MapPoint(26, new Properties("заправка 26", "27.737045288085938, 53.93102840881328", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.737045288085938, 53.93102840881328))),

                new MapPoint(27, new Properties("заправка 27", "27.739791870117188, 53.882892721712686", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.739791870117188, 53.882892721712686))),

                new MapPoint(28, new Properties("заправка 28", "27.66357421875, 53.8282178295217", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.66357421875, 53.8282178295217))),

                new MapPoint(29, new Properties("заправка 29", "27.588043212890625, 53.835917133888245", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.588043212890625, 53.835917133888245))),

                new MapPoint(30, new Properties("заправка 30", "27.34840393066406, 53.880059483052605", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "super puper speed", "100kW"),
                                new Connector(2, "service", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.34840393066406, 53.880059483052605))));


        Random random = new Random();



        while (true){

                try {

                    List<MapPoint> mapPointsUpdate = new LinkedList<>();

                    int waitTime = 3000;

                    // update something and save it to mapPointsUpdate
                    for (MapPoint mapPoint : mapPoints) {
                        int randomNum = random.nextInt(8); // 0 or 1
                        if (randomNum == 0) {

                            List<Connector> connectors = mapPoint.getConnectors();
                            int connectorNumber = random.nextInt(connectors.size()); // 0 or 1
                            Connector connector = mapPoint.getConnectors().get(connectorNumber);
                            connector.setStatus(nextStatus.get(connector.getStatus()));
                            mapPointsUpdate.add(mapPoint);

                        }

                    }

                    Thread.sleep(waitTime);
                    serverWebSocket.broadcast(JsonUtil.getJsonString(new WebInformation("update", mapPointsUpdate)));


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

}
