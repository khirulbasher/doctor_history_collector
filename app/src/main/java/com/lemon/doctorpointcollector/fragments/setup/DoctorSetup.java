package com.lemon.doctorpointcollector.fragments.setup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lemon.androidlibs.concurrent.ClientCallback;
import com.lemon.androidlibs.concurrent.CustomRunnable;
import com.lemon.androidlibs.concurrent.Task;
import com.lemon.androidlibs.concurrent.ThreadPool;
import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.list.FilterAdapter;
import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.entity.Diseases;
import com.lemon.doctorpointcollector.entity.Doctor;
import com.lemon.doctorpointcollector.entity.converter.DiseaseConverter;
import com.lemon.doctorpointcollector.fragments.callback.SetupCallback;

import java.util.List;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal", "SynchronizationOnLocalVariableOrMethodParameter"})
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
        name=view.findViewById(R.id.doc_name);
        diseaseAuto=view.findViewById(R.id.disease_auto);
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
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        diseaseAdapter=new FilterAdapter(diseaseList,getLayoutInflater());
        diseaseAuto.setAdapter(diseaseAdapter);
        diseaseAuto.setThreshold(2);
    }

}
