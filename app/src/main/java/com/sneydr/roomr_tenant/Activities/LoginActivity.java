package com.sneydr.roomr_tenant.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.sneydr.roomr_tenant.R;

import static com.sneydr.roomr_tenant.App.Permission.INTERNET_PERMISSION_REQUEST_CODE;
import static com.sneydr.roomr_tenant.App.Permission.WRITE_EXTERNAL_STORAGE_REQUEST_CODE;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        navController = Navigation.findNavController(this, R.id.nav_login_host_fragment);
    }


    @Override
    public void onBackPressed() {
        if(!Navigation.findNavController(this, R.id.nav_login_host_fragment).popBackStack()){
            finish();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_login_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == INTERNET_PERMISSION_REQUEST_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Internet permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Internet permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Write permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Write permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

