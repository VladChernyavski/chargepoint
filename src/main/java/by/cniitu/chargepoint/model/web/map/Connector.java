package by.cniitu.chargepoint.model.web.map;

import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Connector {

    private Integer number;
    private ConnectorStatus status;
    private String type;
    private String power;
    private Tariffs tariffs;

}
