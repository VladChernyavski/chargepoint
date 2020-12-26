package by.cniitu.chargepoint.service;

import by.cniitu.chargepoint.api.Smsc;
import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.repository.UserRepository;
import by.cniitu.chargepoint.util.UserUtil;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Service
@EnableScheduling
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;
    private final MailService mailService;
    private final Smsc smsc;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, Environment environment, MailService mailService, Smsc smsc) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
        this.mailService = mailService;
        this.smsc = smsc;
    }

    @PostConstruct
    void postConstruct() {
        // TODO prepare the pages of users here and than change them every time when userRepository is changed
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

    // create new user and set the password
    public void create(User user) {
        userRepository.save(prepareAndSave(user));
    }

    // save user without codding the password
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

        smsc.send_sms(user.getPhoneNumber(), "Confirmation code: " + code, 0, "", "", 0, "CNIITU charges", "");
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

    public User findOneById(Integer id){
        return userRepository.findOneById(id);
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

    public List<User> findAll(){
        return userRepository.findAll();
    }

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
