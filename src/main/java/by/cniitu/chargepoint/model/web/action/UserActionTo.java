package by.cniitu.chargepoint.model.web.action;

import by.cniitu.chargepoint.service.enums.UserActionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class UserActionTo {

    Integer chargePointId;
    Integer connectorId;
    UserActionEnum type;
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
        this.type = UserActionEnum.get(userAction.getClass());
        if(this.type == null)
            throw new Exception("no messageType found for such messageBody!");
        this.userAction = userAction;
        this.chargePointId = chargePointId;
        this.connectorId = connectorId;
    }

}
