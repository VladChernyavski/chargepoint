package by.cniitu.chargepoint.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.chargetime.ocpp.model.firmware.FirmwareStatusNotificationRequest;


/**
 * Sent by the Central System to the Charge Point in response to a {@link FirmwareStatusNotificationRequest}.
 */
@JsonSerialize
public class FirmwareStatusNotificationResponse {
}
