package by.cniitu.chargepoint.service.mock;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.entity.connector.ConnectorStatusEntity;
import by.cniitu.chargepoint.service.ChargePointService;
import by.cniitu.chargepoint.service.ConnectorService;
import by.cniitu.chargepoint.service.ConnectorStatusService;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableScheduling
public class SendGeneralInformation{

    final ChargePointService chargePointService;
    final ConnectorStatusService connectorStatusService;
    final ConnectorService connectorService;

    public SendGeneralInformation(ChargePointService chargePointService, ConnectorStatusService connectorStatusService, ConnectorService connectorService) {
        this.chargePointService = chargePointService;
        this.connectorStatusService = connectorStatusService;
        this.connectorService = connectorService;
    }

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

                List<ConnectorEntity> connectors = entity.getConnectors();
                int connectorNumber = random.nextInt(connectors.size()); // 0 or 1
                ConnectorEntity connectorEntity = connectors.get(connectorNumber);
                ConnectorStatus nextConnectorStatus = nextStatus.get(connectorEntity.getStatus().getName());
                ConnectorStatusEntity connectorStatusEntity = connectorStatusService.findByName(nextConnectorStatus);
                connectorEntity.setStatus(connectorStatusEntity);
                connectorService.save(connectorEntity);
                updatesIds.add(id);
            }
        }

        chargePointService.broadcastUpdate(updatesIds);

    }

}
