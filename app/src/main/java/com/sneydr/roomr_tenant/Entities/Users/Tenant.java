package com.sneydr.roomr_tenant.Entities.Users;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Tenant extends User {


    private int tenantId;
    private int houseId;
    private String phoneNumber;



    private boolean isApproved;
    private String authToken;
    @Nullable
    private String imageURL;


    public Tenant(String firstName, String lastName, String email, String phoneNumber, String password, int houseId) {
        super(firstName, lastName, email, password);
        this.phoneNumber = phoneNumber;
        this.houseId = houseId;
        this.isApproved = false;
        this.imageURL = null;

    }

    public Tenant(String firstName, String lastName, String email, String phoneNumber, @Nullable String imageURL, int houseId, boolean isApproved, String authToken) {
        super(firstName, lastName, email);
        this.houseId = houseId;
        this.phoneNumber = phoneNumber;
        this.isApproved = isApproved;
        this.authToken = authToken;
        this.imageURL = imageURL;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Nullable
    public String getImageURL() {
        return imageURL;
    }
}
