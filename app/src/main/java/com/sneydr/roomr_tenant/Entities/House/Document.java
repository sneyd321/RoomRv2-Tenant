package com.sneydr.roomr_tenant.Entities.House;

import androidx.annotation.Nullable;

import java.lang.reflect.AccessibleObject;

public class Document extends AccessibleObject {

    private int houseId;
    private String province;
    private String description;
    private String name;
    @Nullable
    private String documentURL;


    public int getHouseId() {
        return houseId;
    }
    public String getProvince() {
        return province;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    @Nullable
    public String getDocumentURL() {
        return documentURL;
    }
}
