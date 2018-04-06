package com.lemon.doctorpointcollector.entity.callbacks;

import com.lemon.androidlibs.fragment.view.FragmentConversation;
import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.androidlibs.utility.recycler.Item;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickCallback;
import com.lemon.doctorpointcollector.MainActivity;
import com.lemon.doctorpointcollector.entity.MedicalCollege;
import com.lemon.doctorpointcollector.utility.Utility;

/**
 * Created by lemon on 4/6/2018.
 */

public class MedicalCollegeClickCallback implements ItemClickCallback {
    private FragmentConversation fragmentConversation;

    public MedicalCollegeClickCallback(FragmentConversation fragmentConversation) {
        this.fragmentConversation=fragmentConversation;
    }

    @Override
    public void onCallback(Item item, Why why) {
        fragmentConversation.onConversation(MedicalCollegeClickCallback.class,Why.SHOW_TOAST, Utility.getWhyMap(Why.TOAST,"Click On Medical College..."+item.toString()));
    }
}
