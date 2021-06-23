package com.sneydr.roomr_tenant.Network.Observers;


import com.sneydr.roomr_tenant.Entities.Users.Homeowner;

public interface HomeownerObserver extends NetworkObserver {

    void onHomeowner(Homeowner homeowner);

}
