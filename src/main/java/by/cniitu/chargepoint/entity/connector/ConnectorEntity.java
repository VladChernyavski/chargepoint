package by.cniitu.chargepoint.entity.connector;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import by.cniitu.chargepoint.entity.Tariff;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "connector")
@JsonIgnoreProperties({ "charge_point" })
@Data
@ToString
public class ConnectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ConnectorStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ConnectorType type;

    @Column(name = "power")
    private Double power;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @ManyToOne
    @JoinColumn(name = "charge_point_id")
    private ChargePointEntity chargePoint;

}
