package by.cniitu.chargepoint.model.web.action;

import by.cniitu.chargepoint.util.TimeUtil;

public abstract class UserAction{

    String currentTime = "";        // example : "01:23"
    Integer currentSeconds = 0;     // example : 83

    public void nextSecond(){
        currentTime = TimeUtil.secondsToString(currentSeconds++);
    }

}
