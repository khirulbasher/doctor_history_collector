package com.lemon.doctorpointcollector.entity.callbacks;

import android.support.v4.app.Fragment;

import com.lemon.androidlibs.utility.item.Item;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickCallback;
import com.lemon.androidlibs.fragment.view.FragmentConversation;
import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.doctorpointcollector.entity.Diseases;
import com.lemon.doctorpointcollector.fragments.setup.DiseaseSetup;
import com.lemon.doctorpointcollector.utility.Utility;

/**
 * Created by lemon on 4/6/2018.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DiseaseClickCallback implements ItemClickCallback {
    private FragmentConversation conversation;

    public DiseaseClickCallback(FragmentConversation conversation) {
        this.conversation=conversation;
    }

    @Override
    public void onCallback(Item item,Why why) {
        conversation.onConversation(this.getClass(), Why.SHOW_TOAST, Utility.getWhyMap(Why.TOAST,"Click On Disease Item:"+item.toString()));
    }

    @Override
    public Class getRenderingClass() {
        return Diseases.class;
    }

    @Override
    public Fragment makeFragment(Why why) throws Exception {
        switch (why) {
            case SETUP:
                return new DiseaseSetup();
                default: throw new Exception("Not a Valid Cause:"+why);
        }
    }

}
