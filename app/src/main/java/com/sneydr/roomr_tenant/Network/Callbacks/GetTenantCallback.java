package com.sneydr.roomr_tenant.Network.Callbacks;

import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Network.JSONParser;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Observables.TenantObservable;
import com.sneydr.roomr_tenant.Network.Observers.TenantObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetTenantCallback extends NetworkCallback implements TenantObservable {

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            notifyFailure("Tenant", "Error: Empty Response");
            return;
        }
        if (response.isSuccessful()){
            Tenant tenant = jsonParser.parseTenant(responseBody.byteStream());
            notifyTenant(tenant);
        }
        else {
            notifyFailure("Tenant", responseBody.string());
        }
        response.close();
    }

    @Override
    public void notifyTenant(Tenant tenant) {
        TenantObserver tenantObserver = (TenantObserver) this.observer;
        tenantObserver.onTenant(tenant);
    }
}
