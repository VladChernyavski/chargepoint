package by.cniitu.chargepoint.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class GetConfigurationRequest {

    private String key;

}
