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


import java.util.ArrayList;
import java.util.List;


public class House {

    private int houseId;
    private int homeownerId;

    private RentalUnitLocation rentalUnitLocation;

    private RentDetails rentDetails;

    private List<Amenity> amenities;

    private List<Utility> utilities;

    private String authToken;
    private String lease;



    public House(String authToken, RentalUnitLocation rentalUnitLocation, RentDetails rentDetails) {
        this.authToken = authToken;
        this.lease = null;
        this.rentalUnitLocation = rentalUnitLocation;
        this.rentDetails = rentDetails;
        this.amenities = new ArrayList<>();
        this.utilities = new ArrayList<>();
    }

    public House(int houseId, String authToken, String lease, RentalUnitLocation rentalUnitLocation, RentDetails rentDetails) {
        this.houseId = houseId;
        this.authToken = authToken;
        this.lease = lease;
        this.rentalUnitLocation = rentalUnitLocation;
        this.rentDetails = rentDetails;
        this.amenities = new ArrayList<>();
        this.utilities = new ArrayList<>();
    }


    public RentalUnitLocation getRentalUnitLocation() {
        return rentalUnitLocation;
    }

    public String getFullAddress() {
        return this.getRentalUnitLocation().getFormattedPrimaryAddress() + "\n" + this.getRentalUnitLocation().getFormattedSecondaryAddress();
    }

    public RentDetails getRentDetails() {
        return rentDetails;
    }


    public List<Amenity> getAmenities() {
        return amenities;
    }


    public List<Utility> getUtilities() {
        return utilities;
    }

    public List<Utility> getTenantResponsibilityUtilities() {
        List<Utility> tenantUtilities = new ArrayList<>();
        for (Utility utility : this.utilities) {
            if (utility.getResponsibilityOf().equals("Tenant")) {
                tenantUtilities.add(utility);
            }
        }
        return tenantUtilities;
    }


    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public void setUtilities(List<Utility> utilities) {
        this.utilities = utilities;
    }

    public int getHouseId() {
        return houseId;
    }

    public int getHomeownerId() {
        return homeownerId;
    }

    public void setHomeownerId(int homeownerId) {
        this.homeownerId = homeownerId;
    }

    public String getLease() {
        return lease;
    }
}
