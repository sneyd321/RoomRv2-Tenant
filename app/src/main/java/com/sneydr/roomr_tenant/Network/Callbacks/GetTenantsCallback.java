package com.sneydr.roomr_tenant.Network.Callbacks;

import com.sneydr.roomr_tenant.Network.JSONParser;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Observables.NetworkObservable;
import com.sneydr.roomr_tenant.Network.Observables.TenantsObservable;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;
import com.sneydr.roomr_tenant.Network.Observers.TenantsObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class GetTenantsCallback extends NetworkCallback implements TenantsObservable {


    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()){
            List<Tenant> tenants = jsonParser.parseTenants(response.body().string());
            notifyTenants(tenants);
        }
        else {
            notifyFailure("Tenant",response.body().string());
        }
        response.close();
    }

    @Override
    public void notifyTenants(List<Tenant> tenants) {
        TenantsObserver tenantsObserver = (TenantsObserver) this.observer;
        tenantsObserver.onTenants(tenants);
    }
}
