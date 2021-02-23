package com.sneydr.roomr_tenant.SocketIO.Observables;

import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.SocketIO.Observers.SocketIOObserver;

import java.util.List;

public interface JoinObservable extends SocketIOObservable {

    void notify(List<Message> messages);

}
