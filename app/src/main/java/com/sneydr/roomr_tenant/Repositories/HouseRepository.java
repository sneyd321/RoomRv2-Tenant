package com.sneydr.roomr_tenant.Repositories;

import android.app.Application;

import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import okhttp3.Request;

public class HouseRepository extends Repository {

    public HouseRepository(Application application) {
        super(application);
    }

    private void insert(House house, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            //Request request = network.postHouse(house);
            //network.send(request, NetworkCallbackType.GetHouse, observer);
        }
    }


    public void getHouse(int id, String authToken, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            Request request = network.getHouse(id, authToken);
            network.send(request, NetworkCallbackType.GetHouse, observer);
        }
    }

    private void getHouses(int homeownerId, NetworkObserver observer) {
        if (doesHaveInternet(observer) && doesHaveInternetPermission(observer)) {
            //Request request = network.getHouses(homeownerId);
            //network.send(request, NetworkCallbackType.GetHouses, observer);
        }
    }

}
