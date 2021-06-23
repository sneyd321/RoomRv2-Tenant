package com.sneydr.roomr_tenant.Network.Callbacks;



import com.sneydr.roomr_tenant.Network.Observables.AddHouseURLObservable;
import com.sneydr.roomr_tenant.Network.Observers.AddHouseURLObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class GetAddHouseURLCallback extends NetworkCallback implements AddHouseURLObservable {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

        if (response.isSuccessful()) {
            if (response.code() == 204) {
                notifyFormCompete("TEST");
            }
            System.out.println("TEST");
            notifyObserver(response.body().string());
        }

        else {
            notifyFailure("Web","Failed to connect to server");
        }
        response.close();
    }

    @Override
    public void notifyObserver(String url) {
        AddHouseURLObserver addHouseURLObserver = (AddHouseURLObserver) this.observer;
        addHouseURLObserver.onAddHouseRequest(url);
    }

    @Override
    public void notifyFormCompete(String message) {
        AddHouseURLObserver addHouseURLObserver = (AddHouseURLObserver) this.observer;
        addHouseURLObserver.onFormComplete(message);
    }
}
