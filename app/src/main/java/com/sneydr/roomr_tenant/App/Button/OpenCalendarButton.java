package com.sneydr.roomr_tenant.App.Button;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.sneydr.roomr_tenant.App.IntentFactory;
import com.sneydr.roomr_tenant.App.Permission;

public class OpenCalendarButton implements View.OnClickListener {

    private Permission permission;
    private IntentFactory intentFactory;
    private Context context;


    public OpenCalendarButton(Context context) {
        this.context = context;
        permission = new Permission(this.context);
        intentFactory = new IntentFactory();
    }

    @Override
    public void onClick(View view) {

    }
}
