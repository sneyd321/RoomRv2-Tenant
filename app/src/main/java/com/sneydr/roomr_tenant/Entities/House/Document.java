package com.sneydr.roomr_tenant.Entities.House;

import androidx.annotation.Nullable;

public class Document {



    private int houseId;
    private String province;
    private String description;
    private String name;
    @Nullable
    private String documentURL;


    public Document(int houseId, String province, String description, String name, @Nullable String documentURL) {
        this.houseId = houseId;
        this.province = province;
        this.description = description;
        this.name = name;
        this.documentURL = documentURL;
    }

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
