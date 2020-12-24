package by.cniitu.chargepoint.entity;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "charge_point")
@Data
@ToString
public class ChargePointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "firmware_version")
    private String firmwareVersion;

    // TODO understand what is it
    @Column(name = "iccid")
    private String iccid;

    // TODO understand what is it
    @Column(name = "imsi")
    private String imsi;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "address")
    private String address;

    // TODO ask if it will be used correctly
    @Column(name = "worktime")
    private String worktime;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chargePoint", cascade = CascadeType.ALL)
    private List<ConnectorEntity> connectors;

}
