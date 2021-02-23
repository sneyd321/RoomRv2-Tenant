package com.sneydr.roomr_tenant.Adapters.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sneydr.roomr_tenant.databinding.RowEmptyRecyclerviewBinding;


public class EmptyViewHolder extends RecyclerView.ViewHolder {

    RowEmptyRecyclerviewBinding binding;


    public EmptyViewHolder(@NonNull RowEmptyRecyclerviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindText(String text) {
        binding.txtEmptyRecyclerView.setText(text);
    }
}
