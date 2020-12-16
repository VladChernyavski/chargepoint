package by.cniitu.chargepoint.model.web.action;

import by.cniitu.chargepoint.util.TimeUtil;

public class ReserveAction extends UserAction{

    String totalTime = "";
    Integer totalSeconds = 0;
    String remainedTime = "";

    public void nextSecond(){
        super.nextSecond();
        remainedTime = TimeUtil.secondsToString(totalSeconds - currentSeconds);
    }

    public ReserveAction(ReserveAction reserveAction){
        this.currentTime = reserveAction.currentTime;
        this.currentSeconds = reserveAction.currentSeconds;
    }

}
