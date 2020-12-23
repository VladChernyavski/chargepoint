package by.cniitu.chargepoint.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tariff")
@Data
@ToString
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "charge")
    private Double charge;

    @Column(name = "reserve")
    private Double reserve;

}
