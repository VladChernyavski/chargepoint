package by.cniitu.chargepoint.entity.connector;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.persistence.*;

// TODO make a table with charge points and use ConnectorType There.
// TODO careful with making an enum
@Entity
@Table(name = "connector_type")
@Data
public class ConnectorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // TODO create an enum here
    @Column(name = "name")
    private String name;

    @Override
    @JsonValue
    public String toString(){
        return name;
    }

}
