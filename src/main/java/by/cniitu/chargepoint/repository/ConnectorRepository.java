package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.entity.connector.ConnectorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorRepository extends JpaRepository<ConnectorEntity, Integer> {

}
