package com.sneydr.roomr_tenant.Services.State;

import androidx.core.app.NotificationCompat;

import com.sneydr.roomr_tenant.App.NotificationHelper;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.Services.State.NotificationState;

import java.util.Map;

public class FailureState extends NotificationState {
    @Override
    public void buildNotification(NotificationHelper notificationHelper, Map<String, Object> map) {
        notificationHelper.setContentTitle(getContentTitle(map));
        notificationHelper.setContentText("Failed to upload");
        notificationHelper.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        notificationHelper.setPriority(NotificationCompat.PRIORITY_LOW);
        notificationHelper.setProgress(100);
        notificationHelper.build();
    }
}
