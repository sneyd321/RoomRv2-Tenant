package com.sneydr.roomr_tenant.Network.Observers;

import com.sneydr.roomr_tenant.Entities.House.House;

import java.util.List;

public interface HouseObserver extends NetworkObserver {



    void onHouse(House house);
}
