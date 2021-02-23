package com.sneydr.roomr_tenant.Entities.Users;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Tenant extends User {


    private int tenantId;
    private int houseId;
    private boolean isApproved;
    private String authToken;


    public Tenant(String firstName, String lastName, String email, String password, int houseId) {
        super(firstName, lastName, email, password);
        this.houseId = houseId;
        this.isApproved = false;

    }

    public Tenant(String firstName, String lastName, String email, int houseId, boolean isApproved, String authToken) {
        super(firstName, lastName, email);
        this.houseId = houseId;
        this.isApproved = isApproved;
        this.authToken = authToken;
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
}
