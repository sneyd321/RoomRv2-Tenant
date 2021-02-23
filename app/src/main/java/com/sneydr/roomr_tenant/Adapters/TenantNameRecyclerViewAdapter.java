package com.sneydr.roomr_tenant.Adapters;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sneydr.roomr_tenant.Adapters.ViewHolders.EmptyViewHolder;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.databinding.RowEmptyRecyclerviewBinding;
import com.sneydr.roomr_tenant.databinding.RowTenantNameBinding;
import com.sneydr.roomr_tenant.Adapters.Listeners.StatefulItemClickListener;
import com.sneydr.roomr_tenant.Adapters.ViewHolders.TenantNameViewHolder;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;

import java.util.List;

public class TenantNameRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Tenant> data;
    private StatefulItemClickListener itemClickListener;
    private final int EMPTY = 0;
    private final int TENANTS = 1;
    private RowTenantNameBinding binding;

    public TenantNameRecyclerViewAdapter(List<Tenant> data) {
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.isEmpty()) {
            return EMPTY;
        }
        else if (data.get(0) != null) {
            return TENANTS;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.row_tenant_name, parent, false);
        return new TenantNameViewHolder(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == EMPTY) {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            emptyViewHolder.bindText("No Tenants");
        } else {
            Tenant tenant = data.get(position);
            TenantNameViewHolder tenantNameViewHolder = (TenantNameViewHolder) holder;
            tenantNameViewHolder.bindTenant(tenant);
        }

    }

    public List<Tenant> getData() {
        return this.data;
    }

    @Override
    public int getItemCount() {
        if (data.isEmpty()) {
            return 1;
        }
        return this.data.size();
    }

    public void setItemClickListener(StatefulItemClickListener onItemClickedListener) {
        this.itemClickListener = onItemClickedListener;
    }

    public Tenant getTenantAtPosition(int position) {
        return this.data.get(position);
    }



}
