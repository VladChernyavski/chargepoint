package by.cniitu.chargepoint.config;

import by.cniitu.chargepoint.util.UserUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class ScheduledTask {

    @Scheduled(fixedRate = 60000)
    public void deleteNoConfirmUsers() {
        System.out.println("Call delete no confirm users method");

        List<Integer> codesForRemove = new ArrayList<>();

        for (Map.Entry<LocalDateTime, Integer> map : UserUtil.map.entrySet()) {
            if(map.getKey().isBefore(LocalDateTime.now().minusMinutes(10))){
                codesForRemove.add(map.getValue());
            }
        }

        for (Integer i : codesForRemove){
            UserUtil.noConfirmedUsers.remove(i);
            UserUtil.map.entrySet().removeIf(v -> v.getValue().equals(i));
        }

    }

}
