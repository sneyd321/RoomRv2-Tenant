package com.sneydr.roomr_tenant.ViewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.sneydr.roomr_tenant.Network.Observers.HomeownerObserver;
import com.sneydr.roomr_tenant.Repositories.HomeownerRepository;


public class HomeownerViewModel extends AndroidViewModel {

    HomeownerRepository repository;

    public HomeownerViewModel(Application application) {
        super(application);
        repository = new HomeownerRepository(application);
    }

    public void loadHomeowner(String authToken, int houseId, HomeownerObserver observer) {
        repository.getHomeowner(authToken, houseId, observer);
    }

}
