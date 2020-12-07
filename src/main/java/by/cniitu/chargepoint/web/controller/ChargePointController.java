package by.cniitu.chargepoint.web.controller;

import by.cniitu.chargepoint.model.ChargePoint;
import by.cniitu.chargepoint.model.request.*;
import by.cniitu.chargepoint.model.web.MapPoint;
import by.cniitu.chargepoint.service.websocket.ServerWebSocket;
import by.cniitu.chargepoint.util.JsonUtil;
import org.java_websocket.WebSocket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/chargepoint/{id}")
public class ChargePointController {

    @PostMapping("/reservenow")
    public ResponseEntity<Object> reserveNow(@PathVariable int id, @RequestBody ReserveNowRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ReserveNow", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ReserveNow) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancelreservation")
    public ResponseEntity<Object> cancelReservation(@PathVariable int id, @RequestBody CancelReservationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "CancelReservation", request);

        System.out.println("********************************");
        System.out.println("HTTP request (CancelReservation) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO charge point not support
    @PostMapping("/changeavailability")
    public ResponseEntity<Object> changeAvailability(@PathVariable int id, @RequestBody ChangeAvailabilityRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeAvailability", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeAvailability) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO charge point not support
    @PostMapping("/changeconfiguration")
    public ResponseEntity<Object> changeConfiguration(@PathVariable int id, @RequestBody ChangeConfigurationRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ChangeConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ChangeConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO charge point not support
    @PostMapping("/clearcache")
    public ResponseEntity<Object> clearCache(@PathVariable int id, @RequestBody ClearCacheRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearCache", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearCache) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO not implemented by charge point
    @PostMapping("/clearchargingprofile")
    public ResponseEntity<Object> clearChargingProfile(@PathVariable int id, @RequestBody ClearChargingProfileRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "ClearChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (ClearChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/datatransfer")
    public ResponseEntity<Object> dataTransfer(@PathVariable int id, @RequestBody DataTransferRequest request) {
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "DataTransfer", request);

        System.out.println("********************************");
        System.out.println("HTTP request (DataTransfer) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO not implemented by charge point
    @PostMapping("/getcompositeschedule")
    public ResponseEntity<Object> getCompositeSchedule(@PathVariable int id, @RequestBody GetCompositeScheduleRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetCompositeSchedule", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetCompositeSchedule) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO charge point not support
    @PostMapping("/getconfiguration")
    public ResponseEntity<Object> getConfiguration(@PathVariable int id, @RequestBody GetConfigurationRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetConfiguration", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetConfiguration) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO not implemented by charge point
    @PostMapping("/getdiagnostics")
    public ResponseEntity<Object> getDiagnostics(@PathVariable int id, @RequestBody GetDiagnosticsRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetDiagnostics", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetDiagnostics) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO not implemented by charge point
    @PostMapping("/getlocallistversion")
    public ResponseEntity<Object> getLocalListVersion(@PathVariable int id, @RequestBody GetLocalListVersionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "GetLocalListVersion", request);

        System.out.println("********************************");
        System.out.println("HTTP request (GetLocalListVersion) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remotestarttransaction")
    public ResponseEntity<Object> remoteStartTransaction(@PathVariable int id, @RequestBody RemoteStartTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStartTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStartTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remotestoptransaction")
    public ResponseEntity<Object> remoteStopTransaction(@PathVariable int id, @RequestBody RemoteStopTransactionRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "RemoteStopTransaction", request);

        System.out.println("********************************");
        System.out.println("HTTP request (RemoteStopTransaction) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset")
    public ResponseEntity<Object> reset(@PathVariable int id, @RequestBody ResetRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "Reset", request);

        System.out.println("********************************");
        System.out.println("HTTP request (Reset) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO test
    @PostMapping("/sendlocallist")
    public ResponseEntity<Object> sendLocalList(@PathVariable int id, @RequestBody SendLocalListRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SendLocalList", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SendLocalList) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO not implemented by charge point
    @PostMapping("/setchargingprofile")
    public ResponseEntity<Object> setChargingProfile(@PathVariable int id, @RequestBody SetChargingProfileRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "SetChargingProfile", request);

        System.out.println("********************************");
        System.out.println("HTTP request (SetChargingProfile) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO not implemented by charge point
    @PostMapping("/triggermessage")
    public ResponseEntity<Object> triggerMessage(@PathVariable int id, @RequestBody TriggerMessageRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "TriggerMessage", request);

        System.out.println("********************************");
        System.out.println("HTTP request (TriggerMessage) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unlockconnector")
    public ResponseEntity<Object> unlockConnector(@PathVariable int id, @RequestBody UnlockConnectorRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "UnlockConnector", request);

        System.out.println("********************************");
        System.out.println("HTTP request (UnlockConnector) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO test
    @PostMapping("/updatefirmware")
    public ResponseEntity<Object> updateFirmware(@PathVariable int id, @RequestBody UpdateFirmwareRequest request){
        WebSocket webSocket = ChargePoint.websocketByChargePointId.get(id);

        Object[] requestObject = getRequestObject(2, UUID.randomUUID().toString(), "UpdateFirmware", request);

        System.out.println("********************************");
        System.out.println("HTTP request (UpdateFirmware) = " + JsonUtil.getJsonString(requestObject));

        webSocket.send(JsonUtil.getJsonString(requestObject));
        return ResponseEntity.ok().build();
    }

    // TODO get real tariffs
    @GetMapping("/tariffs")
    public ResponseEntity<Object> getTariffs(@PathVariable int id){

        ResponseEntity<Object> result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"id is not found\"}");

        for(MapPoint mapPoint : ServerWebSocket.mapPoints){
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

}
