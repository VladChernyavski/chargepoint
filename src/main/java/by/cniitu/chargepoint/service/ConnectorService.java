package by.cniitu.chargepoint.service;

import org.springframework.stereotype.Service;
import by.cniitu.chargepoint.model.web.map.Connector;

@Service
public class ConnectorService {

    /**
     * /
     * @param chargePointId
     * @param connectorNumber --  1 or 2
     * @return the connector from database
     * @throws Exception -- unknown connector id
     */
    public Connector getConnector(Integer chargePointId, Integer connectorNumber) throws Exception{

        // TODO get real values from database
        return null;
    }


}
