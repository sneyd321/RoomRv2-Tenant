package com.sneydr.roomr_tenant.Adapters.ViewHolders;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.sneydr.roomr_tenant.Adapters.ButtonState.ApprovedState;
import com.sneydr.roomr_tenant.Adapters.ButtonState.ButtonStateContext;
import com.sneydr.roomr_tenant.Adapters.ButtonState.UnapprovedState;
import com.sneydr.roomr_tenant.Adapters.Listeners.StatefulItemClickListener;
import com.sneydr.roomr_tenant.databinding.RowTenantNameBinding;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;

public class TenantNameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    ButtonStateContext buttonStateContext;
    private StatefulItemClickListener itemClickListener;
    RowTenantNameBinding binding;

    public TenantNameViewHolder(RowTenantNameBinding binding, StatefulItemClickListener itemClickListener){
        super(binding.getRoot());
        this.binding = binding;
        this.itemClickListener = itemClickListener;
        this.buttonStateContext = new ButtonStateContext();
        binding.getRoot().setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            binding.imgApproveTenant.setColorFilter(itemView.getResources().getColor(buttonStateContext.getState().getColour()));
            binding.txtApprovalLabel.setText(buttonStateContext.getState().getText());
            binding.crdTenantName.setBackground(itemView.getResources().getDrawable(buttonStateContext.getState().getBackgroundDrawable()));
            binding.imgApproveTenant.setImageDrawable(itemView.getResources().getDrawable(buttonStateContext.getState().getVectorDrawable()));
            itemClickListener.onItemClick(v, getAdapterPosition(), buttonStateContext);
        }
    }



    public void bindTenant(Tenant tenant) {
        binding.txtTenantName.setText(tenant.getFullName());
        buttonStateContext.setState(new UnapprovedState(buttonStateContext));
        if (tenant.isApproved()) {
            buttonStateContext.setState(new ApprovedState(buttonStateContext));
        }
        binding.imgApproveTenant.setColorFilter(itemView.getResources().getColor(buttonStateContext.getState().getColour()));
        binding.imgApproveTenant.setImageDrawable(itemView.getResources().getDrawable(buttonStateContext.getState().getVectorDrawable()));
        binding.txtApprovalLabel.setText(buttonStateContext.getState().getText());
        binding.crdTenantName.setBackground(itemView.getResources().getDrawable(buttonStateContext.getState().getBackgroundDrawable()));
    }



}
