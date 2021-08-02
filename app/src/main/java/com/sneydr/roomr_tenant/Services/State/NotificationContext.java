package com.sneydr.roomr_tenant.Services.State;



public class NotificationContext {

    private NotificationState notificationState;

    public NotificationContext() {
        this.notificationState = new PendingState();
    }

    public void setState(NotificationState notificationState){
        this.notificationState = notificationState;
    }

    public NotificationState getState() {
        return this.notificationState;
    }

}
