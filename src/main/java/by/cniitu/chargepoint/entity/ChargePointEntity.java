package by.cniitu.chargepoint.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

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
    @Column(name = "work_time")
    private String workTime;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    /* something went wrong
     @OneToMany(fetch = FetchType.EAGER, mappedBy = "chargePoint", cascade = CascadeType.ALL)
     private List<ConnectorEntity> connectors;
    */

}
