package com.lemon.androidlibs.utility.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lemon.androidlibs.R;
import com.lemon.androidlibs.utility.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lemon on 4/7/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "unchecked"})
public class FilterAdapter extends BaseAdapter implements Filterable {
    private final List<Item> items;
    private List<Item> filteredItems;
    private LayoutInflater layoutInflater;

    public FilterAdapter(List<Item> items, LayoutInflater layoutInflater) {
        this.items = items;
        this.layoutInflater = layoutInflater;
        this.filteredItems=items;
    }

    @Override
    public int getCount() {
        return filteredItems.size();
    }

    @Override
    public Object getItem(int i) {
        return filteredItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
            view=layoutInflater.inflate(R.layout.filter_item,null);
        ((TextView)view).setText(filteredItems.get(i).title);

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String txt=String.valueOf(charSequence).toLowerCase();
                if(txt==null||txt.isEmpty()) filteredItems = items;
                else {
                    String[] attr=txt.split(" ");
                    filteredItems=new ArrayList<>();
                    for(Item item:items)
                        if(item.searchable.contains(txt) || isContained(attr,item.searchable))
                            filteredItems.add(item);
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filteredItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredItems= (List<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private boolean isContained(String[] attrs,String searchable) {
        for(String attr:attrs)
            if(searchable.contains(attr))
                return true;
        return false;
    }

    public List<Item> getFilteredItems() {
        return filteredItems;
    }
}
