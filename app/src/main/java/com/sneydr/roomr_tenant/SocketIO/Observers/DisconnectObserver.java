package com.sneydr.roomr_tenant.SocketIO.Observers;

import com.sneydr.roomr_tenant.Entities.Message.Message;

public interface DisconnectObserver extends SocketIOObserver {
    void onDisconnect(Message message);

}
