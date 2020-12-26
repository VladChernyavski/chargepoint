package by.cniitu.chargepoint.model.web.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MapPoint {

    private Integer id;
    private Properties properties;
    private Map<Integer, Connector> connectors;
    private Geometry geometry;

    // chargePointEntityToMapPoint in ChargePointService

}
