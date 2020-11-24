package by.cniitu.chargepoint.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.chargetime.ocpp.model.core.StatusNotificationRequest;


/**
 * Sent by the Central System to the Charge Point in response to a {@link StatusNotificationRequest}.
 */
@JsonSerialize
public class StatusNotificationResponse {
}
