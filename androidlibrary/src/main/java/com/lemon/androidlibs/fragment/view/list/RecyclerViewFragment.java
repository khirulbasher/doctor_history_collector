package com.lemon.androidlibs.fragment.view.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lemon.androidlibs.R;
import com.lemon.androidlibs.utility.recycler.Item;
import com.lemon.androidlibs.utility.recycler.RecyclerAdapter;
import com.lemon.androidlibs.utility.recycler.RecyclerListener;
import com.lemon.androidlibs.utility.recycler.divider.RecyclerDivider;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickListener;
import com.lemon.androidlibs.fragment.view.FragmentCallback;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;
import static android.support.v7.widget.LinearLayoutManager.VERTICAL;



/**
 * Created by lemon on 2/16/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate", "FieldCanBeLocal"})
public class RecyclerViewFragment extends Fragment {
    private View mainView;
    private RecyclerView recyclerView;
    private List<Item> items;
    private RecyclerAdapter recyclerAdapter;
    private boolean verticalScroll=true;
    private ItemClickListener itemClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mainView=inflater.inflate(R.layout.fragment_recycler,container,false);
        this.recyclerView=mainView.findViewById(R.id.recycler_view);
        setLayoutManager(recyclerView,verticalScroll);
        //setBasicDivider(recyclerView,verticalScroll);
        setDivider(verticalScroll);

        this.recyclerAdapter=new RecyclerAdapter(items);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
        d("listener Added");
        recyclerView.addOnItemTouchListener(new RecyclerListener.TouchListener(itemClickListener,getContext(),recyclerView));
        return this.mainView;
    }

    private void d(String s) {

    }

    private void setLayoutManager(RecyclerView recyclerView,boolean verticalScroll) {
        RecyclerView.LayoutManager layoutManager = verticalScroll?new LinearLayoutManager(getContext(), VERTICAL,false):new LinearLayoutManager(getContext(), HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setBasicDivider(RecyclerView recyclerView,boolean verticalDivider) {
        int divider=verticalDivider? VERTICAL: HORIZONTAL;
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), divider));
    }

    private void setDivider(boolean verticalDivider) {
        setDivider(new RecyclerDivider(getActivity(),verticalDivider,null));
    }

    private void setDivider(RecyclerView.ItemDecoration itemDecoration) {
        this.recyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void onAttach(Context context) {
        FragmentCallback fragmentCallback= (FragmentCallback) context;
        this.items=fragmentCallback.getItems();
        this.itemClickListener=fragmentCallback.getListener();
        super.onAttach(context);
    }
}
