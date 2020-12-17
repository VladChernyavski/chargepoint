package by.cniitu.chargepoint.model.web.action;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserActionTo {

    static final Map<Class, String> classToUserActionName = new HashMap<>();

    static final Map<String, String> actionNameToConnectorStatus = new HashMap<>();

    static public final List<String> userActionTypes = Arrays.asList("charge", "reserve");

    static{

        classToUserActionName.put(ChargeAction.class, userActionTypes.get(0));
        classToUserActionName.put(ReserveAction.class, userActionTypes.get(1));

        actionNameToConnectorStatus.put(userActionTypes.get(0), "busy");
        actionNameToConnectorStatus.put(userActionTypes.get(1), "reserved");
    }

    Integer chargePointId;
    Integer connectorId;
    String type;
    UserAction userAction;

    public UserActionTo nextSecond(){
        userAction.nextSecond();
        return this;
    }

    public void finish(){
        userAction.finish();
    }

    public boolean shouldBeFinished(){
        return userAction.shouldBeFinished();
    }

    public UserActionTo(Integer chargePointId, Integer connectorId, UserAction userAction) throws Exception{
        this.type = classToUserActionName.get(userAction.getClass());
        if(this.type == null)
            throw new Exception("no messageType found for such messageBody!");
        this.userAction = userAction;
        this.chargePointId = chargePointId;
        this.connectorId = connectorId;
    }

}
