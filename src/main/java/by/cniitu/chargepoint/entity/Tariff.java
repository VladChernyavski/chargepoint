package by.cniitu.chargepoint.entity;

import by.cniitu.chargepoint.model.TariffTo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
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
    @JsonIgnore
    private Integer id;

    @Column(name = "charge")
    private Double charge;

    @Column(name = "reserve")
    private Double reserve;

    // It is used
    @JsonValue
    public TariffTo toJson(){
        return new TariffTo(this);
    }

}
