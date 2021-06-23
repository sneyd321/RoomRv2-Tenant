package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Entities.RentDetails.RentDetails;

public interface RentDetailsObservable extends NetworkObservable {

    void notifyRentDetails(RentDetails rentDetails);

}
