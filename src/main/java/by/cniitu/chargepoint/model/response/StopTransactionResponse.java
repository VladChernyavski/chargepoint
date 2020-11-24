package by.cniitu.chargepoint.model.response;

import eu.chargetime.ocpp.model.core.IdTagInfo;
import eu.chargetime.ocpp.model.core.StopTransactionRequest;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point in response to a {@link StopTransactionRequest}.
 */
@Getter
@Setter
public class StopTransactionResponse {

    private IdTagInfo idTagInfo;

}
