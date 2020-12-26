package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.connector.ConnectorStatusEntity;
import by.cniitu.chargepoint.repository.ConnectorStatusRepository;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import org.springframework.stereotype.Service;

@Service
public class ConnectorStatusService {

    final ConnectorStatusRepository connectorStatusRepository;

    public ConnectorStatusService(ConnectorStatusRepository connectorStatusRepository) {
        this.connectorStatusRepository = connectorStatusRepository;
    }

    public ConnectorStatusEntity connectorStatusToConnectorStatusEntity(ConnectorStatus name){
        return connectorStatusRepository.findByName(name);
    }

}
