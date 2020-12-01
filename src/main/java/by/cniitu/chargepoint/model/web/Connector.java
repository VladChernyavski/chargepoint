package by.cniitu.chargepoint.model.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
{
                "number": 1,
                "status": "work",
                "type": "string",
                "power": "string"
            }
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Connector {

    private Integer number;
    private String status;
    private String type;
    private String power;

}
