package by.cniitu.chargepoint.web.controller;

import by.cniitu.chargepoint.service.UserActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
// TODO add more tokens to everything
public class ActionCancellationController {

    final UserActionService userActionService;

    public ActionCancellationController(UserActionService userActionService){
        this.userActionService = userActionService;
    }

    /**
     * the end of charging or reservation process
     *
     * nothing is needed except the userId
     *
     * */
    @PostMapping("/end/{userId}")
    public ResponseEntity<Object> end(@PathVariable Integer userId) {
        if(!userActionService.containsKey(userId)){
            // TODO make a class with message, status and toast fields
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"unknown user id\"," +
                    "\"status\": \" not ok\", \"toast\": \"unknownUserId\"}");
        }
        userActionService.finish(userId);
        return ResponseEntity.ok("{\"message\": \"ok\", \"status\": \"ok\", \"toast\": \"transactionFinished\"}");
    }
}
