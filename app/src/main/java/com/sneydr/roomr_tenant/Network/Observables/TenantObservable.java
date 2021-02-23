package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Entities.Users.Tenant;

import java.util.List;

public interface TenantObservable extends NetworkObservable {

    void notifyTenant(Tenant tenant);

}
