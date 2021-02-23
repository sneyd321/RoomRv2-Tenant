package com.sneydr.roomr_tenant.Entities.Problem;


import com.sneydr.roomr_tenant.R;

public class CompletedStatus implements ProblemStatus {
    @Override
    public String getName() {
        return "Completed";
    }

    @Override
    public int getColour() {
        return R.color.Green;
    }
}
