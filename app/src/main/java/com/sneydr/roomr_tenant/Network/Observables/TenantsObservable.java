package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Entities.Users.Tenant;

import java.util.List;

public interface TenantsObservable extends NetworkObservable {

    void notifyTenants(List<Tenant> tenants);
}
