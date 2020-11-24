package by.cniitu.chargepoint.model.request;

import eu.chargetime.ocpp.model.core.ChargingProfile;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class SetChargingProfileRequest {

    private Integer connectorId;
    private ChargingProfile csChargingProfile;

}
