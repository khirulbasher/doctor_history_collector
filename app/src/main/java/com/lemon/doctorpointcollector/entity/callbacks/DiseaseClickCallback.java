package com.lemon.doctorpointcollector.entity.callbacks;

import com.lemon.androidlibs.utility.recycler.Item;
import com.lemon.androidlibs.utility.recycler.listener.ItemClickCallback;
import com.lemon.androidlibs.fragment.view.FragmentConversation;
import com.lemon.androidlibs.utility.enumeration.Why;
import com.lemon.doctorpointcollector.utility.Utility;

/**
 * Created by lemon on 4/6/2018.
 */

public class DiseaseClickCallback implements ItemClickCallback {
    private FragmentConversation conversation;

    public DiseaseClickCallback(FragmentConversation conversation) {
        this.conversation=conversation;
    }

    @Override
    public void onCallback(Item item) {
        conversation.onConversation(this.getClass(), Why.SHOW_TOAST, Utility.getWhyMap(Why.TOAST,"Click On Disease Item:"+item.toString()));
    }
}
