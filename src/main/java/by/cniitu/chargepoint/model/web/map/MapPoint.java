package by.cniitu.chargepoint.model.web.map;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MapPoint {

    private Integer id;
    private Properties properties;
    private Map<Integer, Connector> connectors;
    private Geometry geometry;

    public MapPoint(ChargePointEntity chargePointEntity){
        id = chargePointEntity.getId();
        properties = new Properties(chargePointEntity.getTittle(),
                chargePointEntity.getAddress(), chargePointEntity.getWorktime());
        connectors = new HashMap<>();
        for(ConnectorEntity connectorEntity: chargePointEntity.getConnectors()){
            connectors.put(connectorEntity.getNumber(), new Connector(connectorEntity));
        }
        geometry = new Geometry("Point", Arrays.asList(chargePointEntity.getLatitude(),
                chargePointEntity.getLongitude()));
    }


}
