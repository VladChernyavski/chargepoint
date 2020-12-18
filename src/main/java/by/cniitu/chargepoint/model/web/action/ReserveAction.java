package by.cniitu.chargepoint.model.web.action;

import by.cniitu.chargepoint.util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReserveAction extends UserAction{

    String totalTime;
    Integer totalSeconds;
    String remainedTime;
    String currentTime = "0:00";        // example : "01:23"
    Integer currentSeconds = 0;         // example : 83
    boolean shouldBeFinished = false;

    public ReserveAction(Integer totalSeconds){
        this.totalSeconds = totalSeconds;
        remainedTime = totalTime = TimeUtil.secondsToString(totalSeconds);
    }

    public void nextSecond(){
        currentTime = TimeUtil.secondsToString(++currentSeconds);
        remainedTime = TimeUtil.secondsToString(totalSeconds - currentSeconds);
        if(shouldBeFinished()){
            shouldBeFinished = true;
        }
    }

    @Override
    boolean shouldBeFinished() {
        return shouldBeFinished || currentSeconds >= totalSeconds;
    }

    @Override
    void finish() {
        shouldBeFinished = true;
    }
}
