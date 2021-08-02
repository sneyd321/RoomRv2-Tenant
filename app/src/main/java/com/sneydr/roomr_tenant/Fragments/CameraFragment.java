package com.sneydr.roomr_tenant.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.view.PreviewView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sneydr.roomr_tenant.App.Camera.Camera;
import com.sneydr.roomr_tenant.App.Camera.CameraObserver;
import com.sneydr.roomr_tenant.App.Permission;
import com.sneydr.roomr_tenant.Fragments.FragmentTemplate;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.databinding.FragmentCameraBinding;

public class CameraFragment extends FragmentTemplate implements CameraObserver, View.OnClickListener {

    private Camera camera;
    private FragmentCameraBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false);
        Bundle bundle = getArguments();
        if (!permission.doesHaveCameraPermission()) {
            permission.requestCameraPermission();
            navigation.navigateBack(this);
            return binding.getRoot();
        }
        if (bundle != null && bundle.containsKey("authToken") && bundle.containsKey("houseId") && bundle.containsKey("email")){
            authToken = bundle.getString("authToken");
            houseId = bundle.getInt("houseId");
            email = bundle.getString("email");
            camera = new Camera(binding.cameraPreview);
            binding.fabTakePhoto.setOnClickListener(this);
            camera.startCamera(getViewLifecycleOwner());
        }
        else {
            Toast.makeText(context, "Not Authorized", Toast.LENGTH_LONG).show();
            navigation.navigateBack(this);
        }
        return binding.getRoot();
    }


    @Override
    public void onCameraSuccess(@NonNull ImageCapture.OutputFileResults outputFileResults) {
        navigation.navigate(this, R.id.action_cameraFragment_to_addProblemFragment, authToken, houseId, email);
    }

    @Override
    public void onCameraFailure(@NonNull ImageCaptureException exception) {
        Toast.makeText(context, "Camera failed to take photo.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        camera.capturePicture(this);
    }
}
