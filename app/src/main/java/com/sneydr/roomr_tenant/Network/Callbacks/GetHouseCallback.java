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
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            notifyFailure("House", "Error: Empty Response");
            return;
        }
        if (response.isSuccessful()){
            House house = jsonParser.parseHouse(responseBody.byteStream());
            notifyHouse(house);

        }
        else {
            notifyFailure("House", responseBody.string());
        }
        response.close();
    }

    @Override
    public void notifyHouse(House house) {
        HouseObserver houseObserver = (HouseObserver) this.observer;
        houseObserver.onHouse(house);
    }
}
