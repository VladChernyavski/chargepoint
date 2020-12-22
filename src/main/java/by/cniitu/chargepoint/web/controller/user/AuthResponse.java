package by.cniitu.chargepoint.web.controller.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private Integer id;
    private String token;
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthdate;
    private Boolean emailConfirmed;
    private String role; // "admin" or "user"


}
