package com.lemon.doctorpointcollector.utility.fragment;

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

import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.concurrent.FragmentCallback;
import com.lemon.doctorpointcollector.utility.util.Item;
import com.lemon.doctorpointcollector.utility.util.ItemAdapter;
import com.lemon.doctorpointcollector.utility.util.inf.ClickListener;

import java.util.List;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class ListFragment extends Fragment implements ClickListener {
    private View view;
    private RecyclerView recyclerView;
    private List<Item> items;
    private FragmentCallback fragmentCallback;
    private String title;
    private ItemAdapter itemAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.list_frag,container,false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        itemAdapter=new ItemAdapter(items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCallback= (FragmentCallback) context;
        items=fragmentCallback.getItems();
        itemAdapter=new ItemAdapter(items);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
