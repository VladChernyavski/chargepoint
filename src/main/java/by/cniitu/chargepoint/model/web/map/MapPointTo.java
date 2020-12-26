package by.cniitu.chargepoint.model.web.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MapPointTo {

    private Integer id;
    private Properties properties;
    private List<Connector> connectors;
    private Geometry geometry;

    public MapPointTo(MapPoint mapPoint){
        this.id = mapPoint.getId();
        this.properties = new Properties(mapPoint.getProperties());
        this.connectors = new LinkedList<>(mapPoint.getConnectors().values());
        this.geometry = new Geometry(mapPoint.getGeometry());
    }

}
