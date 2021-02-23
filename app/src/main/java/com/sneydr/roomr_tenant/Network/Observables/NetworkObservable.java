package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

public interface NetworkObservable {

    void registerObserver(NetworkObserver networkObserver);

    void clearObserver();

    void notifyFailure(String response);
}
