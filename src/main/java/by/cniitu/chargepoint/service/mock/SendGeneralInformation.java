package by.cniitu.chargepoint.service.mock;

import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.service.ChargePointService;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class SendGeneralInformation extends Thread {

    ServerWebSocket serverWebSocket;

    static int waitTime = 5000;

    static Map<ConnectorStatus, ConnectorStatus> nextStatus = new HashMap<>();

    static{
        nextStatus.put(ConnectorStatus.get(ConnectorStatus.count() - 1), ConnectorStatus.get(0));
        for(int i = 0; i < ConnectorStatus.count() - 1; i++){
            nextStatus.put(ConnectorStatus.get(i), ConnectorStatus.get(i + 1));
        }
    }

    // ids of charge points the state of which can be changed only by user
    static Set<Integer> normalChargePointIds = new HashSet<>();

    static{

        // Andrey
        normalChargePointIds.add(31);
        normalChargePointIds.add(32);

        // Sandro
        normalChargePointIds.add(11);
        normalChargePointIds.add(12);
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
