package com.sneydr.roomr_tenant.Services.State;

import androidx.core.app.NotificationCompat;


import com.sneydr.roomr_tenant.App.NotificationHelper;
import com.sneydr.roomr_tenant.R;

import java.util.Map;

public class SuccessState extends NotificationState {


    @Override
    public void buildNotification(NotificationHelper notificationHelper, Map<String, Object> map) {
        notificationHelper.setContentTitle(getContentText(map));
        notificationHelper.setContentText("Please Refresh Page To See Update");
        notificationHelper.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        notificationHelper.setPriority(NotificationCompat.PRIORITY_LOW);
        notificationHelper.setProgress(0,0);
        notificationHelper.build();

    }


}
