package com.sneydr.roomr_tenant.Adapters.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.databinding.RowProblemBinding;
import com.sneydr.roomr_tenant.Adapters.Listeners.ItemClickListener;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;

import com.squareup.picasso.Picasso;

public class ProblemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private ItemClickListener itemClickListener;
    private RowProblemBinding binding;

    public ProblemViewHolder(@NonNull RowProblemBinding binding, ItemClickListener itemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.itemClickListener = itemClickListener;
        binding.getRoot().setOnClickListener(this);
    }

    public void bindProblem(Problem problem) {
        if (problem.getImageUrl() == null) {
            Picasso.get()
                    .load(R.drawable.ic_baseline_broken_image_24)
                    .placeholder(R.drawable.ic_baseline_image_search_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .fit()
                    .centerCrop()
                    .into(binding.imgHomeownerProblem);
        }
        else {
            Picasso.get()
                    .load(problem.getImageUrl())
                    .placeholder(R.drawable.ic_baseline_image_search_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .fit()
                    .centerCrop()
                    .into(binding.imgHomeownerProblem);
        }

        binding.txtHomeownerProblemType.setText(problem.getCategory());
        binding.txtHomeownerProblemDescription.setText(problem.getDescription());
        binding.txtTenantProblemStatus.setText(problem.getStatus().getName());
        binding.txtTenantProblemStatus.setTextColor(binding.getRoot().getResources().getColor(problem.getStatus().getColour()));
        binding.txtTenantDatePosted.setText(problem.getDatePosted());
        binding.txtTenantLastUpdated.setText(problem.getLastUpdated());
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

}
