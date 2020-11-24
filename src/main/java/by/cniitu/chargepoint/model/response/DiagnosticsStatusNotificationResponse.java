package by.cniitu.chargepoint.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.chargetime.ocpp.model.firmware.DiagnosticsStatusNotificationRequest;


/**
 * Sent by the Central System to the Charge Point in response to a {@link DiagnosticsStatusNotificationRequest}.
 */
@JsonSerialize
public class DiagnosticsStatusNotificationResponse {
}
