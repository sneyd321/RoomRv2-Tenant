package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.App.Permission;

public interface InternetPermissionObservable extends NetworkObservable {

    void notifyNoInternetPermission(Permission permission);
}
