package com.sneydr.roomr_tenant.SocketIO.Callbacks;

import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.SocketIO.Observables.DisconnectObservable;
import com.sneydr.roomr_tenant.SocketIO.Observers.DisconnectObserver;

import java.util.List;

public class DisconnectCallback extends SocketCallback implements DisconnectObservable {
    @Override
    public void call(Object... args) {
        String string = (String) args[0];
        Message message = jsonParser.parseMessage(string);
        notify(message);
    }

    @Override
    public void notify(Message message) {
        DisconnectObserver disconnectObserver = (DisconnectObserver) observer;
        disconnectObserver.onDisconnect(message);
    }
}
