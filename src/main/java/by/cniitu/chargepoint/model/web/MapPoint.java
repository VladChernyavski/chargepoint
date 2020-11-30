package by.cniitu.chargepoint.model.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Connector> connectors;
    private Geometry geometry;


}
