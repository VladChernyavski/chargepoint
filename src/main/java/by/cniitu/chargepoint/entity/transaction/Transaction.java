package by.cniitu.chargepoint.entity.transaction;

import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.entity.transaction.TransactionType;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @Column(name = "total_time")
    private LocalDateTime totalTime;

    @Column(name = "start_money")
    private Double startMoney;

    @Column(name = "finish_money")
    private Double finishMoney;

    @Column(name = "total_money")
    private Double totalMoney;

    @Column(name = "used_energy")
    private Double usedEnergy;

    @Column(name = "tariff_per_hour")
    private Double tariffPerHour;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
