package com.sneydr.roomr_tenant.SocketIO.Observers;

import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.SocketIO.Observers.SocketIOObserver;

public interface MessageObserver extends SocketIOObserver {

    void onMessage(Message message);

}
