package by.cniitu.chargepoint.model.response;

import eu.chargetime.ocpp.model.core.DataTransferRequest;
import eu.chargetime.ocpp.model.core.DataTransferStatus;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Charge Point to the Central System or vice versa in response to a {@link DataTransferRequest}.
 */
@Getter
@Setter
public class DataTransferResponse {

    private DataTransferStatus status;
    private String data;

}
