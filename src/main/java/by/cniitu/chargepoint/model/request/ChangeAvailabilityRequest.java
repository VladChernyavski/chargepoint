package by.cniitu.chargepoint.model.request;

import eu.chargetime.ocpp.model.core.AvailabilityType;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class ChangeAvailabilityRequest {

    private int connectorId;
    private AvailabilityType type;

}
