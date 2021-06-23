package com.sneydr.roomr_tenant.Repositories;

import android.app.Application;

import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import okhttp3.Request;

public class HomeownerRepository extends Repository {



    public HomeownerRepository(Application application) {
        super(application);

    }


    public void getHomeowner(String authToken, int houseId, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            Request request = network.getHomeowner(authToken, houseId);
            network.send(request, NetworkCallbackType.GetHomeowner, observer);
        }
    }


}
