package com.lemon.androidlibs.utility.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.lemon.androidlibs.utility.recycler.listener.ItemClickListener;


/**
 * Created by lemon on 2/17/2018.
 */

@SuppressWarnings({"unused", "DefaultFileTemplate"})
public class RecyclerListener {
    @SuppressWarnings("WeakerAccess")
    public static class TouchListener implements RecyclerView.OnItemTouchListener {

        public ItemClickListener itemClickListener;
        private GestureDetector gestureDetector;
        private Context context;
        private final RecyclerView recyclerView;

        public TouchListener(final ItemClickListener itemClickListener, Context context, final RecyclerView recyclerView) {
            this.itemClickListener = itemClickListener;
            this.context = context;
            this.recyclerView = recyclerView;

            gestureDetector=new GestureDetector(context, new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return false;
                }

                @Override
                public void onShowPress(MotionEvent e) {

                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    return false;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=getChild(e);
                    if(child!=null)
                        itemClickListener.onLongClickListener(child,recyclerView.getChildLayoutPosition(child));
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    return false;
                }
            });
        }

        private View getChild(MotionEvent e) {
            return recyclerView.findChildViewUnder(e.getX(),e.getY());
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
            View child=getChild(e);
            if(child!=null&&gestureDetector.onTouchEvent(e))
                itemClickListener.onClickListener(child,recyclerView.getChildAdapterPosition(child));
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public static abstract class ClickListener implements ItemClickListener {
        @Override
        public abstract void onClickListener(View view,int position);
    }

    public static abstract class LongClickListener implements ItemClickListener {
        @Override
        public abstract void onLongClickListener(View view,int position);
    }
}
