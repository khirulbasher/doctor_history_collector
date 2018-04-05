package com.lemon.doctorpointcollector;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lemon.androidlibs.utility.recycler.Item;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickListener;
import com.lemon.androidlibs.fragment.view.FragmentCallback;
import com.lemon.androidlibs.fragment.view.list.RecyclerViewFragment;
import com.lemon.androidlibs.concurrent.ClientCallback;
import com.lemon.androidlibs.concurrent.ClientHandler;
import com.lemon.androidlibs.concurrent.Converter;
import com.lemon.androidlibs.concurrent.Task;
import com.lemon.androidlibs.concurrent.TaskManager;
import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.doctorpointcollector.entity.Diseases;
import com.lemon.doctorpointcollector.entity.converter.DiseaseConverter;
import com.lemon.doctorpointcollector.fragments.setup.DiseaseSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@SuppressWarnings({"unchecked", "ControlFlowStatementWithoutBraces", "unused", "SameParameterValue"})
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ClientCallback,FragmentCallback {

    private Handler handler;
    private List<Item> itemList;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new ClientHandler(this);
        Realm.init(this);
        RealmConfiguration realmConfiguration=new RealmConfiguration.Builder().name("doctors_point.realm").build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment=null;
        switch (id) {
            case R.id.nav_action_disease_setup:
                fragment=new DiseaseSetup();
                break;
            case R.id.nav_disease_list:
                fetchAll(new DiseaseConverter(),Diseases.class,"Disease List");
        }

        if(fragment!=null)
            changeFragment(fragment);

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private <T> void fetchAll(final Converter<T,Item> converter, final Class<T> entityClass, String title) {
        new TaskManager(handler, new Task() {
            @Override
            public void doTask(ClientCallback clientCallback) {
                RealmDatabase realmDatabase=new RealmDatabase();
                try {
                    final List<T> tList=realmDatabase.findAllList(entityClass);
                    List<Item> itemList=new ArrayList<>();
                    for(T t:tList)
                        itemList.add(converter.convert(t));
                    clientCallback.onPrepareCallback(itemList);
                } finally {
                    realmDatabase.close();
                }
            }
        }).start();
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.replace(R.id.main_content,fragment).commit();
    }

    @Override
    public void onCallback(Object obj, int code) {

    }

    @Override
    public void onCallback(List<Map<String, Object>> maps, int code) {

    }

    @Override
    public void onPrepareCallback(List<Item> items) {
        this.itemList=items;
        changeFragment(new RecyclerViewFragment());
    }


    @Override
    public List<Item> getItems() {
        return itemList;
    }

    @Override
    public ItemClickListener getListener() {
        return new ItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "Click On Position"+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickListener(View view, int position) {

            }
        };
    }
}
