package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

public interface InternetAvailableObservable extends NetworkObservable {

    void notifyInternetNotAvailable(String text);
}
