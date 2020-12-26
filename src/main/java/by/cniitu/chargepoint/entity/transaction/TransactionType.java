package by.cniitu.chargepoint.entity.transaction;

import by.cniitu.chargepoint.service.enums.UserActionEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transaction_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private UserActionEnum name;

    // it is used
    @JsonValue
    public UserActionEnum toJson(){
        return name;
    }

}
