package by.cniitu.chargepoint.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;


/**
 * Sent by the Central System to the Charge Point.
 */
@Getter
@Setter
public class ReserveNowRequest {

    private Integer connectorId;
    private String expiryDate;
    private String idTag;
    private Integer reservationId;
    private String parentIdTag;

}
