package com.sneydr.roomr_tenant.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sneydr.roomr_tenant.Network.Observers.DocumentsObserver;
import com.sneydr.roomr_tenant.Network.Observers.RentDetailsObserver;
import com.sneydr.roomr_tenant.Repositories.RentDetailsRepository;

public class RentDetailsViewModel extends AndroidViewModel {

    RentDetailsRepository repository;

    public RentDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new RentDetailsRepository(application);

    }


    public void getRentDetails(int houseId, String authToken, RentDetailsObserver observer) {
        repository.getRentDetails(houseId, authToken, observer);
    }
}
