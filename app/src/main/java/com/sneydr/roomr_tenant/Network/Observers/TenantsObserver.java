package com.sneydr.roomr_tenant.Network.Observers;


import com.sneydr.roomr_tenant.Entities.Users.Tenant;

import java.util.List;

public interface TenantsObserver extends NetworkObserver {
    void onTenants(List<Tenant> tenants);
}
