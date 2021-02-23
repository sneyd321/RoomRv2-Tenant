package com.sneydr.roomr_tenant.Network.Observers;

import com.sneydr.roomr_tenant.App.Permission;

public interface InternetPermissionObserver extends NetworkObserver {

    void onNoInternetPermission(Permission permission);
}
