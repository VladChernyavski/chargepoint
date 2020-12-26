package by.cniitu.chargepoint.service.enums;

import com.fasterxml.jackson.annotation.JsonValue;

// all possible statuses of the connector
public enum ConnectorStatus {

    ALERT, SERVICE, BUILD, BUSY, WORK, CONNECTED, RESERVED;

    static ConnectorStatus[] values = values();

    @Override
    @JsonValue
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static ConnectorStatus get(int index){
        return values[index];
    }

    public static ConnectorStatus get(String value) throws Exception {
        return valueOf(value.toUpperCase());
    }

    public static int count(){
        return values.length;
    }

}
