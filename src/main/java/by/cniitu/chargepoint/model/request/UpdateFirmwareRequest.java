package by.cniitu.chargepoint.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class UpdateFirmwareRequest {

    private String location;
    private Integer retries;
    private Calendar retrieveDate;
    private Integer retryInterval;

}
