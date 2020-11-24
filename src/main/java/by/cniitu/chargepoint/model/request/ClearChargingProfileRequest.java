package by.cniitu.chargepoint.model.request;

import eu.chargetime.ocpp.model.core.ChargingProfilePurposeType;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class ClearChargingProfileRequest {

    private Integer id;
    private Integer connectorId;
    private ChargingProfilePurposeType chargingProfilePurpose;
    private Integer stackLevel;

}
