package com.sneydr.roomr_tenant.Entities.Location;


import com.sneydr.roomr_tenant.Entities.Users.Homeowner;

public class HomeownerLocation extends Location {

    private String poBox;
    private String unitNumber;

    public HomeownerLocation() {
        super();
    }

    public String getPoBox() {
        return poBox;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

}
