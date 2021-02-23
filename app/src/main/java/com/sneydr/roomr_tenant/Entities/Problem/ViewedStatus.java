package com.sneydr.roomr_tenant.Entities.Problem;

import com.sneydr.roomr_tenant.R;

public class ViewedStatus implements ProblemStatus {
    @Override
    public String getName() {
        return "Viewed";
    }

    @Override
    public int getColour() {
        return R.color.Yellow;
    }
}
