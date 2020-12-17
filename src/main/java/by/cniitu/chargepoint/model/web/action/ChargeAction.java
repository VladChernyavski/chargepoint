package by.cniitu.chargepoint.model.web.action;

import by.cniitu.chargepoint.util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChargeAction extends UserAction{

    Double totalEnergy;                 // кВт⋅ч
    Double remainedEnergy;              // кВт⋅ч
    Double currentEnergy;               // Вт⋅ч (0.001 кВт⋅ч)
    Integer currentWats = 0;            // Вт⋅ч (0.001 кВт⋅ч)
    String currentTime = "0:00";        // example : "01:23"
    Integer currentSeconds = 0;         // example : 83
    Integer sameCount = 0;              // TODO add 1 when currentWats are the same and set to 0 otherwise
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
        currentEnergy = (double)currentWats/1000;
        if(shouldBeFinished()){
            shouldBeFinished = true;
        }
    }

    /**
     *
     * @return true when: currentWats do not grow for 1 minute or
     *                    the energy is finished or
     *                    we are charging for more than 10 minutes or
     *                    the transaction is set to be finished
     */
    @Override
    boolean shouldBeFinished() {
        return shouldBeFinished || remainedEnergy <= 0 || sameCount >= 60 || currentSeconds > 600;
    }

    @Override
    void finish() {
        shouldBeFinished = true;
    }
}
