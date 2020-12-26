package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.repository.ConnectorRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConnectorService {

    final ConnectorRepository connectorRepository;

    public ConnectorService(ConnectorRepository connectorRepository) {
        this.connectorRepository = connectorRepository;
    }

    /**
     * /
     * @param chargePointId -- chargePointId
     * @param connectorNumber --  1 or 2
     * @return the connector from database
     * @throws NotFoundException -- unknown connector id
     */
    public ConnectorEntity getConnector(Integer chargePointId, Integer connectorNumber) throws NotFoundException {
        List<ConnectorEntity> connectorEntities = getConnectors(chargePointId);
        for(ConnectorEntity connectorEntity: connectorEntities){
            if(connectorEntity.getNumber().equals(connectorNumber)){
                return connectorEntity;
            }
        }
        throw new NotFoundException("there is not such connector");
    }

    public List<ConnectorEntity> getConnectors(Integer chargePointId) {
        return connectorRepository.getConnectors(chargePointId);
    }

    public void save(ConnectorEntity connectorEntity){
        connectorRepository.save(connectorEntity);
    }


}
