package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.model.web.map.Geometry;
import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.model.web.map.Properties;
import by.cniitu.chargepoint.repository.ChargePointRepository;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ChargePointService {

    final ServerWebSocket serverWebSocket;
    final ChargePointRepository chargePointRepository;
    final ConnectorService connectorService;

    public ChargePointService(ServerWebSocket serverWebSocket,
                              ChargePointRepository chargePointRepository,
                              ConnectorService connectorService) {
        this.serverWebSocket = serverWebSocket;
        this.chargePointRepository = chargePointRepository;
        this.connectorService = connectorService;
    }

    @PostConstruct
    public void postConstruct(){

        // get chargePoints from database and put to serverWebSocket.map
        List<ChargePointEntity> chargePointEntities = chargePointRepository.findAll();
        for(ChargePointEntity chargePointEntity: chargePointEntities){
            serverWebSocket.put(chargePointEntityToMapPoint(chargePointEntity));
        }

    }

    public void broadcastUpdate(Set<Integer> updateChargePointIds){

        // get all updated ids and set in to the map by using some methods of serverWebSocket
        for(Integer updateChargePointId : updateChargePointIds){
            ChargePointEntity chargePointEntity = chargePointRepository.findOneById(updateChargePointId);
            serverWebSocket.put(chargePointEntityToMapPoint(chargePointEntity));
        }
        serverWebSocket.broadcastUpdate(updateChargePointIds);
    }

    public void broadcastUpdate(Integer updateChargePointId){

        // get updated id and set into the map by using some methods of serverWebSocket
        ChargePointEntity chargePointEntity = chargePointRepository.findOneById(updateChargePointId);
        serverWebSocket.put(chargePointEntityToMapPoint(chargePointEntity));
        serverWebSocket.broadcastUpdate(updateChargePointId);
    }

    public List<ChargePointEntity> findAll(){
        return chargePointRepository.findAll();
    }

    public MapPoint chargePointEntityToMapPoint(ChargePointEntity chargePointEntity){
        Integer id = chargePointEntity.getId();
        Properties properties = new Properties(chargePointEntity.getTittle(),
                chargePointEntity.getAddress(), chargePointEntity.getWorkTime());
        Map<Integer, Connector> connectors = new HashMap<>();
        for(ConnectorEntity connectorEntity: connectorService.getConnectors(chargePointEntity.getId())){
            connectors.put(connectorEntity.getNumber(), new Connector(connectorEntity));
        }
        Geometry geometry = new Geometry("Point", Arrays.asList(chargePointEntity.getLatitude(),
                chargePointEntity.getLongitude()));
        return new MapPoint(id, properties, connectors, geometry);

    }

}
