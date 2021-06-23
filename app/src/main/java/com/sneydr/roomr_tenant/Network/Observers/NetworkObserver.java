package com.sneydr.roomr_tenant.Network.Observers;

import androidx.lifecycle.LifecycleObserver;

public interface NetworkObserver extends LifecycleObserver {

    void onFailure(String tag, String response);

}
