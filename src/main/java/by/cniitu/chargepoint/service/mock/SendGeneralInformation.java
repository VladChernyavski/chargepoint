package by.cniitu.chargepoint.service.mock;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.service.ChargePointService;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
@EnableScheduling
public class SendGeneralInformation{

    @Autowired
    ChargePointService chargePointService;

    static Map<ConnectorStatus, ConnectorStatus> nextStatus = new HashMap<>();

    static Random random = new Random();

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

    @Scheduled(fixedRate = 5000)
    public void run() {

        Set<Integer> updatesIds = new HashSet<>();

        // TODO update directly database using chargePointService

        List<ChargePointEntity> chargePointEntities = chargePointService.findAll();

        // update something
        for(ChargePointEntity entity : chargePointEntities) {
            int randomNum = random.nextInt(8); // 0 <= x < 8
            Integer id = entity.getId();
            if (randomNum == 0 && !normalChargePointIds.contains(id)) {



                Map<Integer, Connector> connectors = entry.getValue().getConnectors();
                int connectorNumber = random.nextInt(connectors.size()) + 1; // 1 or 2
                Connector connector = connectors.get(connectorNumber);
                connector.setStatus(nextStatus.get(connector.getStatus()));
                updatesIds.add(id);
            }
        }

        chargePointService.broadcastUpdate(updatesIds);

    }

}
