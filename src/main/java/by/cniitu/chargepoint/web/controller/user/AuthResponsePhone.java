package by.cniitu.chargepoint.web.controller.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponsePhone {

    private Integer id;
    private String token;
    private String phone;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthday;

}
