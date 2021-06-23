package com.sneydr.roomr_tenant.Adapters.ViewHolders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sneydr.roomr_tenant.Adapters.Listeners.ItemClickListener;
import com.sneydr.roomr_tenant.Adapters.Listeners.StatefulItemClickListener;
import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.databinding.RowDocumentBinding;

public class TenantDocumentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RowDocumentBinding binding;
    private ItemClickListener itemClickListener;

    public TenantDocumentViewHolder(RowDocumentBinding binding, ItemClickListener itemClickListener) {
        super(binding.getRoot());
        this.binding = binding;

        this.itemClickListener = itemClickListener;
    }


    public void bind(Document document) {
        this.binding.txtDocumentName.setText(document.getName());
        this.binding.btnViewLease.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
