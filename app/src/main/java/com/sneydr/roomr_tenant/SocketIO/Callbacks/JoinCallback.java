package com.sneydr.roomr_tenant.SocketIO.Callbacks;

import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.SocketIO.Observables.JoinObservable;
import com.sneydr.roomr_tenant.SocketIO.Observables.MessageObservable;
import com.sneydr.roomr_tenant.SocketIO.Observers.JoinObserver;

import java.util.List;

public class JoinCallback extends SocketCallback implements JoinObservable {


    @Override
    public void call(Object... args) {
        String string = (String) args[0];
        List<Message> messages = jsonParser.parseMessages(string);
        notify(messages);
    }




    @Override
    public void notify(List<Message> messages) {
        JoinObserver joinObserver = (JoinObserver) this.observer;
        joinObserver.onJoin(messages);
    }
}
