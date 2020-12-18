package by.cniitu.chargepoint.service.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Random;

// all possible statuses of the connector
public enum ConnectorStatus {

    ALERT, SERVICE, BUILD, BUSY, WORK, CONNECTED, RESERVED;

    static Random random = new Random();
    static ConnectorStatus[] values = values();

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static ConnectorStatus random(){
        // TODO to get know what was the problem
        int index = random.nextInt() % values.length;
        if(index < 0)
            index += 7;
        return values[index];
    }

    public static ConnectorStatus get(int index){
        return values[index];
    }

    public static ConnectorStatus get(String value){
        return valueOf(value.toUpperCase());
    }

    public static int count(){
        return values.length;
    }

    @JsonValue // it is used while creating jsons
    public String getMeters() {
        return toString();
    }

}
