package com.sneydr.roomr_tenant.Network.Observables;

import com.sneydr.roomr_tenant.Entities.House.House;

import java.util.List;

public interface HousesObservable extends NetworkObservable {

    void notifyHouses(List<House> houses);


}
