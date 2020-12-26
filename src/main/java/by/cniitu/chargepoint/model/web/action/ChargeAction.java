package by.cniitu.chargepoint.model.web.action;

import by.cniitu.chargepoint.util.TimeUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChargeAction extends UserAction{

    // кВт⋅ч
    Double totalEnergy;

    // кВт⋅ч
    Double remainedEnergy;

    // Вт⋅ч (0.001 кВт⋅ч)
    Double currentEnergy;

    @JsonIgnore
    // Вт⋅ч (0.001 кВт⋅ч)
    Integer currentWats = 0;

    // example : "01:23"
    String currentTime = "0:00";

    @JsonIgnore
    // example : 83
    Integer currentSeconds = 0;

    @JsonIgnore
    // TODO add 1 when currentWats are the same and set to 0 otherwise
    Integer sameCount = 0;
    boolean shouldBeFinished = false;

    public ChargeAction(Double totalEnergy){
        this.remainedEnergy = this.totalEnergy = totalEnergy;
    }

    public void nextSecond(){
        // TODO add energy while development but after get and add real energy value
        // TODO some mocks
        currentTime = TimeUtil.secondsToString(++currentSeconds);
        currentWats += 30;
        remainedEnergy = (totalEnergy * 1000 - currentWats)/1000;
        if(remainedEnergy < 0){
            remainedEnergy = 0d;
        }
        currentEnergy = (double)currentWats/1000;
        if(shouldBeFinished()){
            shouldBeFinished = true;
        }
    }

    /**
     *
     * @return true when: currentWats do not grow for 1 minute or
     *                    the energy is finished or
     *                    the transaction is set to be finished
     */
    @Override
    boolean shouldBeFinished() {
        return shouldBeFinished || remainedEnergy <= 0 || sameCount >= 60;
    }

    @Override
    void finish() {
        shouldBeFinished = true;
    }

    @Override
    double getTariffParam() {
        return currentEnergy;
    }
}
