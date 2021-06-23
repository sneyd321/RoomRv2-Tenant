package com.sneydr.roomr_tenant.Network.Observers;

import com.sneydr.roomr_tenant.Entities.RentDetails.RentDetails;
import com.sneydr.roomr_tenant.Network.Observables.NetworkObservable;

public interface RentDetailsObserver extends NetworkObserver {

    void onRentDetails(RentDetails rentDetails);

}
