package com.sneydr.roomr_tenant.Adapters.ViewHolders;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sneydr.roomr_tenant.databinding.RowTenantUtilitiesBinding;
import com.sneydr.roomr_tenant.Entities.House.Utility.Utility;


public class TenantUtilityViewHolder extends RecyclerView.ViewHolder {


    private RowTenantUtilitiesBinding binding;

    public TenantUtilityViewHolder(@NonNull RowTenantUtilitiesBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindUtility(Utility utility) {

        binding.txtTenantLandingUtilityName.setText(utility.getName() +" responsibility of: " + utility.getResponsibilityOf());
    }




}
