package com.sneydr.roomr_tenant.Network.Callbacks;


import com.sneydr.roomr_tenant.Entities.Users.Homeowner;
import com.sneydr.roomr_tenant.Network.Observables.HomeownerObservable;
import com.sneydr.roomr_tenant.Network.Observers.HomeownerObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class GetHomeownerCallback extends NetworkCallback implements HomeownerObservable {






    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()){
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                InputStream input = responseBody.byteStream();
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                Homeowner homeowner = jsonParser.parseHomeowner(reader);
                notifyHomeowner(homeowner);
            }
            else {
                notifyFailure("Homeowner", "Error: Empty Response");
            }
        }
        else {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                notifyFailure("Homeowner", responseBody.string());
            }
            else {
                notifyFailure("Homeowner","Error: Unknown Error Occurred");
            }
        }
        response.close();
    }

    @Override
    public void notifyHomeowner(Homeowner homeowner) {
        HomeownerObserver homeownerObserver = (HomeownerObserver) this.observer;
        homeownerObserver.onHomeowner(homeowner);
    }
}
