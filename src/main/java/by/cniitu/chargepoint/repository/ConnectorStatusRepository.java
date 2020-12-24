package by.cniitu.chargepoint.repository;

import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.entity.connector.ConnectorStatusEntity;
import by.cniitu.chargepoint.entity.connector.ConnectorType;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectorStatusRepository extends JpaRepository<ConnectorStatusEntity, Integer> {

    ConnectorStatusEntity findByName(ConnectorStatus name);

}
