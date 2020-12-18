package by.cniitu.chargepoint.service.enums;


import by.cniitu.chargepoint.model.web.action.ChargeAction;
import by.cniitu.chargepoint.model.web.action.ReserveAction;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

// static public final List<String> userActionTypes = Arrays.asList("charge", "reserve");
public enum UserActionEnum {

    CHARGE, RESERVE;

    static final Map<Class, UserActionEnum> classToUserActionName = new HashMap<>();

    static{
        classToUserActionName.put(ChargeAction.class, UserActionEnum.CHARGE);
        classToUserActionName.put(ReserveAction.class, UserActionEnum.RESERVE);
    }

    public static UserActionEnum get(Class class_){
        return classToUserActionName.get(class_);
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    @JsonValue // it is used while creating jsons
    public String getMeters() {
        return toString();
    }

}
