package by.cniitu.chargepoint.model.response;

import eu.chargetime.ocpp.model.core.AuthorizeRequest;
import eu.chargetime.ocpp.model.core.IdTagInfo;
import lombok.Getter;
import lombok.Setter;


/**
 * Sent by the Central System to the Charge Point in response to a {@link AuthorizeRequest}.
 */
@Getter
@Setter
public class AuthorizeResponse {

    private IdTagInfo idTagInfo;

}
