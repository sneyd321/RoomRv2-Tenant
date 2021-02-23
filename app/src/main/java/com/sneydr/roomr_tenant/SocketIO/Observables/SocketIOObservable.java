package com.sneydr.roomr_tenant.SocketIO.Observables;

import com.sneydr.roomr_tenant.SocketIO.Observers.SocketIOObserver;

public interface SocketIOObservable {

    void registerObserver(SocketIOObserver observer);

    void clearObserver();


}
