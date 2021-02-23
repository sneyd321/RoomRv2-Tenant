package com.sneydr.roomr_tenant.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Network.Observers.HouseObserver;
import com.sneydr.roomr_tenant.Network.Observers.HousesObserver;
import com.sneydr.roomr_tenant.Repositories.HouseRepository;

public class HouseViewModel extends AndroidViewModel implements LifecycleObserver {


    HouseRepository repository;


    public HouseViewModel(@NonNull Application application) {
        super(application);
        repository = new HouseRepository(application);

    }

    private void saveHouse(House house, HouseObserver observer) {
        //repository.insert(house, observer);
    }

    public void getHouse(int houseId, String authToken, HouseObserver observer) {
        repository.getHouse(houseId, authToken, observer);
    }

    private void getHouses(int homeownerId, HousesObserver observer) {
        //repository.getHouses(homeownerId, observer);
    }




}
