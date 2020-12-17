package by.cniitu.chargepoint.util;

import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.model.web.map.MapPointTo;

import java.util.LinkedList;
import java.util.List;

public class MapUtil {

    public static List<MapPointTo> MapPointListToMapPointToList(List<MapPoint> mapPoints){
        List<MapPointTo> mapPointToList = new LinkedList<>();
        for(MapPoint mapPoint: mapPoints){
            mapPointToList.add(new MapPointTo(mapPoint));
        }
        return mapPointToList;
    }

}
