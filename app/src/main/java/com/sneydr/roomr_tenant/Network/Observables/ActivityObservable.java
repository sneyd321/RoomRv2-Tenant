package com.sneydr.roomr_tenant.Network.Observables;

import java.io.File;

public interface ActivityObservable extends NetworkObservable {

    void notifyObserver(File file);

}
