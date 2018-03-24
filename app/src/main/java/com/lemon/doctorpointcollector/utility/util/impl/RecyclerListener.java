package com.lemon.doctorpointcollector.utility.util.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.lemon.doctorpointcollector.utility.util.inf.ClickListener;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class RecyclerListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private ClickListener clickListener;
    private final RecyclerView recyclerView;

    public RecyclerListener(ClickListener clickListener, Context context, final RecyclerView recyclerView) {
        this.clickListener = clickListener;
        this.recyclerView=recyclerView;
        this.gestureDetector=new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                onClick(false,motionEvent);
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                onClick(false,motionEvent);
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                onClick(true,motionEvent);
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
    }

    private void onClick(boolean longClick, MotionEvent motionEvent) {
        View view=recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
        if(longClick)clickListener.onLongClick(view,recyclerView.getChildLayoutPosition(view));
        else clickListener.onClick(view,recyclerView.getChildLayoutPosition(view));
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view=recyclerView.findChildViewUnder(e.getX(),e.getY());
        clickListener.onClick(view,recyclerView.getChildLayoutPosition(view));
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
