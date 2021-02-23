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
    private String authToken;
    private int houseId;
    private Permission permission;
    private FragmentCameraBinding binding;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_camera, container, false);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("authToken") && bundle.containsKey("houseId")){
            authToken = bundle.getString("authToken");
            houseId = bundle.getInt("houseId");

            binding.fabTakePhoto.setOnClickListener(this);
            camera = new Camera(binding.cameraPreview);

            permission = new Permission(getContext());
            if (permission.doesHaveCameraPermission()) {
                camera.startCamera(getViewLifecycleOwner());
            }
            else {
                permission.requestCameraPermission();
                NavHostFragment.findNavController(this).popBackStack();
            }
        }
        else {
            Toast.makeText(context, "Not authenticated", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(this).popBackStack();
        }
        return binding.getRoot();
    }


    @Override
    public void onCameraSuccess(@NonNull ImageCapture.OutputFileResults outputFileResults) {
        Bundle bundle = new Bundle();
        bundle.putString("authToken", authToken);
        bundle.putInt("houseId", houseId);
        NavHostFragment.findNavController(this).navigate(R.id.action_cameraFragment_to_addProblemFragment, bundle);
    }

    @Override
    public void onCameraFailure(@NonNull ImageCaptureException exception) {
        Toast.makeText(context, "Camera failed to take photo.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        camera.capturePicture(this);
    }
}
