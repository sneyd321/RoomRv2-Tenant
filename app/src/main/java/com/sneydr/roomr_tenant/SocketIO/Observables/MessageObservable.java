package com.sneydr.roomr_tenant.SocketIO.Observables;

import com.sneydr.roomr_tenant.Entities.Message.Message;

public interface MessageObservable extends SocketIOObservable {

    void notify(Message message);

}
