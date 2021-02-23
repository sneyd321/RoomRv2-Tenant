package com.sneydr.roomr_tenant.Adapters.Listeners;

import android.view.View;

import com.sneydr.roomr_tenant.Adapters.ButtonState.ButtonStateContext;

public interface StatefulItemClickListener {

    void onItemClick(View view, int position, ButtonStateContext buttonStateContext);
}
