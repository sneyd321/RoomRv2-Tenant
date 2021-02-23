package com.sneydr.roomr_tenant.Entities.Problem;

import com.sneydr.roomr_tenant.R;

public class ReportedStatus implements ProblemStatus {
    @Override
    public String getName() {
        return "Reported";
    }

    @Override
    public int getColour() {
        return R.color.Purple;
    }
}
