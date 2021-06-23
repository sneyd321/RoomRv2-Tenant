package com.sneydr.roomr_tenant.Network.Observables;

public interface AddHouseURLObservable extends NetworkObservable {

    void notifyObserver(String url);

    void notifyFormCompete(String message);
}
