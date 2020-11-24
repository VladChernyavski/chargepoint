package by.cniitu.chargepoint.model.response;

import eu.chargetime.ocpp.model.core.IdTagInfo;
import eu.chargetime.ocpp.model.core.StartTransactionRequest;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point in response to a {@link StartTransactionRequest}.
 */
@Getter
@Setter
public class StartTransactionResponse {

    private IdTagInfo idTagInfo;
    private Integer transactionId;

}
