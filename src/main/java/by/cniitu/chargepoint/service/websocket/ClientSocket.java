package by.cniitu.chargepoint.service.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.*;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;


public class ClientSocket extends WebSocketClient {


    public ClientSocket(@Value("${websocket.url}") URI serverUri) {
        super(serverUri);
    }

    public ClientSocket(@Value("${websocket.url}") URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello. I am WSClient");
        System.out.println("opened connection");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println(
                "Connection closed by " + (remote ? "remote peer" : "us") + " Code: " + code + " Reason: "
                        + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error");
    }

    public static void main(String[] args) {
        ClientSocket clientSocket = new ClientSocket(URI.create("ws://localhost:8887"), new Draft_6455());
        clientSocket.connect();
    }
}
