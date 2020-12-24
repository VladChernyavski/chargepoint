package by.cniitu.chargepoint.model;

import by.cniitu.chargepoint.entity.Gender;
import by.cniitu.chargepoint.entity.Role;
import by.cniitu.chargepoint.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;

@NoArgsConstructor
@Getter
public class UserTo {

    private Integer id;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Long birthday;
    private Boolean emailConfirmed;
    private Double money;
    private Role role;

    public UserTo(User user){
        id = user.getId();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        gender = user.getGender();
        birthday = user.getBirthday().atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        emailConfirmed = user.getEmailConfirmed();
        money = user.getMoney();
        role = user.getRole();
    }


}
