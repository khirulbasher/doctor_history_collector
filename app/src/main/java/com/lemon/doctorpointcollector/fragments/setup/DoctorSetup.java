package com.lemon.doctorpointcollector.fragments.setup;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lemon.androidlibs.concurrent.ClientCallback;
import com.lemon.androidlibs.concurrent.CustomRunnable;
import com.lemon.androidlibs.concurrent.Task;
import com.lemon.androidlibs.concurrent.ThreadPool;
import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.androidlibs.utility.fragment.SimpleDialog;
import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.list.FilterAdapter;
import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.entity.Diseases;
import com.lemon.doctorpointcollector.entity.Doctor;
import com.lemon.doctorpointcollector.entity.converter.DiseaseConverter;
import com.lemon.doctorpointcollector.fragments.callback.SetupCallback;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal", "SynchronizationOnLocalVariableOrMethodParameter", "UnusedAssignment"})
public class DoctorSetup extends Fragment {
    private EditText name;
    private View view;
    private SetupCallback setupCallback;
    private Doctor doctor;
    private Button add;
    private Button sub;/*SHow on Recycler View and give Access to delete item...*/
    private AutoCompleteTextView diseaseAuto,clinicAuto;

    private RealmDatabase realmDatabase;
    private List<Item> diseaseList;
    private FilterAdapter diseaseAdapter;
    private TextView diseaseCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.doctor_setup_fragment,container,false);
        realmDatabase=new RealmDatabase();
        initView(view);

        return view;
    }

    private void clearScr(@Nullable String msg) {
        if(msg!=null)
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        name.setText("");
    }

    @Override
    public void onDestroyView() {
        if(realmDatabase!=null)
            realmDatabase.close();
        super.onDestroyView();
    }

    private void initView(View view) {
        verifyEditOrCreateMode();
        Object lock=new Object();
        synchronized (lock) {
            ThreadPool threadPool=new ThreadPool(lock);
            threadPool.addRunnable(new CustomRunnable(new Task() {
                @Override
                public void doTask(ClientCallback clientCallback) {
                    RealmDatabase database=new RealmDatabase();
                    final List<Diseases> diseaseDatas = database.findAllList(Diseases.class);
                    diseaseList=new DiseaseConverter().convert(diseaseDatas);
                    database.close();
                }
            }));
            threadPool.start();

            doBeforeLockObject();

            try {
                if(!threadPool.isInterrupted())
                    lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*Done after all list Ready...*/
        diseaseAdapter=new FilterAdapter(diseaseList,getLayoutInflater());
        diseaseAuto.setAdapter(diseaseAdapter);

    }

    private void verifyEditOrCreateMode() {
        if(doctor==null)
            doctor=new Doctor();
        else renderEditMode();
    }

    private void renderEditMode() {

    }

    private void doBeforeLockObject() {
        name=view.findViewById(R.id.doc_name);
        manageDisease();
    }

    private void manageDisease() {
        diseaseAuto=view.findViewById(R.id.disease_auto);
        diseaseAuto.setThreshold(2);
        diseaseCount=view.findViewById(R.id.disease_count);
        diseaseCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopup(new PopupMenu.OnMenuItemClickListener(){

                    @SuppressLint("SetTextI18n")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        try {
                            RealmList<Diseases> realmList=doctor.getDiseases();
                            if(realmList==null)
                                realmList=new RealmList<>();
                            boolean action=false;
                            switch (item.getItemId()) {
                                case R.id.popup_add:
                                    if(diseaseAuto.getText().toString().isEmpty())
                                        return true;
                                    Diseases diseases= (Diseases) realmDatabase.findOne(Long.parseLong(diseaseAuto.getText().toString()),Diseases.class);
                                    if(diseases==null){
                                        showAlert("Disease Not Valid...");
                                        return true;
                                    }
                                    if(realmList.contains(diseases)){
                                        showAlert("Disease Already Exist...");
                                        return true;
                                    }
                                    realmList.add(diseases);
                                    diseaseAuto.setText("");
                                    action= true;
                                    break;
                                case R.id.popup_delete_first_entry:
                                    if(realmList.size()>0)
                                        realmList.remove(0);
                                    action= true;
                                    break;
                                case R.id.popup_delete_last_entry:
                                    if(realmList.size()>0)
                                        realmList.remove(realmList.size()-1);
                                    action= true;
                                    break;
                            }
                            diseaseCount.setText(""+realmList.size());
                            doctor.setDiseases(realmList);
                            return action;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            showAlert(e.getMessage());
                        }
                        return false;
                    }
                });
            }
        });
    }

    private void showAlert(String message) {
        new SimpleDialog().setMessage(message).show(getActivity().getSupportFragmentManager(),"");
    }

    private void setPopup(PopupMenu.OnMenuItemClickListener onMenuItemClickListener) {
        PopupMenu popupMenu=new PopupMenu(getActivity(),diseaseAuto);
        popupMenu.inflate(R.menu.auto_complete_menu);
        popupMenu.setOnMenuItemClickListener(onMenuItemClickListener);
        popupMenu.show();
    }

}
