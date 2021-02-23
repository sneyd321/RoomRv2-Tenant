package com.sneydr.roomr_tenant.SocketIO.Callbacks;

import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.SocketIO.Observables.MessageObservable;
import com.sneydr.roomr_tenant.SocketIO.Observers.MessageObserver;

public class MessageCallback extends SocketCallback implements MessageObservable {


    @Override
    public void call(Object... args) {
        String string = (String) args[0];
        Message message = jsonParser.parseMessage(string);
        notify(message);
    }


    @Override
    public void notify(Message message) {
        MessageObserver messageObserver = (MessageObserver) this.observer;
        messageObserver.onMessage(message);
    }
}
