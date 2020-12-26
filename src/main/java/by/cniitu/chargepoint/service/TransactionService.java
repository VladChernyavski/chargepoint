package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.entity.transaction.Transaction;
import by.cniitu.chargepoint.entity.transaction.TransactionType;
import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.repository.TransactionRepository;
import by.cniitu.chargepoint.repository.TransactionTypeRepository;
import by.cniitu.chargepoint.service.enums.UserActionEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TransactionService {

    final TransactionRepository transactionRepository;
    final TransactionTypeRepository transactionTypeRepository;
    final UserService userService;

    public TransactionService(TransactionRepository transactionRepository,
                              TransactionTypeRepository transactionTypeRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.userService = userService;

        // TODO finish all unfinished transaction (in case of server breakdown)

    }

    // tariff is double because tariffs in database can be changed but not in previous transactions
    public Integer startTransaction(UserActionEnum userActionEnum, User user, double tariff, ConnectorEntity connectorEntity) {

        TransactionType transactionType = transactionTypeRepository.findByName(userActionEnum);
        Transaction transaction = new Transaction();
        transaction.setStartTime(LocalDateTime.now());
        transaction.setTransactionType(transactionType);
        transaction.setStartMoney(user.getMoney());
        transaction.setTariff(tariff);
        transaction.setUser(user);
        transaction.setConnectorEntity(connectorEntity);

        return transactionRepository.save(transaction).getId();

    }

    public void stopTransaction(UserActionTo userActionTo, Integer userId) {

        Transaction transaction = transactionRepository.findOneById(userActionTo.getTransactionId());
        transaction.setFinishTime(LocalDateTime.now());
        long totalMillisecond = transaction.getFinishTime().toInstant(ZoneOffset.UTC).toEpochMilli() -
                transaction.getStartTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        transaction.setTotalMilliseconds(totalMillisecond);
        double tariffParam = userActionTo.getTariffParam();
        if (userActionTo.getType() == UserActionEnum.CHARGE){
            transaction.setUsedEnergy(tariffParam);
        } else /* UserActionEnum.RESERVE */ {
            transaction.setUsedEnergy(0d);
        }

        // take money from user account
        double totalMoney = transaction.getTariff() * tariffParam;
        transaction.setTotalMoney(totalMoney);
        double finishMoney = transaction.getStartMoney() - totalMoney;
        transaction.setFinishMoney(finishMoney);

        // something went wrong
        // User user = userService.findOneById(transaction.getUser().getId());
        User user = userService.findOneById(userId);
        user.setMoney(finishMoney);
        userService.save(user);
        transactionRepository.save(transaction);

    }

    // TODO write a method that finishes all unfinished transactions after rebooting the server
    // TODO write stopTransaction with no args

}
