package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.model.web.map.*;
import by.cniitu.chargepoint.model.web.map.Properties;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChargePointService {

    // all charge points for map
    // TODO use real values after all
    // TODO save changes in mongoDB
    public static Map<Integer, MapPoint> chargePointsMap = new HashMap<>();

    static{



    }

    public Connector getConnector(Integer id, Integer conId) throws Exception{
        MapPoint mapPoint = chargePointsMap.get(id);
        if(mapPoint == null){
           throw new Exception("unknown id");
        }
        Connector connector = mapPoint.getConnectors().get(conId);
        if(connector == null){
            throw new Exception("unknown connector id");
        }
        return connector;
    }

}
