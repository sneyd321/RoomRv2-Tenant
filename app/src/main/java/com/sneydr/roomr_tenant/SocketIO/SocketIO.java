package com.sneydr.roomr_tenant.SocketIO;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.Network.JSONParser;
import com.sneydr.roomr_tenant.SocketIO.Callbacks.CallbackFactory;
import com.sneydr.roomr_tenant.SocketIO.Callbacks.CallbackType;
import com.sneydr.roomr_tenant.SocketIO.Callbacks.SocketCallback;
import com.sneydr.roomr_tenant.SocketIO.Observers.SocketIOObserver;

import java.net.URISyntaxException;
import java.security.PublicKey;

public class SocketIO {

    private static SocketIO instance;

    private Socket socket;
    private CallbackFactory factory;

    public static SocketIO getInstance() {
        if (instance == null) {
            instance = new SocketIO();
        }
        return instance;
    }

    public SocketIO() {
        try {
            //this.socket = IO.socket("http://34.107.132.144");
            this.socket = IO.socket("http://192.168.0.108:8087");
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
            this.socket = null;
        }
        factory = new CallbackFactory();

    }

    public void registerSocket(String event, Emitter.Listener listener) {
        if (this.socket != null) {
            socket.on(event, listener);
        }
    }

    public void clearSocket(String event) {
        if (this.socket != null) {
            socket.off(event);
        }
    }



    public Emitter.Listener getListener(CallbackType type, SocketIOObserver observer) {
        SocketCallback callback = factory.getSocketCallback(type);
        callback.registerObserver(observer);
        return callback;
    }

    public void connect() {
        if (this.socket != null) {
            this.socket.connect();
        }
    }

    public void emitMessage(String event, Message message) {
        if (this.socket != null) {
            JSONParser jsonParser = JSONParser.getInstance();
            this.socket.emit(event, jsonParser.messageToJson(message));
        }
    }


    public void disconnect() {
        if (this.socket != null) {
            this.socket.disconnect();
        }
    }



}
