package com.sneydr.roomr_tenant.Repositories;

import android.app.Application;

import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import okhttp3.Request;

public class RentDetailsRepository extends Repository {

    public RentDetailsRepository(Application application) {
        super(application);
    }


    public void getRentDetails(int houseId, String authToken, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            Request request = network.getRentDetails(houseId, authToken);
            network.send(request, NetworkCallbackType.GetRentDetails, observer);
        }
    }



}
