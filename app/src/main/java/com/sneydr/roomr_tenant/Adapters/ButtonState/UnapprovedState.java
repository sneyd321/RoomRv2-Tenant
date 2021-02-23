package com.sneydr.roomr_tenant.Adapters.ButtonState;

import com.sneydr.roomr_tenant.Adapters.ButtonState.ButtonState;
import com.sneydr.roomr_tenant.Adapters.ButtonState.ButtonStateContext;
import com.sneydr.roomr_tenant.R;


public class UnapprovedState implements ButtonState {


    public UnapprovedState(ButtonStateContext context) {
        context.setState(this);
    }

    @Override
    public int getColour() {
        return R.color.Red;
    }

    @Override
    public int getBackgroundDrawable() {
        return R.drawable.unnapproved_tenant_background;
    }

    @Override
    public String getText() {
        return "Unapproved";
    }

    @Override
    public int getVectorDrawable() {
        return R.drawable.ic_baseline_clear_24;
    }
}
