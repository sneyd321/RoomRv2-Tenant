package com.sneydr.roomr_tenant.App.Button;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.sneydr.roomr_tenant.Activities.MainActivityTenant;
import com.sneydr.roomr_tenant.App.Constants;
import com.sneydr.roomr_tenant.App.IntentFactory;
import com.sneydr.roomr_tenant.Fragments.TenantLandingFragment;
import com.sneydr.roomr_tenant.Network.Observers.ActivityObserver;

public class AddPhotoButton implements View.OnClickListener{

    private ActivityObserver activityObserver;
    private Activity activity;
    private IntentFactory intentFactory;


    public AddPhotoButton(Activity activity, ActivityObserver activityObserver) {
        this.activity = activity;
        this.activityObserver = activityObserver;
        this.intentFactory = new IntentFactory();
    }


    @Override
    public void onClick(View view) {
        MainActivityTenant mainActivityTenant = (MainActivityTenant) activity;
        mainActivityTenant.registerObserver(activityObserver);
        Intent intent = intentFactory.getGalleryIntent();
        mainActivityTenant.startActivityForResult(intent, Constants.IMAGE_INTENT);
    }
}
