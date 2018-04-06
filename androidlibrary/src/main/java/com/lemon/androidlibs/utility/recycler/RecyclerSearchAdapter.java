package com.lemon.androidlibs.utility.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lemon.androidlibs.R;
import com.lemon.androidlibs.utility.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate", "unchecked"})
public class RecyclerSearchAdapter extends RecyclerView.Adapter<RecyclerSearchAdapter.RecyclerHolder> implements Filterable {
    private List<Item> filterItems;
    private final List<Item> mainItems;

    public RecyclerSearchAdapter(List<Item> filterItems) {
        this.filterItems = filterItems;
        this.mainItems = filterItems;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        Item item = filterItems.get(position);
        holder.title.setText(item.title);
        holder.description.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return filterItems.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String filterText=String.valueOf(charSequence).toLowerCase();
                if(filterText.isEmpty()) filterItems = mainItems;
                else {
                    String[] attr=filterText.split(" ");
                    filterItems=new ArrayList<>();

                    for(Item item:mainItems)
                        if(item.searchable.contains(filterText)||(attr.length>1 && (item.searchable.contains(attr[0]) || item.searchable.contains(attr[1]))))
                            filterItems.add(item);
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filterItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterItems= (List<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView heading;

        RecyclerHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
        }
    }

    public List<Item> getFilterItems() {
        return filterItems;
    }
}