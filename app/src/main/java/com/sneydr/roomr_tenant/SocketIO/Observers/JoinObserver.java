package com.sneydr.roomr_tenant.SocketIO.Observers;

import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.SocketIO.Observers.SocketIOObserver;

import java.util.List;

public interface JoinObserver extends SocketIOObserver {

    void onJoin(List<Message> messages);


}
