package by.cniitu.chargepoint.model.web;

import by.cniitu.chargepoint.model.web.map.MapPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
// to send to web on the connect or on the update
public class WebInformation {

    String type;
    List<MapPoint> features;



}
