package by.cniitu.chargepoint.model.web.action;

public abstract class UserAction{

    abstract void nextSecond();

    abstract boolean shouldBeFinished();

    abstract void finish();

}
