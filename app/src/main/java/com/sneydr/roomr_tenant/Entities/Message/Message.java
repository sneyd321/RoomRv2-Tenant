package com.sneydr.roomr_tenant.Entities.Message;

import androidx.annotation.Nullable;
import androidx.room.Ignore;

import com.sneydr.roomr_tenant.Entities.RentDetails.CalendarHandler;


public class Message {

    private String message;
    private String timestamp;
    private String email;
    private String userName;
    private int houseId;
    private String userType;
    @Nullable
    private String imageURL;


    public Message(String message, String email, String userName, String userType, String imageURL, int houseId) {
        this.message = message;
        CalendarHandler calendarHandler = new CalendarHandler();
        this.timestamp = calendarHandler.getNow();
        this.email = email;
        this.userName = userName;
        this.userType = userType;
        this.houseId = houseId;
        this.imageURL = imageURL;

    }

    public String getUserType() {
        return userType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Nullable
    public String getImageURL() {
        return imageURL;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
