package by.cniitu.chargepoint.model.web.map;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/*
 {
         "id": 1,
         "properties": {
            "tittle": "String",
            "address": "String",
            "worktime": "String"
         },
         "connectors": [
            {
                "number": 1,
                "status": "work",
                "type": "string",
                "power": "string"
            },
            {
                "number": 2,
                "status": "service",
                "type": "string",
                "power": "string"
            }
         ],
         "geometry": {
            "type": "Point",
            "coordinates": [
                27.58,
                53.89
            ]
         }
}
         */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MapPoint {

    private Integer id;
    private Properties properties;
    private Map<Integer, Connector> connectors;
    private Geometry geometry;

    public MapPoint(ChargePointEntity chargePointEntity){

    }


}
