package by.cniitu.chargepoint.entity.transaction;

import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@ToString
@Transactional
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

    // TODO understand how to work with postgresql interval
    @Column(name = "total_milliseconds")
    private Long totalMilliseconds;

    @Column(name = "start_money")
    private Double startMoney;

    @Column(name = "finish_money")
    private Double finishMoney;

    @Column(name = "total_money")
    private Double totalMoney;

    @Column(name = "used_energy")
    private Double usedEnergy;

    @Column(name = "tariff")
    private Double tariff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "connector_id")
    private ConnectorEntity connectorEntity;


}
