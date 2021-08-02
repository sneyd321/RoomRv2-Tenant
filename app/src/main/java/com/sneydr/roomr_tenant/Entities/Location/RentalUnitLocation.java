package com.sneydr.roomr_tenant.Entities.Location;


public class RentalUnitLocation extends Location{

    private String unitName;
    private boolean isCondo;
    private int parkingSpaces;

    public RentalUnitLocation() {
        super();
    }


    public boolean isCondo() {
        return isCondo;
    }
    public int getParkingSpaces() {
        return parkingSpaces;
    }
    public String getUnitName() {
        return unitName;
    }

}
