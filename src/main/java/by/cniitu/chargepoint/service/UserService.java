package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.api.Smsc;
import by.cniitu.chargepoint.model.Gender;
import by.cniitu.chargepoint.model.User;
import by.cniitu.chargepoint.repository.UserRepository;
import by.cniitu.chargepoint.util.UserUtil;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment environment;

    @Autowired
    private MailService mailService;

    @Autowired
    private Smsc smsc;

    @PostConstruct
    public void postConstruct(){
        System.out.println("passwordEncoder.encode(\"GvK8mQTy\") = " + passwordEncoder.encode("GvK8mQTy"));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public User findByEmailAndPassword(String email, String password) {
        User user = findUserByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User findByPhoneNumberAndPassword(String phoneNumber, String password) {
        User user = findUserByPhoneNumber(phoneNumber);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User create(User user) {
        return userRepository.save(prepareAndSave(user));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean isEmailExist(String email) {
        return findUserByEmail(email) != null;
    }

    public boolean isPhoneNumberExist(String phoneNumber) {
        return findUserByPhoneNumber(phoneNumber) != null;
    }

    public void confirmUserByEmail(User user) {
        String email = user.getEmail();
        String data = email + "~"
                + user.getPassword() + "~"
                + user.getFirstName() + "~"
                + user.getLastName() + "~"
                + user.getGender() + "~"
                + user.getBirthday().toString();
        String encode = Base64.getEncoder().encodeToString(data.getBytes());

        String message = String.format("Hello, %s! \n" +
                        "Please, visit next link to confirm your email: http://" +
                        environment.getProperty("server.host") +
                        ":" + environment.getProperty("local.server.port") + "/activate/%s",
                email, encode);
        try {
            mailService.send(email, "Activation code", message);
        } catch (MailException e) {
            System.out.println(e.getMessage());
        }
    }

    public void confirmUserByPhone(User user) {
        Random random = new Random();
        int code = 1000 + random.nextInt(10000 - 1000);
        while (UserUtil.isCodeExists(code)) {
            code = 1000 + random.nextInt(10000 - 1000);
        }

        UserUtil.noConfirmedUsers.put(code, user);
        UserUtil.map.put(LocalDateTime.now(), code);

        smsc.send_sms(user.getPhoneNumber(), "Confirmation code: " + code, 0, "", "", 0, "CHARGEPOINT", "");
    }

    private User prepareAndSave(User user) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        if (user.getEmail() != null) {
            user.setEmail(user.getEmail().toLowerCase());
        }
        if (user.getPhoneNumber() != null) {
            user.setPhoneNumber(user.getPhoneNumber());
        }
        return user;
    }

    public User getOne(Integer id){
        return userRepository.getOne(id);
    }

    public User changeMoney(User user, Double amount){
        user.setMoney(user.getMoney() + amount);
        userRepository.save(user);
        return user;
    }

    public void changeUserPassword(User user, String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
        String email = user.getEmail();
        String message = String.format("Hello, %s! \n" +
                "Your password was changed. Your new password is %s", email, newPassword);
        try {
            mailService.send(email, "Your password was changed", message);
        } catch (MailException e){
            System.out.println(e.getMessage());
        }
        userRepository.save(user);
    }

}
