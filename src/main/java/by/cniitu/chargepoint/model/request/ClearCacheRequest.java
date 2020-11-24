package by.cniitu.chargepoint.model.request;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Sent by the Central System to the Charge Point.
 * Request holds no values and is always valid.
 */
@JsonSerialize
public class ClearCacheRequest {
}
