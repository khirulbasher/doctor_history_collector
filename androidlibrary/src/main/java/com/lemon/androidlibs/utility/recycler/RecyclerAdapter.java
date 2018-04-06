package com.lemon.androidlibs.utility.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lemon.androidlibs.R;
import com.lemon.androidlibs.utility.Item;

import java.util.List;

/**
 * Created by lemon on 2/17/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {
    private List<Item> items;

    public RecyclerAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        Item item=items.get(position);
        holder.title.setText(item.title);
        holder.description.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    

    static class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView heading;

        RecyclerHolder(View itemView) {
            super(itemView);
            this.title=itemView.findViewById(R.id.title);
            this.description=itemView.findViewById(R.id.description);
        }
    }
}
