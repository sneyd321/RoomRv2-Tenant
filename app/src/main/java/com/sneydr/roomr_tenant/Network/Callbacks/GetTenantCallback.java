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
        if (response.isSuccessful()){
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                InputStream input = responseBody.byteStream();
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                Tenant tenant = jsonParser.parseTenant(reader);
                notifyTenant(tenant);
            }
            else {
                notifyFailure("Tenant", "Error: Empty Response");
            }
        }
        else {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                notifyFailure("Tenant", responseBody.string());
            }
            else {
                notifyFailure("Tenant","Error: Unknown Error Occurred");
            }
        }
    }

    @Override
    public void notifyTenant(Tenant tenant) {
        TenantObserver tenantObserver = (TenantObserver) this.observer;
        tenantObserver.onTenant(tenant);
    }
}
