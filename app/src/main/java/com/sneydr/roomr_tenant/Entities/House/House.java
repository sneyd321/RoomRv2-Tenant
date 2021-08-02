package com.sneydr.roomr_tenant.Entities.House;



import androidx.lifecycle.LiveData;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.sneydr.roomr_tenant.Entities.House.Amenity.Amenity;
import com.sneydr.roomr_tenant.Entities.Location.RentalUnitLocation;
import com.sneydr.roomr_tenant.Entities.RentDetails.RentDetails;

import com.sneydr.roomr_tenant.Entities.House.Utility.Utility;


import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;


public class House extends AccessibleObject {

    private int houseId;
    private int homeownerId;

    private RentalUnitLocation rentalUnitLocation;

    public void setRentalUnitLocation(RentalUnitLocation rentalUnitLocation) { this.rentalUnitLocation = rentalUnitLocation; }


    public String getFullAddress() {
        return this.rentalUnitLocation.getFormattedPrimaryAddress() + "\n" + this.rentalUnitLocation.getFormattedSecondaryAddress();
    }


    public int getHouseId() {
        return houseId;
    }

}
