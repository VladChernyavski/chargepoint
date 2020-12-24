package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.repository.ConnectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.cniitu.chargepoint.model.web.map.Connector;

@Service
public class ConnectorService {

    @Autowired
    ConnectorRepository connectorRepository;

    /**
     * /
     * @param chargePointId
     * @param connectorNumber --  1 or 2
     * @return the connector from database
     * @throws Exception -- unknown connector id
     */
    public Connector getConnector(Integer chargePointId, Integer connectorNumber) {

        // TODO get real values from database
        return null;
    }

    public void save(ConnectorEntity connectorEntity){
        connectorRepository.save(connectorEntity);
    }


}
