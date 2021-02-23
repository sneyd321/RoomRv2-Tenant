package com.sneydr.roomr_tenant.Repositories;

import android.app.Application;


import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import okhttp3.Request;

public class TenantRepository extends Repository {


    public TenantRepository(Application application) {
        super(application);
    }

    private void insert(Tenant tenant, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            //Request request = network.postTenant(tenant);
            //network.send(request, NetworkCallbackType.GetTenant, observer);
        }
    }

    private void update(Tenant tenant, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            //Request request = network.putTenant(tenant);
            //network.send(request, NetworkCallbackType.GetTenants, observer);
        }
    }

    public void getTenant(String authToken, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            Request request = network.getTenant(authToken);
            network.send(request, NetworkCallbackType.GetTenant, observer);
        }
    }

    private void getTenantsByHouseId(int houseId, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            //Request request = network.getTenants(houseId);
            //network.send(request, NetworkCallbackType.GetTenants, observer);
        }

    }

    public void loginTenant(Login login, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            Request request = network.login(login);
            network.send(request, NetworkCallbackType.GetTenant, observer);
        }
    }



}
