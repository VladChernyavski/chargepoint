package by.cniitu.chargepoint.web.controller;

import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.entity.connector.ConnectorEntity;
import by.cniitu.chargepoint.model.ChargePoint;
import by.cniitu.chargepoint.model.request.*;
import by.cniitu.chargepoint.model.web.action.ChargeAction;
import by.cniitu.chargepoint.model.web.action.ReserveAction;
import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.service.*;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import by.cniitu.chargepoint.service.enums.UserActionEnum;
import by.cniitu.chargepoint.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chargePoint/{chargePointId}")
@CrossOrigin("*")
// TODO add more tokens to everything
public class ChargePointController {

    // serverWebSocket should not be here
    final ChargePointService chargePointService;
    final UserActionService userActionService;
    final UserService userService;
    final ConnectorService connectorService;
    final ConnectorStatusService connectorStatusService;
    final TransactionService transactionService;

    public ChargePointController(ChargePointService chargePointService, UserActionService userActionService,
                                 UserService userService, ConnectorService connectorService,
                                 ConnectorStatusService connectorStatusService, TransactionService transactionService) {
        this.chargePointService = chargePointService;
        this.userActionService = userActionService;
        this.userService = userService;
        this.connectorService = connectorService;
        this.connectorStatusService = connectorStatusService;
        this.transactionService = transactionService;
    }

    // TODO something with this
    @PostMapping("/reserveNow")
    public ResponseEntity<Object> reserveNow(@PathVariable int chargePointId, @RequestBody ReserveNowRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ReserveNow", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ReserveNow) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/cancelReservation")
    public ResponseEntity<Object> cancelReservation(@PathVariable int chargePointId, @RequestBody CancelReservationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "CancelReservation", request);

        System.out.println("********************************");
        System.out.println("HTTP request (CancelReservation) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/changeAvailability")
    public ResponseEntity<Object> changeAvailability(@PathVariable int chargePointId, @RequestBody ChangeAvailabilityRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeAvailability", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeAvailability) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/changeConfiguration")
    public ResponseEntity<Object> changeConfiguration(@PathVariable int chargePointId, @RequestBody ChangeConfigurationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/clearCache")
    public ResponseEntity<Object> clearCache(@PathVariable int chargePointId, @RequestBody ClearCacheRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearCache", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearCache) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/clearChargingProfile")
    public ResponseEntity<Object> clearChargingProfile(@PathVariable int chargePointId, @RequestBody ClearChargingProfileRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/dataTransfer")
    public ResponseEntity<Object> dataTransfer(@PathVariable int chargePointId, @RequestBody DataTransferRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "DataTransfer", request);

        System.out.println("********************************");
        System.out.println("HTTP request (DataTransfer) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getCompositeSchedule")
    public ResponseEntity<Object> getCompositeSchedule(@PathVariable int chargePointId, @RequestBody GetCompositeScheduleRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetCompositeSchedule", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetCompositeSchedule) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/getConfiguration")
    public ResponseEntity<Object> getConfiguration(@PathVariable int chargePointId, @RequestBody GetConfigurationRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getDiagnostics")
    public ResponseEntity<Object> getDiagnostics(@PathVariable int chargePointId, @RequestBody GetDiagnosticsRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetDiagnostics", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetDiagnostics) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getLocalListVersion")
    public ResponseEntity<Object> getLocalListVersion(@PathVariable int chargePointId, @RequestBody GetLocalListVersionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetLocalListVersion", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetLocalListVersion) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/remoteStartTransaction")
    public ResponseEntity<Object> remoteStartTransaction(@PathVariable int chargePointId, @RequestBody RemoteStartTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStartTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStartTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/remoteStopTransaction")
    public ResponseEntity<Object> remoteStopTransaction(@PathVariable int chargePointId, @RequestBody RemoteStopTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStopTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStopTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/reset")
    public ResponseEntity<Object> reset(@PathVariable int chargePointId, @RequestBody ResetRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "Reset", request);

        System.out.println("********************************");
        System.out.println("HTTP request (Reset) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO test
    @PostMapping("/sendLocalList")
    public ResponseEntity<Object> sendLocalList(@PathVariable int chargePointId, @RequestBody SendLocalListRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SendLocalList", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SendLocalList) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/setChargingProfile")
    public ResponseEntity<Object> setChargingProfile(@PathVariable int chargePointId, @RequestBody SetChargingProfileRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SetChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SetChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/triggerMessage")
    public ResponseEntity<Object> triggerMessage(@PathVariable int chargePointId, @RequestBody TriggerMessageRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "TriggerMessage", request);

        System.out.println("********************************");
        System.out.println("HTTP request (TriggerMessage) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/unlockConnector")
    public ResponseEntity<Object> unlockConnector(@PathVariable int chargePointId, @RequestBody UnlockConnectorRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "UnlockConnector", request);

        System.out.println("********************************");
        System.out.println("HTTP request (UnlockConnector) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO test
    @PostMapping("/updateFirmware")
    public ResponseEntity<Object> updateFirmware(@PathVariable int chargePointId, @RequestBody UpdateFirmwareRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(chargePointId);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "UpdateFirmware", request);

        System.out.println("********************************");
        System.out.println("HTTP request (UpdateFirmware) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    private Object[] getRequestObject(Object... objects) {
        Object[] requestObject = new Object[objects.length];
        System.arraycopy(objects, 0, requestObject, 0, requestObject.length);
        return requestObject;
    }

    // TODO delete after development
    // manually changing off connector state
    @PostMapping("/status/{connectorNumber}/{status}")
    public ResponseEntity<Object> status(@PathVariable Integer chargePointId, @PathVariable Integer connectorNumber,
                                        @PathVariable String status) {

        ConnectorEntity connectorEntity;
        try {
            connectorEntity = connectorService.getConnector(chargePointId, connectorNumber);
            connectorEntity.setStatus(connectorStatusService.connectorStatusToConnectorStatusEntity(ConnectorStatus.get(status)));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }

        connectorService.save(connectorEntity);
        chargePointService.broadcastUpdate(chargePointId);
        return ResponseEntity.ok("{\"message\": \"status is changed\"}");
    }

    // TODO connect it to real chargePoint, start charging process
    // TODO connect it to real chargePoint, may be lock the connector
    /**
     * the start of reserving or charging process
     * @param chargePointId - charge point id
     * @param param - energy of totalSeconds
     * */
    @PostMapping("/start/{action}/{conId}/{userId}")
    public ResponseEntity<Object> start(@PathVariable Integer chargePointId, @PathVariable String action,
                                        @PathVariable Integer conId, @PathVariable Integer userId, @RequestParam Double param) throws Exception {

        // check the existence of the user using database
        User user = userService.findOneById(userId);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"unknown user id\", \"status\": \" not ok\"}");
        }

        if(userActionService.containsKey(userId)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"finish already started transaction firstly\", \"status\": \" not ok\"}");
        }
        ConnectorEntity connectorEntity;
        try {
            connectorEntity = connectorService.getConnector(chargePointId, conId);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }
        if(action.equals("reserve")) {
            double tariff = connectorEntity.getTariff().getReserve();
            int totalSeconds = (int) param.doubleValue();
            if (user.getMoney() < ((double) totalSeconds / 60) * tariff) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"not enough money\", \"status\": \" not ok\"}");
            }
            if (connectorEntity.getStatus().getName() == ConnectorStatus.WORK) {
                Integer transactionId = transactionService.startTransaction(UserActionEnum.RESERVE, user, tariff, connectorEntity);
                userActionService.put(userId, new UserActionTo(chargePointId, conId,
                        new ReserveAction(totalSeconds), transactionId));
                connectorEntity.setStatus(connectorStatusService.connectorStatusToConnectorStatusEntity(ConnectorStatus.RESERVED));
                connectorService.save(connectorEntity);
                chargePointService.broadcastUpdate(chargePointId);
                return ResponseEntity.ok("{\"message\": \"reservation is started\", \"status\": \"ok\"}");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"reservation process is now unavailable\"}");
        } else if (action.equals("charge")) {
            double energy = param;
            double tariff = connectorEntity.getTariff().getCharge();
            if(user.getMoney() < energy * tariff) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"not enough money\", \"status\": \" not ok\"}");
            }
            ConnectorStatus status = connectorEntity.getStatus().getName();
            if (status == ConnectorStatus.WORK) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\": \"please connect the connector and try again\", \"status\": \"plug\"}");
            } else if (status== ConnectorStatus.CONNECTED) {
                Integer transactionId = transactionService.startTransaction(UserActionEnum.CHARGE, user, tariff, connectorEntity);
                userActionService.put(userId, new UserActionTo(chargePointId, conId, new ChargeAction(energy), transactionId));
                connectorEntity.setStatus(connectorStatusService.connectorStatusToConnectorStatusEntity(ConnectorStatus.BUSY));
                connectorService.save(connectorEntity);
                chargePointService.broadcastUpdate(chargePointId);
                return ResponseEntity.ok("{\"message\": \"charging is started\", \"status\": \"ok\"}");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"charging process is now unavailable\"}");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"unknown action\", \"status\": \" not ok\"}");
        }
    }

}
