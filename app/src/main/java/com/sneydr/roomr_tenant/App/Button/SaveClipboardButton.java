package com.sneydr.roomr_tenant.App.Button;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.sneydr.roomr_tenant.Entities.House.House;

public class SaveClipboardButton implements View.OnClickListener {

    private Context context;
    private House house;

    public SaveClipboardButton(Context context, House house) {
        this.context = context;
        this.house = house;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {

    }
}
