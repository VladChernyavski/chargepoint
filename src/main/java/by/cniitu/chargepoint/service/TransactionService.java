package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.entity.transaction.Transaction;
import by.cniitu.chargepoint.entity.transaction.TransactionType;
import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.repository.TransactionRepository;
import by.cniitu.chargepoint.repository.TransactionTypeRepository;
import by.cniitu.chargepoint.service.enums.UserActionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionTypeRepository transactionTypeRepository;

    public Integer startTransaction(UserActionEnum userActionEnum, User user, double tariff) {

        String typeName = userActionEnum.toString();
        Integer typeId = transactionTypeRepository.findByName(typeName).getId();

        Transaction transaction = new Transaction();
        transaction.setStartTime(LocalDateTime.now());
        transaction.setTransactionType(new TransactionType(typeId, typeName));
        transaction.setStartMoney(user.getMoney());
        transaction.setTariffPerHour(tariff);
        transaction.setUser(user);

        return transactionRepository.save(transaction).getId();


    }


}
