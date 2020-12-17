package by.cniitu.chargepoint.web.controller;

import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.service.UserActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
// TODO add more tokens to everything
public class ActionCancellationController {

    /**
     * the end of charging or reservation process
     *
     * nothing is needed except the userId
     *
     * */
    @PostMapping("/end/{userId}")
    public ResponseEntity<Object> end(@PathVariable Integer userId) {
        UserActionTo userActionTo = UserActionService.userActionMap.get(userId);
        if(userActionTo == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"unknown user id\", \"status\": \" not ok\"}");
        }
        userActionTo.finish();
        return ResponseEntity.ok("{\"message\": \"ok\", \"status\": \"ok\"}");
    }
}
