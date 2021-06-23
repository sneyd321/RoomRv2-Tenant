package com.sneydr.roomr_tenant.Network.Observers;


public interface AddHouseURLObserver extends NetworkObserver {

    void onAddHouseRequest(String url);

    void onFormComplete(String message);
}
