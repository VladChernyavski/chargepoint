package by.cniitu.chargepoint.model;

import org.java_websocket.WebSocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChargePoint {

    public static final Map<Integer, WebSocket> websocketByChargePointId = new ConcurrentHashMap<>();

    private Integer id;
    private String chargePointModel;
    private String chargePointSerialNumber;
    private String chargePointVendor;
    private String firmwareVersion;
    private String iccid;
    private String imsi;
    private String meterSerialNumber;
    private String meterType;

}
