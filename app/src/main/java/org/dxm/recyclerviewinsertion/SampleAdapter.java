package org.dxm.recyclerviewinsertion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.dxm.recyclerviewinsertion.databinding.ListItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ants on 9/13/16.
 */

public final class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {
    public final List<Integer> colors = new ArrayList<>();

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(colors.get(position));
    }

    public void insertItemAt(int index, int color) {
        if (index <= getItemCount()) {
            colors.add(index, color);
            notifyItemInserted(index);
        }
    }

    @Override public int getItemCount() {
        return colors.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull private final ListItemBinding binding;
        public ViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
