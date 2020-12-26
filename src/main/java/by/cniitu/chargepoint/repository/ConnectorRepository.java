package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ConnectorRepository extends JpaRepository<ConnectorEntity, Integer> {

    @Query("SELECT c FROM ConnectorEntity c WHERE c.chargePoint.id = ?1")
    List<ConnectorEntity> getConnectors(Integer chargePointId);


}
