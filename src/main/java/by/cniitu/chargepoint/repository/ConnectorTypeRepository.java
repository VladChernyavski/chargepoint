package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.connector.ConnectorTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ConnectorTypeRepository extends JpaRepository<ConnectorTypeEntity, Integer> {

}
