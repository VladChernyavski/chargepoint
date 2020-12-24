package by.cniitu.chargepoint.model.web.map;

import by.cniitu.chargepoint.entity.Tariff;
import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
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

    // TODO create an enum from the database table if it is possible
    // TODO think about it
    // TODO ConnectorType
    private String type;
    private String power;

    // charge and reserve
    private Tariff tariff;

    public Connector(ConnectorEntity connectorEntity){
        this.number = connectorEntity.getNumber();
        this.status = connectorEntity.getStatus().getName();
        this.type = connectorEntity.getType().getName();
        this.power = connectorEntity.getPower().toString();
        this.tariff = connectorEntity.getTariff();
    }

}
