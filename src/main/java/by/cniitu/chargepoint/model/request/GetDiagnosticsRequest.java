package by.cniitu.chargepoint.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class GetDiagnosticsRequest {

    private String location;
    private int retries;
    private int retryInterval;
    private Calendar startTime;
    private Calendar stopTime;

}
