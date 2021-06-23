package com.sneydr.roomr_tenant.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sneydr.roomr_tenant.Adapters.Listeners.ItemClickListener;
import com.sneydr.roomr_tenant.Adapters.ViewHolders.EmptyViewHolder;
import com.sneydr.roomr_tenant.Adapters.ViewHolders.ProblemViewHolder;
import com.sneydr.roomr_tenant.Adapters.ViewHolders.TenantDocumentViewHolder;
import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.R;
import com.sneydr.roomr_tenant.databinding.RowDocumentBinding;
import com.sneydr.roomr_tenant.databinding.RowEmptyRecyclerviewBinding;

import java.util.List;

public class TenantDocumentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Document> data;
    private RowDocumentBinding binding;
    private ItemClickListener itemClickListener;
    private final int EMPTY = 0;
    private final int DOCUMENTS = 1;

    public TenantDocumentRecyclerViewAdapter(List<Document> documents) {
        this.data = documents;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.isEmpty()) {
            return EMPTY;
        }
        else if (data.get(0) != null) {
            return DOCUMENTS;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.row_document, parent, false);
        return new TenantDocumentViewHolder(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == EMPTY) {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            emptyViewHolder.bindText("No Documents");
        } else {
            Document document = data.get(position);
            TenantDocumentViewHolder tenantDocumentViewHolder = (TenantDocumentViewHolder) holder;
            tenantDocumentViewHolder.bind(document);
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public Document getItemAtPosition(int position) {
        return this.data.get(position);
    }
}
