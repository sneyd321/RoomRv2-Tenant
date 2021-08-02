package com.sneydr.roomr_tenant.Entities.Users;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Tenant extends User {


    private int tenantId;
    private int houseId;
    private boolean isApproved;

    public Tenant() {
        super();
    }


    public boolean isApproved() {
        return isApproved;
    }
    public int getHouseId() {
        return houseId;
    }


}
