package by.cniitu.chargepoint.web.controller;

import by.cniitu.chargepoint.entity.User;
import by.cniitu.chargepoint.model.ChargePoint;
import by.cniitu.chargepoint.model.request.*;
import by.cniitu.chargepoint.model.web.action.ChargeAction;
import by.cniitu.chargepoint.model.web.action.ReserveAction;
import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.service.ChargePointService;
import by.cniitu.chargepoint.service.ConnectorService;
import by.cniitu.chargepoint.service.UserActionService;
import by.cniitu.chargepoint.service.UserService;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chargePoint/{id}")
@CrossOrigin("*")
// TODO add more tokens to everything
public class ChargePointController {

    final ChargePointService chargePointService;
    final ServerWebSocket serverWebSocket;
    final UserActionService userActionService;
    final UserService userService;
    final ConnectorService connectorService;

    public ChargePointController(ChargePointService chargePointService, ServerWebSocket serverWebSocket, UserActionService userActionService, UserService userService, ConnectorService connectorService) {
        this.chargePointService = chargePointService;
        this.serverWebSocket = serverWebSocket;
        this.userActionService = userActionService;
        this.userService = userService;
        this.connectorService = connectorService;
    }

    // TODO something with this
    @PostMapping("/reserveNow")
    public ResponseEntity<Object> reserveNow(@PathVariable int id, @RequestBody ReserveNowRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ReserveNow", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ReserveNow) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/cancelReservation")
    public ResponseEntity<Object> cancelReservation(@PathVariable int id, @RequestBody CancelReservationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "CancelReservation", request);

        System.out.println("********************************");
        System.out.println("HTTP request (CancelReservation) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/changeAvailability")
    public ResponseEntity<Object> changeAvailability(@PathVariable int id, @RequestBody ChangeAvailabilityRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeAvailability", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeAvailability) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/changeConfiguration")
    public ResponseEntity<Object> changeConfiguration(@PathVariable int id, @RequestBody ChangeConfigurationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/clearCache")
    public ResponseEntity<Object> clearCache(@PathVariable int id, @RequestBody ClearCacheRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearCache", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearCache) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/clearChargingProfile")
    public ResponseEntity<Object> clearChargingProfile(@PathVariable int id, @RequestBody ClearChargingProfileRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/dataTransfer")
    public ResponseEntity<Object> dataTransfer(@PathVariable int id, @RequestBody DataTransferRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "DataTransfer", request);

        System.out.println("********************************");
        System.out.println("HTTP request (DataTransfer) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getCompositeSchedule")
    public ResponseEntity<Object> getCompositeSchedule(@PathVariable int id, @RequestBody GetCompositeScheduleRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetCompositeSchedule", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetCompositeSchedule) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/getConfiguration")
    public ResponseEntity<Object> getConfiguration(@PathVariable int id, @RequestBody GetConfigurationRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getDiagnostics")
    public ResponseEntity<Object> getDiagnostics(@PathVariable int id, @RequestBody GetDiagnosticsRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetDiagnostics", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetDiagnostics) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getLocalListVersion")
    public ResponseEntity<Object> getLocalListVersion(@PathVariable int id, @RequestBody GetLocalListVersionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetLocalListVersion", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetLocalListVersion) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/remoteStartTransaction")
    public ResponseEntity<Object> remoteStartTransaction(@PathVariable int id, @RequestBody RemoteStartTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStartTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStartTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/remoteStopTransaction")
    public ResponseEntity<Object> remoteStopTransaction(@PathVariable int id, @RequestBody RemoteStopTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStopTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStopTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/reset")
    public ResponseEntity<Object> reset(@PathVariable int id, @RequestBody ResetRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "Reset", request);

        System.out.println("********************************");
        System.out.println("HTTP request (Reset) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO test
    @PostMapping("/sendLocalList")
    public ResponseEntity<Object> sendLocalList(@PathVariable int id, @RequestBody SendLocalListRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SendLocalList", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SendLocalList) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/setChargingProfile")
    public ResponseEntity<Object> setChargingProfile(@PathVariable int id, @RequestBody SetChargingProfileRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SetChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SetChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/triggerMessage")
    public ResponseEntity<Object> triggerMessage(@PathVariable int id, @RequestBody TriggerMessageRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "TriggerMessage", request);

        System.out.println("********************************");
        System.out.println("HTTP request (TriggerMessage) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO something with this
    @PostMapping("/unlockConnector")
    public ResponseEntity<Object> unlockConnector(@PathVariable int id, @RequestBody UnlockConnectorRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "UnlockConnector", request);

        System.out.println("********************************");
        System.out.println("HTTP request (UnlockConnector) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO test
    @PostMapping("/updateFirmware")
    public ResponseEntity<Object> updateFirmware(@PathVariable int id, @RequestBody UpdateFirmwareRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

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
    @PostMapping("/status/{conId}/{status}")
    public ResponseEntity<Object> status(@PathVariable Integer id, @PathVariable Integer conId,
                                        @PathVariable String status) {

        Connector connector;
        try {
            connector = connectorService.getConnector(id, conId);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }

        connector.setStatus(ConnectorStatus.get(status));
        serverWebSocket.broadcastUpdate(id);
        return ResponseEntity.ok("{\"message\": \"status is changed\"}");
    }

    /**
     * the start of charging process
     * if the status of the connector is green (work) -> ask the user to connect the connector
     * if the status of the connector is yellow (connected) -> start charging
     * otherwise we cannot start charging
     * @param id - charge point id
     * */
    @PostMapping("/start/charge/{conId}/{userId}")
    public ResponseEntity<Object> start(@PathVariable Integer id, @PathVariable Integer conId,
                                        @PathVariable Integer userId, @RequestParam Double energy) throws Exception {

        // TODO check the existence of the user using database
        // TODO check the balance and save transactions even when the time was 0 (when the client had not enough money)

        Connector connector;
        try {
            connector = connectorService.getConnector(id, conId);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }
        ConnectorStatus status = connector.getStatus();
        if (status == ConnectorStatus.WORK) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\": \"please connect the connector and try again\", \"status\": \"plug\"}");
        } else if (status== ConnectorStatus.CONNECTED) {
            // TODO add transaction ID
            UserActionService.userActionMap.put(userId, new UserActionTo(id, conId, new ChargeAction(energy), 0));

            // TODO save start of transaction to database end somehow finish transaction
            connector.setStatus(ConnectorStatus.BUSY);
            serverWebSocket.broadcastUpdate(id);
            return ResponseEntity.ok("{\"message\": \"charging is started\", \"status\": \"ok\"}");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"charging process is now unavailable\"}");
    }

    /**
     * the start of reserving process
     * @param id - charge point id
     * */
    @PostMapping("/start/reserve/{conId}/{userId}")
    public ResponseEntity<Object> start(@PathVariable Integer id, @PathVariable Integer conId,
                                        @PathVariable Integer userId, @RequestParam Integer totalSeconds) throws Exception {

        // check the existence of the user using database
        User user = userService.findOneById(userId);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"unknown user id\", \"status\": \" not ok\"}");
        }

        // TODO check the balance and save transactions even when the time was 0 (when the client had not enough money)


        Connector connector;
        try {
            connector = connectorService.getConnector(id, conId);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }
        if (connector.getStatus() == ConnectorStatus.WORK) {
            // TODO add transaction ID
            UserActionService.userActionMap.put(userId, new UserActionTo(id, conId, new ReserveAction(totalSeconds),  0));

            // TODO save start of transaction to database end somehow finish transaction
            connector.setStatus(ConnectorStatus.RESERVED);
            serverWebSocket.broadcastUpdate(id);
            return ResponseEntity.ok("{\"message\": \"reservation is started\", \"status\": \"ok\"}");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"reservation process is now unavailable\"}");
    }

}
