package by.cniitu.chargepoint.web.controller.user;

import by.cniitu.chargepoint.config.jwt.JwtProvider;
import by.cniitu.chargepoint.model.Gender;
import by.cniitu.chargepoint.model.User;
import by.cniitu.chargepoint.service.UserService;
import by.cniitu.chargepoint.util.JwtsUtil;
import by.cniitu.chargepoint.util.MailThreadExecutorUtil;
import by.cniitu.chargepoint.util.UserUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUserByEmail(@RequestBody RegisterRequest registerRequest) {
        User noConfirmUser = getUserByClaims(registerRequest.getToken(), "registerbyemail");

        if (Objects.isNull(noConfirmUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (userService.isEmailExist(noConfirmUser.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"User with such email exists\"}");
        }

        MailThreadExecutorUtil.execute(() -> userService.confirmUserByEmail(noConfirmUser));
        return ResponseEntity.ok("{\"message\": \"Ok. Check your email please\"}");
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activate(@PathVariable String code) {
        User newUser = UserUtil.getUserByParseCode(code);
        if (newUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Your link is invalid\"}");
        }
        userService.create(newUser);
        return ResponseEntity.ok("{\"message\": \"Your email is confirmed\"}");
    }

    //TODO
    @PostMapping("/registerbyphone")
    public ResponseEntity<Object> registerUserByPhone(@RequestBody RegisterRequest registerRequest) {
        User noConfirmUser = getUserByClaims(registerRequest.getToken(), "registerbyphone");

        if (Objects.isNull(noConfirmUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (userService.isPhoneNumberExist(noConfirmUser.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"User with such phoneNumber exists\"}");
        }

        MailThreadExecutorUtil.execute(() -> userService.confirmUserByPhone(noConfirmUser));
        return ResponseEntity.ok("{\"message\": \"Ok. Check your phone please\"}");
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody RegisterCodeRequest request){
        int code = request.getCode();
        User newUser = UserUtil.noConfirmedUsers.get(code);
        if(newUser == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Your code is invalid\"}");
        }
        userService.create(newUser);
        UserUtil.noConfirmedUsers.remove(code, newUser);
        UserUtil.map.entrySet().removeIf(e -> e.getValue().equals(code));
        return ResponseEntity.ok("{\"message\": \"Your phone is confirmed\"}");
    }

    @PostMapping("/authbyemail")
    public ResponseEntity<AuthResponseEmail> authByEmail(@RequestBody AuthRequest request ){
        User user = getUserByClaims(request.getToken(), "authByEmail");

        if (Objects.isNull(user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtProvider.generateToken(user.getEmail(), user.getPassword());
        AuthResponseEmail response = new AuthResponseEmail(user.getId(), token, user.getEmail(),
                user.getFirstName(), user.getLastName(),
                user.getGender().toString(), user.getBirthday().toString());
        return ResponseEntity.ok(response);
    }

    //TODO confirm phone number
    @PostMapping("/authbyphone")
    public ResponseEntity<AuthResponsePhone> authByPhoneNumber(@RequestBody AuthRequest request ){
        User user = getUserByClaims(request.getToken(), "authByPhone");

        if (Objects.isNull(user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtProvider.generateToken(user.getPhoneNumber(), user.getPassword());
        AuthResponsePhone response = new AuthResponsePhone(user.getId(), token, user.getPhoneNumber(),
                user.getFirstName(), user.getLastName(),
                user.getGender().toString(), user.getBirthday().toString());
        return ResponseEntity.ok(response);
    }

    private User getUserByClaims(String token, String method) {
        Claims claims = JwtsUtil.getClaims(token);
        if (Objects.isNull(claims)) {
            return null;
        }
        User user = null;
        if (method.equals("authByEmail")) {
            String email = claims.get("email").toString();
            String password = claims.get("password").toString();
            user = userService.findByEmailAndPassword(email, password);
        }
        if (method.equals("authByPhone")) {
            String email = claims.get("phoneNumber").toString();
            String password = claims.get("password").toString();
            user = userService.findByPhoneNumberAndPassword(email, password);
        }
        if (method.equals("registerbyemail")){
            String email = claims.get("email").toString();
            String firstName = claims.get("firstName").toString();
            String lastName = claims.get("lastName").toString();
            String gender = claims.get("gender").toString();
            String birthdate = claims.get("birthdate").toString();
            String password = claims.get("password").toString();

            user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setGender(gender.equalsIgnoreCase(Gender.MALE.toString())
                    ? Gender.MALE : Gender.FEMALE);
            user.setBirthday(LocalDate.parse(birthdate));
            user.setPassword(password);
        }
        if (method.equals("registerbyphone")){
            String phone = claims.get("phoneNumber").toString();
            String firstName = claims.get("firstName").toString();
            String lastName = claims.get("lastName").toString();
            String gender = claims.get("gender").toString();
            String birthdate = claims.get("birthdate").toString();
            String password = claims.get("password").toString();

            user = new User();
            user.setPhoneNumber(phone);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setGender(gender.equalsIgnoreCase(Gender.MALE.toString())
                    ? Gender.MALE : Gender.FEMALE);
            user.setBirthday(LocalDate.parse(birthdate));
            user.setPassword(password);
        }
        return user;
    }






    //TODO DELETE
    @GetMapping("/authtoken")
    public String auth(@RequestParam("l") String login,
                       @RequestParam("p") String password
//                       @RequestParam("fn") String firstName,
//                       @RequestParam("ln") String lastName,
//                       @RequestParam("g") String gender,
//                       @RequestParam("b") String birthdate
    ) {
        Map<String, Object> claims = new HashMap<String, Object>() {{
            put("phoneNumber", login);
            put("password", password);
//            put("firstName", firstName);
//            put("lastName", lastName);
//            put("gender", gender);
//            put("birthdate", birthdate);
        }};
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "chargepoint".getBytes())
                .compact();
    }

}
