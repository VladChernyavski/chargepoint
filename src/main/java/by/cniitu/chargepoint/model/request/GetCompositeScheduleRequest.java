package by.cniitu.chargepoint.model.request;

import eu.chargetime.ocpp.model.core.ChargingRateUnitType;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class GetCompositeScheduleRequest {

    private Integer connectorId;
    private Integer duration;
    private ChargingRateUnitType chargingRateUnit;

}
