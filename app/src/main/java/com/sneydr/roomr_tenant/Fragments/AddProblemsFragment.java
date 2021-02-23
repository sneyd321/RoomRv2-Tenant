package com.sneydr.roomr_tenant.Fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sneydr.roomr_tenant.App.CompoundButtonInput.RadioButtonCompoundButtonInput;
import com.sneydr.roomr_tenant.App.TextInput.NormalTextInput.DescriptionTextInput;
import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Network.Observers.ProblemObserver;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.ViewModels.ProblemViewModel;
import com.sneydr.roomr_tenant.databinding.FragmentAddProblemBinding;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AddProblemsFragment extends FragmentTemplate implements ProblemObserver, View.OnClickListener {


    RadioButtonCompoundButtonInput problemType;
    TextInput description;
    private int houseId;
    private String authToken;
    private ProblemViewModel problemViewModel;
    private FragmentAddProblemBinding fragmentBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_problem, container, false);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("authToken") && bundle.containsKey("houseId")){
            authToken = bundle.getString("authToken");
            houseId = bundle.getInt("houseId");
            fragmentBinding.btnAddProblemReportProblem.setEnabled(true);
            problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);
            problemType = new RadioButtonCompoundButtonInput(fragmentBinding.getRoot(), 0 , R.id.rdgProblemType);
            description = new DescriptionTextInput(fragmentBinding.getRoot(), R.id.tilAddProblemDescription, R.id.edtAddProblemDescription);
            fragmentBinding.btnAddProblemReportProblem.setOnClickListener(this);
            File file = new File(context.getCacheDir(), "Problem.jpg");
            ImageView imgProblem = fragmentBinding.getRoot().findViewById(R.id.imgProblem);
            Picasso.get().load(file).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imgProblem);

        }
        else {
            Toast.makeText(context, "Not Authenticated", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(this).popBackStack();
        }
        return fragmentBinding.getRoot();
    }


    private Problem getProblem() {
        return new Problem(problemType.getText(), description.getText(), houseId);
    }


    @Override
    public void onFailure(String response) {
        super.onFailure(response);
        handler.post(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Shake).duration(1000).playOn(fragmentBinding.getRoot().findViewById(R.id.crdAddProblem));
            }
        });

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    @Override
    public void onProblem(Problem problem) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.SlideOutUp).onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        Bundle bundle = new Bundle();
                        bundle.putString("authToken", authToken);
                        bundle.putInt("houseId", houseId);
                        NavHostFragment.findNavController(AddProblemsFragment.this)
                                .navigate(R.id.action_addProblemFragment_to_tenantStatePagerFragment, bundle);
                    }
                }).duration(1000).playOn(fragmentBinding.getRoot().findViewById(R.id.crdAddProblem));
                fragmentBinding.btnAddProblemReportProblem.setEnabled(true);
            }
        });

    }

    @Override
    public void onClick(View v) {
        File file = new File(context.getCacheDir(), "Problem.jpg");
        problemViewModel.saveProblem(getProblem(), file, authToken,AddProblemsFragment.this);
        v.setEnabled(false);
    }
}
