package by.cniitu.chargepoint.model.response;

import eu.chargetime.ocpp.model.core.HeartbeatRequest;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point in response to a {@link HeartbeatRequest}.
 */
@Getter
@Setter
public class HeartbeatResponse {

    private String currentTime;

}
