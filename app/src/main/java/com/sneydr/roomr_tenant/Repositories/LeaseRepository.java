package com.sneydr.roomr_tenant.Repositories;

import android.app.Application;

import com.sneydr.roomr_tenant.Entities.House.Lease;
import com.sneydr.roomr_tenant.Network.Callbacks.NetworkCallbackType;
import com.sneydr.roomr_tenant.Network.Network;
import com.sneydr.roomr_tenant.Network.Observers.NetworkObserver;

import okhttp3.Request;

public class LeaseRepository extends Repository {


    public LeaseRepository(Application application) {
        super(application);
    }

    private void sendLeaseData(Lease lease, NetworkObserver observer) {
        //Request request = network.postLease(lease);
        //network.send(request, NetworkCallbackType.GetLease, observer);
    }

}
