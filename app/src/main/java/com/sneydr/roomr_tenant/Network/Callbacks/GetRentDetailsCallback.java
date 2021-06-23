package com.sneydr.roomr_tenant.Network.Callbacks;


import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Entities.RentDetails.RentDetails;
import com.sneydr.roomr_tenant.Network.Observables.RentDetailsObservable;
import com.sneydr.roomr_tenant.Network.Observers.DocumentsObserver;
import com.sneydr.roomr_tenant.Network.Observers.RentDetailsObserver;

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

public class GetRentDetailsCallback extends NetworkCallback implements RentDetailsObservable {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (response.isSuccessful()){
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                InputStream input = responseBody.byteStream();
                InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
                RentDetails rentDetails = jsonParser.parseRentDetails(reader);
                notifyRentDetails(rentDetails);
            }
            else {
                notifyFailure("RentDetails", "Error: Empty Response");
            }
        }
        else {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                notifyFailure("RentDetails", responseBody.string());
            }
            else {
                notifyFailure("RentDetails","Error: Unknown Error Occurred");
            }
        }
    }

    @Override
    public void notifyRentDetails(RentDetails rentDetails) {
        RentDetailsObserver rentDetailsObserver = (RentDetailsObserver) this.observer;
        rentDetailsObserver.onRentDetails(rentDetails);
    }
}
