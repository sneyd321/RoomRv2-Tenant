package com.sneydr.roomr_tenant.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.sneydr.roomr_tenant.Activities.MainActivityTenant;
import com.sneydr.roomr_tenant.App.CompoundButtonInput.CompoundButtonInput;

import com.sneydr.roomr_tenant.App.CompoundButtonInput.RadioButtonCompoundButtonInput;
import com.sneydr.roomr_tenant.App.TextInput.NormalTextInput.EmailTextInput;
import com.sneydr.roomr_tenant.App.TextInput.NormalTextInput.PasswordTextInput;
import com.sneydr.roomr_tenant.App.TextInput.NumberTextInput.HouseIdNumberTextInput;
import com.sneydr.roomr_tenant.App.TextInput.NumberTextInput.NumberTextInput;
import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;
import com.sneydr.roomr_tenant.Network.Observers.TenantObserver;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.ViewModels.TenantViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

public class LoginFragment extends FragmentTemplate implements TenantObserver {



    private TextInput email, password;
    private NumberTextInput houseId;


    protected void initUI(View view) {
        email = new EmailTextInput(view, R.id.tilLoginEmail, R.id.edtLoginEmail);
        password = new PasswordTextInput(view, R.id.tilLoginPassword, R.id.edtLoginPassword);
        houseId = new HouseIdNumberTextInput(view, R.id.tilLoginHouseId, R.id.edtLoginHouseId);
        Button btnLogin = view.findViewById(R.id.btnLoginLogin);
        btnLogin.setOnClickListener(onLogin);
        Button btnSignup = view.findViewById(R.id.btnLoginSignup);
        btnSignup.setOnClickListener(onSignUp);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initUI(view);
        return view;
    }

    private View.OnClickListener onSignUp = view -> NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_signUpFragment);

    View.OnClickListener onLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Login login = new Login(email.getText(), password.getText(), houseId.getNumber());
            TenantViewModel tenantViewModel = ViewModelProviders.of(LoginFragment.this).get(TenantViewModel.class);
            tenantViewModel.login(login, LoginFragment.this);
        }
    };

    @Override
    public void onTenant(Tenant tenant) {
        Intent intent = new Intent(getActivity(), MainActivityTenant.class);
        intent.putExtra("authToken", tenant.getAuthToken());
        intent.putExtra("houseId", tenant.getHouseId());
        startActivity(intent);
    }




}
