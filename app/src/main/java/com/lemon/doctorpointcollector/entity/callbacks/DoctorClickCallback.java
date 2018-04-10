package com.lemon.doctorpointcollector.entity.callbacks;

import android.support.v4.app.Fragment;

import com.lemon.androidlibs.fragment.view.FragmentConversation;
import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickCallback;
import com.lemon.doctorpointcollector.MainActivity;
import com.lemon.doctorpointcollector.entity.Doctor;
import com.lemon.doctorpointcollector.fragments.setup.DoctorSetup;
import com.lemon.doctorpointcollector.fragments.setup.MedicalCollegeSetup;
import com.lemon.doctorpointcollector.utility.Utility;

/**
 * Created by lemon on 4/10/2018.
 */

public class DoctorClickCallback implements ItemClickCallback {
    private FragmentConversation fragmentConversation;
    public DoctorClickCallback(FragmentConversation fragmentConversation) {
        this.fragmentConversation=fragmentConversation;
    }

    @Override
    public void onCallback(Item item, Why why) {
        fragmentConversation.onConversation(MedicalCollegeClickCallback.class,Why.SHOW_TOAST, Utility.getWhyMap(Why.TOAST,"Click On Medical College..."+item.toString()));
    }

    @Override
    public Class getRenderingClass() {
        return Doctor.class;
    }

    @Override
    public Fragment makeFragment(Why why) throws Exception {
        switch (why) {
            case SETUP:
                return new DoctorSetup();
            default: throw new Exception("Not a Valid Cause:"+why);
        }
    }
}
