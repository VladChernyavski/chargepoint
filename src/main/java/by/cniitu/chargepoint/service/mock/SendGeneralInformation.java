package by.cniitu.chargepoint.service.mock;

import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.service.ChargePointService;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class SendGeneralInformation extends Thread {

    ServerWebSocket serverWebSocket;

    static int waitTime = 5000;

    static Map<String, String> nextStatus = new HashMap<>();

    // TODO use ChargePointService.conStatuses
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
        normalChargePointIds.add(31);
        normalChargePointIds.add(32);
    }

    @Override
    public void run() {

        // even coordinates can be updated

        Random random = new Random();



        while (true){

                try {

                    Set<Integer> updatesIds = new HashSet<>();

                    // update something
                    for(Map.Entry<Integer, MapPoint> entry : ChargePointService.chargePointsMap.entrySet()){
                        int randomNum = random.nextInt(8); // 0 <= x < 8
                        Integer id = entry.getKey();
                        if (randomNum == 0 && !normalChargePointIds.contains(id)) {
                            Map<Integer, Connector> connectors = entry.getValue().getConnectors();
                            int connectorNumber = random.nextInt(connectors.size()) + 1; // 1 or 2
                            Connector connector = connectors.get(connectorNumber);
                            connector.setStatus(nextStatus.get(connector.getStatus()));
                            updatesIds.add(id);
                        }
                    }

                    Thread.sleep(waitTime);
                    serverWebSocket.broadcastUpdate(updatesIds);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

}
