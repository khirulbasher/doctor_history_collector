package com.lemon.doctorpointcollector.fragments.setup;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lemon.androidlibs.concurrent.ClientCallback;
import com.lemon.androidlibs.concurrent.CustomRunnable;
import com.lemon.androidlibs.concurrent.Task;
import com.lemon.androidlibs.concurrent.ThreadPool;
import com.lemon.androidlibs.database.realm.RealmDatabase;
import com.lemon.androidlibs.utility.fragment.Dialog;
import com.lemon.androidlibs.utility.fragment.SimpleDialog;
import com.lemon.androidlibs.utility.inf.Callback;
import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.list.FilterAdapter;
import com.lemon.doctorpointcollector.R;
import com.lemon.doctorpointcollector.entity.Clinic;
import com.lemon.doctorpointcollector.entity.Contact;
import com.lemon.doctorpointcollector.entity.Designation;
import com.lemon.doctorpointcollector.entity.Diseases;
import com.lemon.doctorpointcollector.entity.Doctor;
import com.lemon.doctorpointcollector.entity.Hospital;
import com.lemon.doctorpointcollector.entity.MedicalCollege;
import com.lemon.doctorpointcollector.entity.converter.ClinicConverter;
import com.lemon.doctorpointcollector.entity.converter.DesignationConverter;
import com.lemon.doctorpointcollector.entity.converter.DiseaseConverter;
import com.lemon.doctorpointcollector.entity.converter.HospitalConverter;
import com.lemon.doctorpointcollector.entity.converter.MedicalCollegeConverter;
import com.lemon.doctorpointcollector.entity.enumeration.EmployeeType;
import com.lemon.doctorpointcollector.fragments.callback.SetupCallback;
import com.lemon.doctorpointcollector.utility.Utility;

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
    private RealmDatabase realmDatabase;

    private AutoCompleteTextView diseaseAuto;
    private List<Item> diseaseList;
    private FilterAdapter diseaseAdapter;
    private TextView diseaseCount;

    private AutoCompleteTextView clinicAuto;
    private List<Item> clinicList;
    private TextView clinicCount;
    private FilterAdapter clinicAdapter;

    private AutoCompleteTextView hospitalAuto;
    private List<Item> hospitalList;
    private TextView hospitalCount;
    private FilterAdapter hospitalAdapter;

    private AutoCompleteTextView designationAuto;
    private List<Item> designation;
    private FilterAdapter designationAdapter;

    private EditText phone,mail,fb;
    private TextView contactCount;

    private ProgressDialog progressDialog;
    private Spinner division,district;
    private Spinner doctorType;

    private AutoCompleteTextView medicalCollege;
    private FilterAdapter medicalCollegeFilterAdapter;
    private List<Item> mcItems;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.doctor_setup_fragment,container,false);
        realmDatabase=new RealmDatabase();
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        initView(view);

        return view;
    }

    private void clearScr(@Nullable String msg) {
        if(msg!=null)
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        name.setText("");
        diseaseAuto.setText("");
        designationAuto.setText("");
        clinicAuto.setText("");
        hospitalCount.setText("");
        phone.setText("");
        mail.setText("");
        fb.setText("");
    }

    @Override
    public void onDestroyView() {
        if(realmDatabase!=null)
            realmDatabase.close();
        super.onDestroyView();
    }

    private void initView(View view) {
        Object lock=new Object();
        synchronized (lock) {
            ThreadPool threadPool=new ThreadPool(lock);
            threadPool.addRunnable(new CustomRunnable(new Task() {
                @Override
                public void doTask(ClientCallback clientCallback) {
                    RealmDatabase database=new RealmDatabase();
                    final List<Diseases> diseaseDatas = database.findAllList(Diseases.class);
                    diseaseList=new DiseaseConverter().convert(diseaseDatas);/*Cause Another Thread can't access androids context related Classes sucsh that views, widget etc but can access others....*/
                    database.close();
                }
            }));

            threadPool.addRunnable(new CustomRunnable(new Task() {
                @Override
                public void doTask(ClientCallback clientCallback) {
                    RealmDatabase realmDatabase=new RealmDatabase();
                    final List<Clinic> clinics=realmDatabase.findAllList(Clinic.class);
                    clinicList=new ClinicConverter().convert(clinics);
                    realmDatabase.close();
                }
            }));

            threadPool.addRunnable(new CustomRunnable(new Task() {
                @Override
                public void doTask(ClientCallback clientCallback) {
                    RealmDatabase realmDatabase=new RealmDatabase();
                    final List<Hospital> hospitals=realmDatabase.findAllList(Hospital.class);
                    hospitalList=new HospitalConverter().convert(hospitals);
                    realmDatabase.close();
                }
            }));

            threadPool.addRunnable(new CustomRunnable(new Task() {
                @Override
                public void doTask(ClientCallback clientCallback) {
                    RealmDatabase realmDatabase=new RealmDatabase();
                    final List<Designation> designations=realmDatabase.findAllList(Designation.class);
                    designation=new DesignationConverter().convert(designations);
                    realmDatabase.close();
                }
            }));

            threadPool.addRunnable(new CustomRunnable(new Task() {
                @Override
                public void doTask(ClientCallback clientCallback) {
                    RealmDatabase realmDatabase=new RealmDatabase();
                    List<MedicalCollege> medicalColleges=realmDatabase.findAllList(MedicalCollege.class);
                    mcItems=new MedicalCollegeConverter().convert(medicalColleges);
                    realmDatabase.close();
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

        clinicAdapter=new FilterAdapter(clinicList,getLayoutInflater());
        clinicAuto.setAdapter(clinicAdapter);

        hospitalAdapter=new FilterAdapter(hospitalList,getLayoutInflater());
        hospitalAuto.setAdapter(hospitalAdapter);

        designationAdapter=new FilterAdapter(designation,getLayoutInflater());
        designationAuto.setAdapter(designationAdapter);

        medicalCollegeFilterAdapter=new FilterAdapter(mcItems,getLayoutInflater());
        medicalCollege.setAdapter(medicalCollegeFilterAdapter);

        verifyEditOrCreateMode();

        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

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

        manageClinic();

        manageHospital();

        manageContact();

        manageDesignation();

        manageMedicalCollege();

        initSpinners();

        initSaveButton();
    }

    private void update(Doctor doctor) {
        realmDatabase.update(doctor.getId(),doctor,Doctor.class);
        clearScr("Doctor Setup was Updated...");
    }

    private void save(Doctor doctor) {
        doctor.setId(System.currentTimeMillis());
        realmDatabase.persist(doctor);
        clearScr("Doctor setup was saved...");
    }

    private void manageContact() {
        phone=view.findViewById(R.id.contact);
        mail=view.findViewById(R.id.mail);
        fb=view.findViewById(R.id.facebook);
        contactCount=view.findViewById(R.id.contact_count);

        contactCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopup(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        RealmList<Contact> realmList=doctor.getContacts();
                        if(realmList==null)
                            realmList=new RealmList<>();
                        boolean action=false;
                        switch (item.getItemId()) {
                            case R.id.popup_add:
                                if(phone.getText().toString().isEmpty())
                                    return true;
                                Contact contact=new Contact();
                                contact.setPhone(phone.getText().toString());
                                contact.setMail(mail.getText().toString());
                                contact.setFacebookId(fb.getText().toString());
                                realmList.add(contact);
                                phone.setText("");
                                fb.setText("");
                                mail.setText("");
                                action= true;
                                break;
                            case R.id.popup_delete_first_entry:
                                if(realmList.size()>0)
                                    deleteRequest(0,Contact.class.getSimpleName());
                                action= true;
                                break;
                            case R.id.popup_delete_last_entry:
                                if(realmList.size()>0)
                                    deleteRequest(realmList.size()-1,Contact.class.getSimpleName());
                                action= true;
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    private void manageHospital() {
        hospitalAuto=view.findViewById(R.id.hospital_auto);
        hospitalAuto.setThreshold(2);
        hospitalCount=view.findViewById(R.id.hospital_count);
        hospitalCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopup(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        RealmList<Hospital> realmList=doctor.getHospitals();
                        if(realmList==null)
                            realmList=new RealmList<>();
                        boolean action=false;
                        switch (item.getItemId()) {
                            case R.id.popup_add:
                                if(hospitalAuto.getText().toString().isEmpty())
                                    return true;
                                Hospital hospital= (Hospital) realmDatabase.findOne(Long.parseLong(hospitalAuto.getText().toString()),Hospital.class);
                                if(hospital==null){
                                    showAlert("Hospital Not Valid...");
                                    return true;
                                }
                                if(realmList.contains(hospital)){
                                    showAlert("Hospital Already Exist...");
                                    return true;
                                }
                                realmList.add(hospital);
                                clinicAuto.setText("");
                                action= true;
                                break;
                            case R.id.popup_delete_first_entry:
                                if(realmList.size()>0)
                                    deleteRequest(0,Hospital.class.getSimpleName());
                                action= true;
                                break;
                            case R.id.popup_delete_last_entry:
                                if(realmList.size()>0)
                                    deleteRequest(realmList.size()-1,Hospital.class.getSimpleName());
                                action= true;
                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    private void manageClinic() {
        clinicAuto=view.findViewById(R.id.clinic_auto);
        clinicAuto.setThreshold(2);
        clinicCount=view.findViewById(R.id.clinic_count);
        clinicCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopup(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        RealmList<Clinic> realmList=doctor.getClinics();
                        if(realmList==null)
                            realmList=new RealmList<>();
                        boolean action=false;
                        switch (item.getItemId()) {
                            case R.id.popup_add:
                                if(clinicAuto.getText().toString().isEmpty())
                                    return true;
                                Clinic clinic= (Clinic) realmDatabase.findOne(Long.parseLong(clinicAuto.getText().toString()),Clinic.class);
                                if(clinic==null){
                                    showAlert("Clinic Not Valid...");
                                    return true;
                                }
                                if(realmList.contains(clinic)){
                                    showAlert("Clinic Already Exist...");
                                    return true;
                                }
                                realmList.add(clinic);
                                clinicAuto.setText("");
                                action= true;
                                break;
                            case R.id.popup_delete_first_entry:
                                if(realmList.size()>0)
                                    deleteRequest(0,Clinic.class.getSimpleName());
                                action= true;
                                break;
                            case R.id.popup_delete_last_entry:
                                if(realmList.size()>0)
                                    deleteRequest(realmList.size()-1,Clinic.class.getSimpleName());
                                action= true;
                                break;
                        }
                        return false;
                    }
                });
            }
        });
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
                                        deleteRequest(0,Diseases.class.getSimpleName());
                                    action= true;
                                    break;
                                case R.id.popup_delete_last_entry:
                                    if(realmList.size()>0)
                                        deleteRequest(realmList.size()-1,Diseases.class.getSimpleName());
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

    private void deleteRequest(final int position, final String name) {
        new Dialog().setTitle(name+" Delete Operation").setMessage("Are You Sure?").setAccept("Confirm").setCallback(new Callback<Object>() {
            @Override
            public void onCallback(Object send, boolean accept) {
                if(accept) {
                    if(name.equalsIgnoreCase(Diseases.class.getSimpleName()))
                        doctor.getDiseases().remove(position);
                    else if(name.equalsIgnoreCase(Clinic.class.getSimpleName()))
                        doctor.getClinics().remove(position);
                    else if(name.equalsIgnoreCase(Hospital.class.getSimpleName()))
                        doctor.getHospitals().remove(position);
                    else if(name.equalsIgnoreCase(Contact.class.getSimpleName()))
                        doctor.getContacts().remove(position);
                }
            }
        }).show(getActivity().getSupportFragmentManager(),"");
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

    private void manageDesignation() {
        designationAuto=view.findViewById(R.id.designation_auto);
        designationAuto.setThreshold(2);
    }

    private void manageMedicalCollege() {
        medicalCollege=view.findViewById(R.id.mc_auto);
        medicalCollege.setThreshold(2);
    }

    private void initSpinners() {
        division=view.findViewById(R.id.division_spinner);
        district=Utility.initSpinner(division,getActivity(),view);
        doctorType=Utility.initTypeSpinner(view,getActivity(), EmployeeType.values(),"Employee Type");
    }

    private void initSaveButton() {
        Utility.initSaveButton(view, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!name.getText().toString().isEmpty()&&!designationAuto.getText().toString().isEmpty()) {
                    doctor.setName(name.getText().toString());
                    String id=designationAuto.getText().toString();

                    try {
                        if(!id.isEmpty()) {
                            Designation designation= (Designation) realmDatabase.findOne(Long.parseLong(id),Designation.class);
                            if(designation!=null) {
                                doctor.setDesignation(designation);
                            }
                        }
                        id=diseaseAuto.getText().toString();
                        if(!id.isEmpty()) {
                            Diseases diseases= (Diseases) realmDatabase.findOne(Long.parseLong(id),Diseases.class);
                            if(diseases!=null) {
                                if(doctor.getDiseases()==null)
                                    doctor.setDiseases(new RealmList<Diseases>());
                            }
                            doctor.getDiseases().add(diseases);
                        }

                        id=hospitalAuto.getText().toString();
                        if(!id.isEmpty()) {
                            Hospital hospital= (Hospital) realmDatabase.findOne(Long.parseLong(id),Hospital.class);
                            if(hospital!=null) {
                                if(doctor.getHospitals()==null)
                                    doctor.setHospitals(new RealmList<Hospital>());
                                doctor.getHospitals().add(hospital);
                            }
                        }

                        id=clinicAuto.getText().toString();

                        if(!id.isEmpty()) {
                            Clinic clinic= (Clinic) realmDatabase.findOne(Long.parseLong(id),Clinic.class);
                            if(clinic!=null) {
                                if(doctor.getClinics()==null)
                                    doctor.setClinics(new RealmList<Clinic>());
                                doctor.getClinics().add(clinic);
                            }
                        }

                        if(!phone.getText().toString().isEmpty() || !mail.getText().toString().isEmpty() || !fb.getText().toString().isEmpty()) {
                            Contact contact=new Contact();
                            contact.setFacebookId(fb.getText().toString());
                            contact.setMail(mail.getText().toString());
                            contact.setPhone(phone.getText().toString());
                            doctor.getContacts().add(contact);
                        }

                        doctor.setEmployeeType(doctorType.getSelectedItem().toString());
                        id=medicalCollege.getText().toString();
                        MedicalCollege medicalCollege= (MedicalCollege) realmDatabase.findOne(Long.parseLong(id),MedicalCollege.class);
                        if(medicalCollege!=null){
                            if(doctor.getMedicalColleges()==null)
                                doctor.setMedicalColleges(new RealmList<MedicalCollege>());
                            doctor.getMedicalColleges().add(medicalCollege);
                        }

                    } catch (Exception e) {
                        showAlert("Not a Valid input");
                    }

                    if(doctor.getId()==null)
                        save(doctor);
                    else update(doctor);
                }
            }
        });
    }

}
