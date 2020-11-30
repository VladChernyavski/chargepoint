package by.cniitu.chargepoint.model.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/*
{
        "type": "Point",
        "coordinates": [
        27.58,
        53.89
        ]
        }
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Geometry {

    private String type;
    private List<Double> coordinates;

}
