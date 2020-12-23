package by.cniitu.chargepoint.entity.connector;

import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "connector_status")
@Data
@ToString
public class ConnectorStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private ConnectorStatus name;

}
