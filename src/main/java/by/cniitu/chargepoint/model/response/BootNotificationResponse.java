package by.cniitu.chargepoint.model.response;

import eu.chargetime.ocpp.model.core.BootNotificationRequest;
import eu.chargetime.ocpp.model.core.RegistrationStatus;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point in response to a {@link BootNotificationRequest}.
 */
@Getter
@Setter
public class BootNotificationResponse {

    private String currentTime;
    private Integer interval;
    private RegistrationStatus status;


}
