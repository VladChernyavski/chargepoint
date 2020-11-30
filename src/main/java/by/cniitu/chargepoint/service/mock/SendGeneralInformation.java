package by.cniitu.chargepoint.service.mock;

import by.cniitu.chargepoint.model.web.*;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class SendGeneralInformation extends Thread {

    ServerWebSocket serverWebSocket;

    @Override
    public void run() {

        // even coordinates can be updated

        List<MapPoint> mapPoints1 = Arrays.asList(new MapPoint(1, new Properties("заправка 1", "Проспект партизанский 2", "00:00 - 24:00"),
                Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                        new Connector(2, "work", "super speed", "25kW")),
                new Geometry("Point", Arrays.asList(27.581451, 53.885061))),

                new MapPoint(2, new Properties("заправка 2", "Библиотека", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "build", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.646493, 53.931800))),

                new MapPoint(3, new Properties("заправка 3", "Скала", "06:00 - 24:00"),
                        Arrays.asList(new Connector(1, "work", "super puper speed", "100kW"),
                                new Connector(2, "busy", "super puper speed", "100kW")),
                        new Geometry("Point", Arrays.asList(27.470398, 53.908437))));

        WebInformation webInformation1 = new WebInformation("update", mapPoints1);

        List<MapPoint> mapPoints2 = Collections.singletonList(
                new MapPoint(2, new Properties("заправка 2", "Библиотека", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "busy", "standard", "12kW"),
                                new Connector(2, "work", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.646493, 53.931800))));

        WebInformation webInformation2 = new WebInformation("update", mapPoints2);

        List<MapPoint> mapPoints3 = Arrays.asList(new MapPoint(1, new Properties("заправка 1", "Проспект партизанский 2", "00:00 - 24:00"),
                        Arrays.asList(new Connector(1, "build", "standard", "12kW"),
                                new Connector(2, "service", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.581451, 53.885061))),

                new MapPoint(2, new Properties("заправка 2", "Библиотека", "01:00 - 24:00"),
                        Arrays.asList(new Connector(1, "service", "standard", "12kW"),
                                new Connector(2, "busy", "super speed", "25kW")),
                        new Geometry("Point", Arrays.asList(27.646493, 53.931800))));

        WebInformation webInformation3 = new WebInformation("update", mapPoints3);

        List<MapPoint> mapPoints4 = Collections.singletonList(new MapPoint(3, new Properties("заправка 3", "Скала", "06:00 - 24:00"),
                Arrays.asList(new Connector(1, "work", "super puper speed", "100kW"),
                        new Connector(2, "work", "super puper speed", "100kW")),
                new Geometry("Point", Arrays.asList(27.470398, 53.908437))));

        WebInformation webInformation4 = new WebInformation("update", mapPoints4);

        while (true){

                try {

                    Integer waitTime = 3000;

                    Thread.sleep(waitTime);
                    serverWebSocket.broadcast(JsonUtil.getJsonString(webInformation1));
                    Thread.sleep(waitTime);
                    serverWebSocket.broadcast(JsonUtil.getJsonString(webInformation2));
                    Thread.sleep(waitTime);
                    serverWebSocket.broadcast(JsonUtil.getJsonString(webInformation3));
                    Thread.sleep(waitTime);
                    serverWebSocket.broadcast(JsonUtil.getJsonString(webInformation4));


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

}
