package by.cniitu.chargepoint.util;

public class TimeUtil {

    // 83 -> "1:23"
    public static String secondsToString(Integer seconds){
        String seconds_str = "" + seconds % 60;
        if (seconds_str.length() == 1){
            seconds_str = "0" + seconds_str;
        }
        return seconds / 60 + ":" + seconds_str;
    }

}
