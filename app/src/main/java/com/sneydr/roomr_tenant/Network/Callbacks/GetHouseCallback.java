package com.sneydr.roomr_tenant.Network.Callbacks;

import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Network.JSONParser;
import com.sneydr.roomr_tenant.Network.Observables.HouseObservable;
import com.sneydr.roomr_tenant.Network.Observables.NetworkObservable;
import com.sneydr.roomr_tenant.Network.Observers.HouseObserver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class GetHouseCallback extends NetworkCallback implements HouseObservable {

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()){
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                InputStream input = responseBody.byteStream();
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                House house = jsonParser.parseHouse(reader);
                notifyHouse(house);
            }
            else {
                notifyFailure("House", "Error: Empty Response");
            }
        }
        else {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                notifyFailure("House", responseBody.string());
            }
            else {
                notifyFailure("House","Error: Unknown Error Occurred");
            }
        }
    }

    @Override
    public void notifyHouse(House house) {
        HouseObserver houseObserver = (HouseObserver) this.observer;
        houseObserver.onHouse(house);
    }
}
