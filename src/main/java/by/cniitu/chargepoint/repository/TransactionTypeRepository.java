package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.transaction.TransactionType;
import by.cniitu.chargepoint.service.enums.UserActionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {

    TransactionType findByName(UserActionEnum name);

}
