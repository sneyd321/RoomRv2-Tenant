package com.sneydr.roomr_tenant.Entities.Problem;


import com.sneydr.roomr_tenant.R;

public class InProgressStatus implements ProblemStatus {
    @Override
    public String getName() {
        return "In Progress";
    }

    @Override
    public int getColour() {
        return R.color.colorSecondary;
    }


}
