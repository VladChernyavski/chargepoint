package by.cniitu.chargepoint.util;

import by.cniitu.chargepoint.entity.Gender;
import by.cniitu.chargepoint.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserUtil {

    public static Map<Integer, User> noConfirmedUsers = new ConcurrentHashMap<>();
    public static Map<LocalDateTime, Integer> map = new ConcurrentHashMap<>();

    public static boolean isCodeExists(int code){
        return noConfirmedUsers.containsKey(code);
    }

    /**
     * @param code - encoded user
     * @return decoded user or null in case of the error
     */
    public static User getUserByParseCode(String code) {
        byte[] decoded = Base64.getDecoder().decode(code);
        String decodedString = new String(decoded);
        String[] data = decodedString.split("~");
        // if the code is wrong and it is not a user
        if(data.length < 2){
            return null;
        }
        User user = new User();
        user.setEmail(data[0]);
        user.setPassword(data[1]);
        user.setFirstName(data[2]);
        user.setLastName(data[3]);
        user.setGender(data[4].equalsIgnoreCase(Gender.MALE.toString()) ? Gender.MALE : Gender.FEMALE);
        user.setBirthday(LocalDate.parse(data[5]));
        return user;
    }

}
