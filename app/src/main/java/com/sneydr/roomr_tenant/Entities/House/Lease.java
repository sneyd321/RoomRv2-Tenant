package com.sneydr.roomr_tenant.Entities.House;

import com.sneydr.roomr_tenant.Entities.Users.Tenant;

import java.util.ArrayList;
import java.util.List;

public class Lease {


    private List<Tenant> tenants;
    private String startDate;
    private String endDate;
    private int houseId;
    private int homeownerId;


    public Lease(List<Tenant> tenants, String startDate, String endDate, int houseId, int homeownerId) {
        this.tenants = filterApprovedTenants(tenants);
        this.startDate = startDate;
        this.endDate = endDate;
        this.houseId = houseId;
        this.homeownerId = homeownerId;
    }


    private List<Tenant> filterApprovedTenants(List<Tenant> tenants) {
        List<Tenant> filteredList = new ArrayList<>();
        for (Tenant tenant : tenants) {
            if (tenant.isApproved()){
                filteredList.add(tenant);
            }
        }
        return filteredList;
    }


    public String getQueueName() {
        return Integer.toString(homeownerId) + "_" + Integer.toString(houseId);
    }
}
