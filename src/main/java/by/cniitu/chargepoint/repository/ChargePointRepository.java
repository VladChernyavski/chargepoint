package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.ChargePointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargePointRepository extends JpaRepository<ChargePointEntity, Integer> {

}
