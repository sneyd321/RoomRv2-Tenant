package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

public interface SignUpRequestObservable extends NetworkObservable {

    void notifyObserver(String url);
}
