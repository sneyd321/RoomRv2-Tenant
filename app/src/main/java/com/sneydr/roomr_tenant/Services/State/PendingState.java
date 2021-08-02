package com.sneydr.roomr_tenant.Services.State;

import androidx.core.app.NotificationCompat;


import com.sneydr.roomr_tenant.App.NotificationHelper;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.Services.State.NotificationState;

import java.util.Map;

public class PendingState extends NotificationState {

    @Override
    public void buildNotification(NotificationHelper notificationHelper, Map<String, Object> map) {
        notificationHelper.setContentTitle(getContentTitle(map));
        notificationHelper.setContentText(getContentTitle(map));
        notificationHelper.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        notificationHelper.setIndeterminateProgress();
        notificationHelper.setPriority(NotificationCompat.PRIORITY_LOW);
        notificationHelper.build();
    }
}
