package by.cniitu.chargepoint.web.controller.user;

import by.cniitu.chargepoint.config.jwt.JwtProvider;
import by.cniitu.chargepoint.entity.Gender;
import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.model.Page;
import by.cniitu.chargepoint.model.UserTo;
import by.cniitu.chargepoint.service.UserService;
import by.cniitu.chargepoint.util.JwtUtil;
import by.cniitu.chargepoint.util.MailThreadExecutorUtil;
import by.cniitu.chargepoint.util.UserUtil;

import java.util.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin("*")
@RestController
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<String> activate(@PathVariable String code) {
        User confirmedEmailUser = UserUtil.getUserByParseCode(code);
        if (confirmedEmailUser == null || confirmedEmailUser.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Your link is invalid\"," +
                    "\"toast\": \"yourLinkIsInvalid\"}");
        }
        User user = userService.findUserByEmail(confirmedEmailUser.getEmail());
        user.setEmailConfirmed(true);
        userService.save(user);
        return ResponseEntity.ok("{\"message\": \"Your email is confirmed\", \"toast\": \"yourEmailIsConfirmed\"}");
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUserByPhone(@RequestBody RegisterRequest request) {
        User noConfirmUser = getUserByClaims(request.getToken(), "register");
        if (Objects.isNull(noConfirmUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error\"," +
                    "\"toast\": \"unknownError\"}");
        }
        if (userService.isPhoneNumberExist(noConfirmUser.getPhoneNumber())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "{\"message\": \"User with such phoneNumber exists\"," +
                            "\"toast\": \"userWithSuchPhoneNumberExists\"}");
        }
        if (userService.isEmailExist(noConfirmUser.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "{\"message\": \"User with such email exists\"," +
                            "\"toast\": \"userWithSuchEmailExists\"}");
        }
        MailThreadExecutorUtil.execute(() -> userService.confirmUserByPhone(noConfirmUser));
        return ResponseEntity.ok("{\"message\": \"Ok. Check your phone please\", \"toast\": \"pleaseCheckYourPhone\"}");
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody RegisterCodeRequest request){
        int code = request.getCode();
        User newUser = UserUtil.noConfirmedUsers.get(code);
        if(newUser == null || newUser.getId() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "{\"message\": \"Your code is invalid\", \"toast\": \"yourCodeIsInvalid\"}"
            );
        }
        userService.create(newUser);
        UserUtil.noConfirmedUsers.remove(code, newUser);
        UserUtil.map.entrySet().removeIf(e -> e.getValue().equals(code));
        return ResponseEntity.ok("{\"message\": \"Your phone is confirmed\", \"toast\": \"yourPhoneIsConfirmed\"}");
    }

    @PostMapping("/authByEmail")
    public ResponseEntity<Object> authByEmail(@RequestBody AuthRequest request ){
        User user = getUserByClaims(request.getToken(), "authByEmail");
        if (Objects.isNull(user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Error\"," +
                    "\"toast\": \"unknownError\"}");
        }
        String token = jwtProvider.generateToken(user.getEmail(), user.getPassword());
        AuthResponse response = new AuthResponse(user.getId(), token, user.getPhoneNumber(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getGender().toString(), user.getBirthday().toString(), user.getEmailConfirmed(),
                user.getRole().getName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authByPhone")
    public ResponseEntity<Object> authByPhoneNumber(@RequestBody AuthRequest request ){
        User user = getUserByClaims(request.getToken(), "authByPhone");

        if (Objects.isNull(user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Error\"," +
                    "\"toast\": \"unknownError\"}");
        }
        String token = jwtProvider.generateToken(user.getPhoneNumber(), user.getPassword());
        AuthResponse response = new AuthResponse(user.getId(), token, user.getPhoneNumber(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getGender().toString(), user.getBirthday().toString(), user.getEmailConfirmed(),
                user.getRole().getName());
        return ResponseEntity.ok(response);
    }

    private User getUserByClaims(String token, String method) {
        Claims claims = JwtUtil.getClaims(token);
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
            String phoneNumber = claims.get("phoneNumber").toString();
            String password = claims.get("password").toString();
            user = userService.findByPhoneNumberAndPassword(phoneNumber, password);
        }
        if (method.startsWith("register")){
            String phone= claims.get("phoneNumber").toString();
            String email = claims.get("email").toString();
            String firstName = claims.get("firstName").toString();
            String lastName = claims.get("lastName").toString();
            String gender = claims.get("gender").toString();
            String birthdate = claims.get("birthdate").toString();
            String password = claims.get("password").toString();
            user = new User();
            user.setPhoneNumber(phone);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setGender(gender.equalsIgnoreCase(Gender.MALE.toString())
                    ? Gender.MALE : Gender.FEMALE);
            user.setBirthday(LocalDate.parse(birthdate));
            user.setPassword(password);
            user.setEmailConfirmed(false);
        }
        return user;
    }

    // TODO delete
    @GetMapping("/authToken")
    public String auth(@RequestParam("l") String login,
                       @RequestParam("p") String password
    ) {
        Map<String, Object> claims = new HashMap<String, Object>() {{
            put("phoneNumber", login);
            put("password", password);
        }};
        return Jwts.builder()
                .setClaims(claims)
                // TODO keep secret key only in one place
                .signWith(SignatureAlgorithm.HS512, "chargePoint".getBytes())
                .compact();
    }

    @GetMapping("/money/get/{userId}")
    public ResponseEntity<Object> getMoney(@PathVariable Integer userId) {
        User user = userService.findOneById(userId);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"No such user id\"," +
                    "\"toast\": \"unknownUserId\"}");
        }
        return ResponseEntity.ok("{\"message\": " + (double)(int)(user.getMoney()*100)/100 +", \"toast\": \"$$$\"}");

    }

    @GetMapping("/money/change/{userId}/{amount}")
    public ResponseEntity<Object> changeMoney(@PathVariable Integer userId, @PathVariable Double amount) {
        User user = userService.findOneById(userId);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"No such user id\"," +
                    "\"toast\": \"unknownUserId\"}");
        }
        user = userService.changeMoney(user, amount);
        return ResponseEntity.ok("{\"message\": " + user.getMoney() +", \"toast\": \"$$$\"}");

    }

    // TODO make it with tokens
    // works only using email
    @PostMapping("/changePassword/{email}")
    public ResponseEntity<Object> changePassword(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error\"," +
                    "\"toast\": \"unknownError\"}");
        }
        String newPassword = RandomString.make();
        MailThreadExecutorUtil.execute(() -> userService.changeUserPassword(user, newPassword));
        return ResponseEntity.ok("{\"message\": \"Success, check your email please.\"," +
                "\"toast\": \"pleaseCheckYourEmailPlease\"}");
    }

    @PostMapping("/verify/{email}")
    public ResponseEntity<Object> verifyEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error\"," +
                    "\"toast\": \"unknownError\"}");
        }
        if(user.getEmailConfirmed()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "{\"message\": \"Your e-mail is already verified\"," +
                            "\"toast\": \"yourEmailIsAlreadyVerified\"}");
        }
        MailThreadExecutorUtil.execute(() -> userService.confirmUserByEmail(user));
        return ResponseEntity.ok(
                "{\"message\": \"Check your email please.\", \"toast\": \"pleaseCheckYourEmailPlease\"}"
        );
    }

    // TODO prepare all returned data before
    /**
     *
     * @param page - number of needed page
     * @return the needed page of users (page can have 100 or less users)
     * example: if we have 345 users the 1st page would have 100 users, the 2nd one would have 100 users, ... and the 4th one - 45...
     */
    @GetMapping("/get/{page}/users")
    public ResponseEntity<Object> getPageOfUsers(@PathVariable Integer page) throws Exception {
        List<User> users = userService.findAll();
        int size = users.size();
        Integer pageAmount = size/Page.pageSize;
        if(size % Page.pageSize != 0)
            pageAmount++;
        if(page > pageAmount)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"No such page\"," +
                    "\"toast\": \"pageNotFound\"}");
        int fromIndex = (page - 1) * Page.pageSize;
        int toIndex = fromIndex + Page.pageSize;
        if(toIndex > size) {
            toIndex = size;
        }
        List<UserTo> userToList = new LinkedList<>();
        for(User user : users.subList(fromIndex, toIndex)){
            userToList.add(new UserTo(user));
        }
        Page<UserTo> pageOfUsers = new Page<>(pageAmount, page, userToList);
        return ResponseEntity.ok(pageOfUsers);
    }



}
