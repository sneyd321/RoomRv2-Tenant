package com.sneydr.roomr_tenant.Adapters.ButtonState;


import com.sneydr.roomr_tenant.R;

public class ApprovedState implements ButtonState {



    public ApprovedState(ButtonStateContext context) {
        context.setState(this);
    }


    @Override
    public int getColour() {
        return R.color.Green;
    }

    @Override
    public int getBackgroundDrawable() {
        return R.drawable.approved_tenant_background;
    }

    @Override
    public String getText() {
        return "Approved";
    }

    @Override
    public int getVectorDrawable() {
        return R.drawable.ic_check_black_24dp;
    }
}
