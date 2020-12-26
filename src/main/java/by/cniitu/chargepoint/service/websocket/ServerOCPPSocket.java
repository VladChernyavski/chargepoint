package by.cniitu.chargepoint.service.websocket;

import by.cniitu.chargepoint.model.ChargePoint;
import by.cniitu.chargepoint.model.response.*;
import by.cniitu.chargepoint.util.DataTransferUtilObject;
import by.cniitu.chargepoint.util.JsonUtil;
import eu.chargetime.ocpp.model.core.AuthorizationStatus;
import eu.chargetime.ocpp.model.core.DataTransferStatus;
import eu.chargetime.ocpp.model.core.IdTagInfo;
import eu.chargetime.ocpp.model.core.RegistrationStatus;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Service
public class ServerOCPPSocket extends WebSocketServer {

    public ServerOCPPSocket(@Value("${websocket.ocpp.port}") int port) {
        super(new InetSocketAddress(port));
    }

    @PostConstruct
    public void startServerSocket() {
        this.start();
    }

    @Override
    public void onStart() {
        System.out.println("ServerOCPPSocket onStart");
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        // TODO save id
        Integer chargePointId = Integer.valueOf(webSocket.getResourceDescriptor().substring(1));
        ChargePoint.websocketByChargePointId.put(chargePointId, webSocket);

        System.out.println("webSocket.getResourceDescriptor() = " + webSocket.getResourceDescriptor().substring(1));
        System.out.println("Socket onOpen " + webSocket);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("Socket onClose " + webSocket);

        // TODO charge point is off

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("request = " + s);

        if (s.contains("Heartbeat")) {
            sendHeartbeatResponse(webSocket, s);
        }

        if (s.contains("BootNotification")) {
            sendBootNotificationResponse(webSocket, s);
        }

        if (s.contains("StatusNotification")) {
            sendStatusNotificationResponse(webSocket, s);
        }

        if (s.contains("DataTransfer")) {
            sendDataTransferResponse(webSocket, s);
        }

        if (s.contains("MeterValues")) {
            sendMeterValuesResponse(webSocket, s);
        }

        /*
            TODO need to test
         */
        if (s.contains("Authorize")) {
            sendAuthorizeResponse(webSocket, s);
        }

        if (s.contains("DiagnosticsStatusNotification")) {
            sendDiagnosticsStatusNotificationResponse(webSocket, s);
        }

        if (s.contains("FirmwareStatusNotification")) {
            sendFirmwareStatusNotificationResponse(webSocket, s);
        }

        if (s.contains("StartTransaction")) {
            sendStartTransactionResponse(webSocket, s);
        }

        if (s.contains("StopTransaction")) {
            sendStopTransactionResponse(webSocket, s);
        }

        // TODO update information about charge points and just save it

    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    private void sendHeartbeatResponse(WebSocket webSocket, String message) {
        HeartbeatResponse response = new HeartbeatResponse();
        response.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("Heartbeat response = " + JsonUtil.getJsonString(responseObject));
            System.out.println("----------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        Возвращает ResponseStatus.Accepted
        Может возвращать ResponseStatus.Accepted, ResponseStatus.Rejected, ResponseStatus.Pending
     */
    private void sendBootNotificationResponse(WebSocket webSocket, String message) {
        BootNotificationResponse response = new BootNotificationResponse();
        response.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        response.setInterval(10);
        response.setStatus(RegistrationStatus.Accepted);

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("BootNotificationResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("----------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        TODO need to test and change
     */
    private void sendStatusNotificationResponse(WebSocket webSocket, String message) {
        StatusNotificationResponse response = new StatusNotificationResponse();

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("StatusNotificationResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("----------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        Возвращает DataTransferStatus.Accepted
        Может возвращать DataTransferStatus.Accepted, DataTransferStatus.Rejected,
            DataTransferStatus.UnknownMessageId, DataTransferStatus.UnknownVendorId
     */
    private void sendDataTransferResponse(WebSocket webSocket, String message) {
        DataTransferResponse response = new DataTransferResponse();
        response.setStatus(DataTransferStatus.Accepted);

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            DataTransferUtilObject object = JsonUtil.getDataTransferObject(JsonUtil.getJsonString(requestObject[3]));
            if(object == null){
                response.setStatus(DataTransferStatus.Rejected);
                webSocket.send(JsonUtil.getJsonString(getResponseObject(3, requestObject[1], response)));
                return;
            }
            if (object.getData() != null) {
                response.setData(object.getData());
            }
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("DataTransferResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("----------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        TODO need to test and change
     */
    private void sendMeterValuesResponse(WebSocket webSocket, String message) {
        MeterValuesResponse response = new MeterValuesResponse();

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("MeterValuesResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("----------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        TODO need to test and change
        Возвращает AuthorizationStatus.Accepted
        Может возвращать AuthorizationStatus.Accepted, AuthorizationStatus.Blocked,
            AuthorizationStatus.Expired, AuthorizationStatus.Invalid, AuthorizationStatus.ConcurrentTx
     */
    private void sendAuthorizeResponse(WebSocket webSocket, String message) {
        AuthorizeResponse response = new AuthorizeResponse();
        IdTagInfo tagInfo = new IdTagInfo();
        tagInfo.setStatus(AuthorizationStatus.Accepted);

        //время когда idTag будет удален из кэша
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        tagInfo.setExpiryDate(calendar);

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            tagInfo.setParentIdTag((String) requestObject[3]);
            response.setIdTagInfo(tagInfo);

            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("AuthorizeResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("--------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        TODO need to test and change
     */
    private void sendDiagnosticsStatusNotificationResponse(WebSocket webSocket, String message) {
        DiagnosticsStatusNotificationResponse response = new DiagnosticsStatusNotificationResponse();

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("DiagnosticsStatusNotificationResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("--------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        TODO need to test and change
     */
    private void sendFirmwareStatusNotificationResponse(WebSocket webSocket, String message) {
        FirmwareStatusNotificationResponse response = new FirmwareStatusNotificationResponse();

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("FirmwareStatusNotificationResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("--------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        TODO need to test and change
     */
    private void sendStartTransactionResponse(WebSocket webSocket, String message) {
        StartTransactionResponse response = new StartTransactionResponse();
        //изменить id транзакции
        response.setTransactionId(123456);

        IdTagInfo tagInfo = new IdTagInfo();
        tagInfo.setStatus(AuthorizationStatus.Accepted);
        response.setIdTagInfo(tagInfo);

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("StartTransactionResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("--------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    /*
        TODO need to test and change
     */
    private void sendStopTransactionResponse(WebSocket webSocket, String message) {
        StopTransactionResponse response = new StopTransactionResponse();
        IdTagInfo tagInfo = new IdTagInfo();
        tagInfo.setStatus(AuthorizationStatus.Accepted);
        response.setIdTagInfo(tagInfo);

        Object[] requestObject = JsonUtil.getObjectByJson(message);
        if (requestObject != null) {
            Object[] responseObject = getResponseObject(3, requestObject[1], response);

            System.out.println("StopTransactionResponse = " + JsonUtil.getJsonString(responseObject));
            System.out.println("--------------------------------------");

            webSocket.send(JsonUtil.getJsonString(responseObject));
        }

    }

    private Object[] getResponseObject(Object... objects) {
        Object[] responseObject = new Object[objects.length];
        System.arraycopy(objects, 0, responseObject, 0, responseObject.length);
        return responseObject;
    }
}
