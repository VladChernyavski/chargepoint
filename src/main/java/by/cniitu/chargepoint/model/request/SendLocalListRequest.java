package by.cniitu.chargepoint.model.request;

import eu.chargetime.ocpp.model.localauthlist.AuthorizationData;
import eu.chargetime.ocpp.model.localauthlist.UpdateType;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class SendLocalListRequest {

    private Integer listVersion;
    private AuthorizationData localAuthorizationList;
    private UpdateType updateType;

}
