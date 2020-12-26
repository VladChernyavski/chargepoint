package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ChargePointRepository extends JpaRepository<ChargePointEntity, Integer> {

    @Query("SELECT c FROM ChargePointEntity c WHERE c.id = ?1")
    ChargePointEntity findOneById(int id);

}
