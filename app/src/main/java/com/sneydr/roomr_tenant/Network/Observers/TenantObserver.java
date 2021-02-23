package com.sneydr.roomr_tenant.Network.Observers;

import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import java.util.List;

public interface TenantObserver extends NetworkObserver {



    void onTenant(Tenant tenant);
}
