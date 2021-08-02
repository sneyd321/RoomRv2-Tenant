package com.sneydr.roomr_tenant.Entities.Users;


import com.sneydr.roomr_tenant.Entities.Location.HomeownerLocation;

public class Homeowner extends User {

    private HomeownerLocation homeownerLocation;

    public Homeowner() {
        super();
    }

    public HomeownerLocation getHomeownerLocation() { return homeownerLocation; }
    public void setHomeownerLocation(HomeownerLocation homeownerLocation) { this.homeownerLocation = homeownerLocation; }
}
