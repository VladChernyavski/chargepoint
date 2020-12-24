package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.entity.connector.ConnectorStatusEntity;
import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.repository.ConnectorStatusRepository;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectorStatusService {

    @Autowired
    ConnectorStatusRepository connectorStatusRepository;

    public ConnectorStatusEntity findByName(ConnectorStatus name){
        return connectorStatusRepository.findByName(name);
    }


}
