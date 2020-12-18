package by.cniitu.chargepoint.web.controller;

import by.cniitu.chargepoint.model.ChargePoint;
import by.cniitu.chargepoint.model.request.*;
import by.cniitu.chargepoint.model.web.action.ChargeAction;
import by.cniitu.chargepoint.model.web.action.ReserveAction;
import by.cniitu.chargepoint.model.web.action.UserActionTo;
import by.cniitu.chargepoint.model.web.map.Connector;
import by.cniitu.chargepoint.model.web.map.MapPoint;
import by.cniitu.chargepoint.service.ChargePointService;
import by.cniitu.chargepoint.service.UserActionService;
import by.cniitu.chargepoint.service.enums.ConnectorStatus;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/chargepoint/{id}")
@CrossOrigin("*")
// TODO add more tokens to everything
public class ChargePointController {

    @Autowired
    ChargePointService chargePointService;

    @Autowired
    ServerWebSocket serverWebSocket;

    @Autowired
    UserActionService userActionService;

    @PostMapping("/reservenow")
    public ResponseEntity<Object> reserveNow(@PathVariable int id, @RequestBody ReserveNowRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ReserveNow", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ReserveNow) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    @PostMapping("/cancelreservation")
    public ResponseEntity<Object> cancelReservation(@PathVariable int id, @RequestBody CancelReservationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "CancelReservation", request);

        System.out.println("********************************");
        System.out.println("HTTP request (CancelReservation) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/changeavailability")
    public ResponseEntity<Object> changeAvailability(@PathVariable int id, @RequestBody ChangeAvailabilityRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeAvailability", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeAvailability) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/changeconfiguration")
    public ResponseEntity<Object> changeConfiguration(@PathVariable int id, @RequestBody ChangeConfigurationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/clearcache")
    public ResponseEntity<Object> clearCache(@PathVariable int id, @RequestBody ClearCacheRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearCache", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearCache) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/clearchargingprofile")
    public ResponseEntity<Object> clearChargingProfile(@PathVariable int id, @RequestBody ClearChargingProfileRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    @PostMapping("/datatransfer")
    public ResponseEntity<Object> dataTransfer(@PathVariable int id, @RequestBody DataTransferRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "DataTransfer", request);

        System.out.println("********************************");
        System.out.println("HTTP request (DataTransfer) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getcompositeschedule")
    public ResponseEntity<Object> getCompositeSchedule(@PathVariable int id, @RequestBody GetCompositeScheduleRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetCompositeSchedule", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetCompositeSchedule) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO charge point not support
    @PostMapping("/getconfiguration")
    public ResponseEntity<Object> getConfiguration(@PathVariable int id, @RequestBody GetConfigurationRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getdiagnostics")
    public ResponseEntity<Object> getDiagnostics(@PathVariable int id, @RequestBody GetDiagnosticsRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetDiagnostics", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetDiagnostics) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/getlocallistversion")
    public ResponseEntity<Object> getLocalListVersion(@PathVariable int id, @RequestBody GetLocalListVersionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetLocalListVersion", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetLocalListVersion) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    @PostMapping("/remotestarttransaction")
    public ResponseEntity<Object> remoteStartTransaction(@PathVariable int id, @RequestBody RemoteStartTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStartTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStartTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    @PostMapping("/remotestoptransaction")
    public ResponseEntity<Object> remoteStopTransaction(@PathVariable int id, @RequestBody RemoteStopTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStopTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStopTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

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
    @PostMapping("/sendlocallist")
    public ResponseEntity<Object> sendLocalList(@PathVariable int id, @RequestBody SendLocalListRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SendLocalList", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SendLocalList) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/setchargingprofile")
    public ResponseEntity<Object> setChargingProfile(@PathVariable int id, @RequestBody SetChargingProfileRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SetChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SetChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO not implemented by charge point
    @PostMapping("/triggermessage")
    public ResponseEntity<Object> triggerMessage(@PathVariable int id, @RequestBody TriggerMessageRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "TriggerMessage", request);

        System.out.println("********************************");
        System.out.println("HTTP request (TriggerMessage) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    @PostMapping("/unlockconnector")
    public ResponseEntity<Object> unlockConnector(@PathVariable int id, @RequestBody UnlockConnectorRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "UnlockConnector", request);

        System.out.println("********************************");
        System.out.println("HTTP request (UnlockConnector) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO test
    @PostMapping("/updatefirmware")
    public ResponseEntity<Object> updateFirmware(@PathVariable int id, @RequestBody UpdateFirmwareRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "UpdateFirmware", request);

        System.out.println("********************************");
        System.out.println("HTTP request (UpdateFirmware) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok("{\"message\": \"success\"}");
    }

    // TODO get real tariffs
    @GetMapping("/tariffs")
    public ResponseEntity<Object> getTariffs(@PathVariable int id){

        ResponseEntity<Object> result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"id is not found\"}");

        for(MapPoint mapPoint : new LinkedList<>(ChargePointService.chargePointsMap.values())){
            if(mapPoint.getId() == id){

                double tariff;

                if(id % 2 == 0){
                    tariff = 2.28;
                } else {
                    tariff = 36.6;
                }

                List<Double> tariffs = new LinkedList<>();

                for(int i = 0; i < mapPoint.getConnectors().size(); i++){
                    tariffs.add(tariff);
                }

                result = ResponseEntity.ok(tariffs);

            }
        }

        return result;
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

        // TODO redu
        //if(!ChargePointService.conStatuses.contains(status))
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"unknown status\"}");

        Connector connector;
        try {
            connector = chargePointService.getConnector(id, conId);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }

        // TODO check
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
            connector = chargePointService.getConnector(id, conId);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }
        ConnectorStatus status = connector.getStatus();
        if (status == ConnectorStatus.WORK) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("{\"message\": \"please connect the connector and try again\"}");
        } else if (status== ConnectorStatus.CONNECTED) {
            UserActionService.userActionMap.put(userId, new UserActionTo(id, conId, new ChargeAction(energy)));

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

        // TODO check the existence of the user using database
        // TODO check the balance and save transactions even when the time was 0 (when the client had not enough money)

        Connector connector;
        try {
            connector = chargePointService.getConnector(id, conId);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"" + ex.getMessage() +"\"}");
        }
        if (connector.getStatus() == ConnectorStatus.WORK) {
            UserActionService.userActionMap.put(userId, new UserActionTo(id, conId, new ReserveAction(totalSeconds)));

            // TODO save start of transaction to database end somehow finish transaction
            connector.setStatus(ConnectorStatus.RESERVED);
            serverWebSocket.broadcastUpdate(id);
            return ResponseEntity.ok("{\"message\": \"reservation is started\", \"status\": \"ok\"}");
        }

        // TODO save start of transaction to database end somehow finish transaction
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"reservation process is now unavailable\"}");
    }

}
