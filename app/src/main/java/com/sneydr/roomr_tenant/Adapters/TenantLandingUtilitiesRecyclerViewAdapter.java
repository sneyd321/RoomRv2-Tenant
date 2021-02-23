package com.sneydr.roomr_tenant.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sneydr.roomr_tenant.Adapters.ViewHolders.EmptyViewHolder;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.databinding.RowEmptyRecyclerviewBinding;
import com.sneydr.roomr_tenant.databinding.RowTenantUtilitiesBinding;
import com.sneydr.roomr_tenant.Adapters.ViewHolders.TenantUtilityViewHolder;
import com.sneydr.roomr_tenant.Entities.House.Utility.Utility;


import java.util.List;

public class TenantLandingUtilitiesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Utility> data;
    private final int EMPTY = 0;
    private final int UTILITIES = 1;

    public TenantLandingUtilitiesRecyclerViewAdapter(List<Utility> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.isEmpty()) {
            return EMPTY;
        }
        else if (data.get(0) != null) {
            return UTILITIES;
        }
        return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == EMPTY) {
            RowEmptyRecyclerviewBinding emptyBinding = DataBindingUtil.inflate(inflater, R.layout.row_empty_recyclerview, parent, false);
            return new EmptyViewHolder(emptyBinding);
        }
        RowTenantUtilitiesBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_tenant_utilities, parent, false);
        return new TenantUtilityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder.getItemViewType() == EMPTY) {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) viewHolder;
            emptyViewHolder.bindText("No Houses");
        } else {
            Utility utility = this.data.get(position);
            TenantUtilityViewHolder tenantUtilityViewHolder = (TenantUtilityViewHolder) viewHolder;
            tenantUtilityViewHolder.bindUtility(utility);
        }
    }


    @Override
    public int getItemCount() {
        if (this.data.isEmpty()) {
            return 1;
        }
        return data.size();
    }




}
