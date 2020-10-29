package com.example.pantryapp.ui.recipebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.pantryapp.BR;
import com.example.pantryapp.databinding.FragmentRecipebookBinding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder> {

    public static class ModelViewHolder extends RecyclerView.ViewHolder {

        private final FragmentRecipebookBinding binding;
        public ModelViewHolder(FragmentRecipebookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(RecipeModel item) {
            binding.setModel(item);
        }
    }

    private final SortedList<RecipeModel> sortedList = new SortedList<>(RecipeModel.class, new SortedList.Callback<RecipeModel>() {
        @Override
        public int compare(RecipeModel o1, RecipeModel o2) {
            return comparator.compare(o1, o2);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(RecipeModel oldItem, RecipeModel newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(RecipeModel item1, RecipeModel item2) {
            return item1.getId() == item2.getId();
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }
    });

    private final LayoutInflater inflater;
    private final Comparator<RecipeModel> comparator;

    public ModelAdapter(Context context, Comparator<RecipeModel> comparator) {
        this.inflater = LayoutInflater.from(context);
        this.comparator = comparator;
    }

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final FragmentRecipebookBinding binding = FragmentRecipebookBinding.inflate(inflater, parent, false);
        return new ModelViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        final RecipeModel model = sortedList.get(position);
        holder.bind(model);

        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, 100));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void add(RecipeModel model) {
        sortedList.add(model);
    }

    public void remove(RecipeModel model) {
        sortedList.remove(model);
    }

    public void add(ArrayList<RecipeModel> models) {
        sortedList.addAll(models);
    }

    public void remove(ArrayList<RecipeModel> models) {
        sortedList.beginBatchedUpdates();
        for(RecipeModel model : models) {
            sortedList.remove(model);
        }
        sortedList.endBatchedUpdates();
    }

    public void replaceAll(ArrayList<RecipeModel> models) {
        sortedList.beginBatchedUpdates();
        for(int i = sortedList.size() - 1; i >= 0; i--) {
            final RecipeModel model = sortedList.get(i);
            if(!models.contains(model)) {
                sortedList.remove(model);
            }
        }
        sortedList.addAll(models);
        sortedList.endBatchedUpdates();
    }

    public void notify(ArrayList<RecipeModel> list) {
        if(sortedList != null) {
            sortedList.clear();
            sortedList.addAll(list);
        } else {
            sortedList.addAll(list);
        }

        notifyDataSetChanged();
    }
}
