package com.sneydr.roomr_tenant.Network.Observables;


import com.sneydr.roomr_tenant.Entities.Users.Homeowner;

public interface HomeownerObservable extends NetworkObservable {

    void notifyHomeowner(Homeowner homeowner);
}
