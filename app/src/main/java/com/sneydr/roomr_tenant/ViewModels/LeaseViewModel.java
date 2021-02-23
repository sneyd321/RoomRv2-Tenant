package com.sneydr.roomr_tenant.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sneydr.roomr_tenant.Entities.House.Lease;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;
import com.sneydr.roomr_tenant.Repositories.LeaseRepository;

public class LeaseViewModel extends AndroidViewModel{

    private LeaseRepository repository;

    public LeaseViewModel(@NonNull Application application) {
        super(application);
        repository = new LeaseRepository(application);

    }


    private void sendLease(Lease lease, NetworkObserver observer) {
        //repository.sendLeaseData(lease, observer);
    }
}
