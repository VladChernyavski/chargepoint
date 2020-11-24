package by.cniitu.chargepoint.model.request;

import eu.chargetime.ocpp.model.remotetrigger.TriggerMessageRequestType;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class TriggerMessageRequest {

    private TriggerMessageRequestType requestedMessage;
    private Integer connectorId;

}
