package com.sneydr.roomr_tenant.Network.Observers;

import java.io.File;

public interface ActivityObserver extends NetworkObserver {

    void onFile(File file);

}
