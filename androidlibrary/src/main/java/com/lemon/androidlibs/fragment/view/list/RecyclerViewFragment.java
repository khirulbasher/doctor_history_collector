package com.lemon.androidlibs.fragment.view.list;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.lemon.androidlibs.R;
import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.androidlibs.fragment.view.FragmentCallback;
import com.lemon.androidlibs.utility.Utility;
import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.fragment.Dialog;
import com.lemon.androidlibs.utility.fragment.SimpleDialog;
import com.lemon.androidlibs.utility.inf.Callback;
import com.lemon.androidlibs.utility.recycler.RecyclerListener;
import com.lemon.androidlibs.utility.recycler.RecyclerSearchAdapter;
import com.lemon.androidlibs.utility.recycler.divider.RecyclerDivider;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickCallback;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickListener;

import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;
import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

/**
 * Created by lemon on 2/16/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate", "FieldCanBeLocal", "ConstantConditions", "StatementWithEmptyBody"})
public class RecyclerViewFragment extends Fragment implements ItemClickListener {
    private View mainView;
    private RecyclerView recyclerView;
    private List<Item> items;
    private RecyclerSearchAdapter recyclerAdapter;
    private boolean verticalScroll=true;
    private ItemClickCallback itemClickCallback;
    private RealmDatabase realmDatabase;
    private FragmentCallback fragmentCallback;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        realmDatabase=new RealmDatabase();
        this.mainView=inflater.inflate(R.layout.fragment_recycler,container,false);
        this.recyclerView=mainView.findViewById(R.id.recycler_view);
        setLayoutManager(recyclerView,verticalScroll);
        //setBasicDivider(recyclerView,verticalScroll);
        setDivider(verticalScroll);

        this.recyclerAdapter=new RecyclerSearchAdapter(items);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new RecyclerListener.TouchListener(this,getContext(),recyclerView));
        return this.mainView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        SearchManager searchManager= (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_search || super.onOptionsItemSelected(item);
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
        fragmentCallback= (FragmentCallback) context;
        this.items=fragmentCallback.getItems();
        this.itemClickCallback=fragmentCallback.getListener();
        super.onAttach(context);
    }

    @Override
    public void onClickListener(View view, int position) {
        itemClickCallback.onCallback(items.get(position), Why.FROM_LIST_VIEW);
    }

    @Override
    public void onLongClickListener(View view, final int position) {
        final PopupMenu popupMenu=new PopupMenu(getActivity(),view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.popup_clipboard) {
                    ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("clip_label", "" + items.get(position).primaryKey);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(getActivity(), "Primary Key Copied To Clip Board", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if(itemId==R.id.popup_delete) {
                    new Dialog().setAccept("Delete").setTitle("User Delete Confirmation").setCallback(new Callback<Object>() {
                        @Override
                        public void onCallback(Object send, boolean accept) {
                            if(accept){
                                if(realmDatabase.delete(itemClickCallback.getRenderingClass(),items.get(position).primaryKey)) {
                                    Toast.makeText(getActivity(), "Item Deleted", Toast.LENGTH_SHORT).show();
                                    items.remove(position);
                                    recyclerAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }).show(getActivity().getSupportFragmentManager(),"");
                    return true;
                }
                else if(itemId==R.id.popup_persist_on_firebase) {
                    /*
                    * TODO: Firebase Persist One Item ...
                    * */
                }
                else if(itemId==R.id.popup_all_persist_on_firebase) {
                    /*
                    * TODO: Firebase Persist All Item ...
                    * */
                }
                /*Send request to itemclickcallback and return fragment then it retrive the realm object for rendering...*/
                else if(itemId==R.id.popup_edit) {
                    try {
                        fragmentCallback.showFragment(itemClickCallback.makeFragment(Why.SETUP),makeObjectFromProxy(realmDatabase.findOne(items.get(position).primaryKey,itemClickCallback.getRenderingClass())),Why.SETUP);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(itemId==R.id.popup_view) {
                    fragmentCallback.showFragment(null,makeObjectFromProxy(realmDatabase.findOne(items.get(position).primaryKey,itemClickCallback.getRenderingClass())),Why.DETAILS);
                }
                return false;
            }
        });
        popupMenu.show();
    }

    /*Cause Proxy can access when the realm connection is open so we need to convert it to object*/
    private Object makeObjectFromProxy(Object proxy) {
        try {
            return Utility.createObjectFromProxy(itemClickCallback.getRenderingClass(),proxy);
        } catch (Exception e) {
            e.printStackTrace();
            showDialog(e.getMessage());
        }
        return null;
    }

    private void showDialog(String message) {
        new SimpleDialog().setMessage(message).show(getActivity().getSupportFragmentManager(),"");
    }

    @Override
    public void onDestroyView() {
        realmDatabase.close();
        super.onDestroyView();
    }
}
