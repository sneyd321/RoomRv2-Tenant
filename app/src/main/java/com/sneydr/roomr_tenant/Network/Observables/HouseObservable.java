package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Entities.House.House;

import java.util.List;

public interface HouseObservable extends NetworkObservable {



    void notifyHouse(House house);
}
