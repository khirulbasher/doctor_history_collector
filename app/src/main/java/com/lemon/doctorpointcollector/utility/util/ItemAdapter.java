package com.lemon.doctorpointcollector.utility.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lemon.doctorpointcollector.R;

import java.util.List;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {
    private List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Item item = items.get(position);
        holder.title.setText(item.title);
        holder.subtitle.setText(item.description);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @SuppressWarnings("WeakerAccess")
    public static class Holder extends RecyclerView.ViewHolder {

        public TextView title,subtitle;

        public Holder(View itemView) {
           super(itemView);
           title=itemView.findViewById(R.id.title);
           subtitle=itemView.findViewById(R.id.subtitle);
        }
    }
}
