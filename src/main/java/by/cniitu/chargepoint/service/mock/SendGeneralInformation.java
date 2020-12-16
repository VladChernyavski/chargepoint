package by.cniitu.chargepoint.service.mock;

import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.model.web.WebInformation;
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

    // ids of charge points the state of which can be changed only by user
    static Set<Integer> normalChargePointIds = new HashSet<>();

    static{
        normalChargePointIds.add(1);
        normalChargePointIds.add(2);
    }

    @Override
    public void run() {

        // even coordinates can be updated

        Random random = new Random();



        while (true){

                try {

                    List<MapPoint> mapPointsUpdate = new LinkedList<>();

                    int waitTime = 30000;

                    // update something and save it to mapPointsUpdate
                    for (MapPoint mapPoint : new LinkedList<>(ServerWebSocket.chargePointsMap.values())) {
                        int randomNum = random.nextInt(8); // 0 or 1
                        if (randomNum == 0 && !normalChargePointIds.contains(mapPoint.getId())) {

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
