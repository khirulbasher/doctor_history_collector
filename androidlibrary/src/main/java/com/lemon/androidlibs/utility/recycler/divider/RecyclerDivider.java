package com.lemon.androidlibs.utility.recycler.divider;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by lemon on 3/9/2018.
 */

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class RecyclerDivider extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    private static final int VERTICAL = LinearLayoutManager.VERTICAL;

    private Drawable divider;
    private Context context;
    private int orientation = VERTICAL;
    private int margin = 6;

    public RecyclerDivider(Context context,@Nullable Drawable divider) {
        this.context = context;
        if (divider == null)
            loadSystemDividerDrawable();
    }

    public RecyclerDivider(Context context, int margin, @Nullable Drawable divider) {
        this.context = context;
        this.margin = margin;
        if (divider == null)
            loadSystemDividerDrawable();
    }

    public RecyclerDivider(Context context, boolean verticalOrientation, @Nullable Drawable divider) {
        this.context = context;
        this.orientation = verticalOrientation ? VERTICAL : HORIZONTAL;
        if (divider == null)
            loadSystemDividerDrawable();
    }

    public RecyclerDivider(Context context, boolean verticalOrientation, int margin, @Nullable Drawable divider) {
        this.context = context;
        this.margin = margin;
        this.orientation = verticalOrientation ? VERTICAL : HORIZONTAL;
        if (divider == null)
            loadSystemDividerDrawable();
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(boolean verticalOrientation) {
        this.orientation = verticalOrientation ? VERTICAL : HORIZONTAL;
    }

    private void loadSystemDividerDrawable() {
        final TypedArray array = context.obtainStyledAttributes(ATTRS);
        divider = array.getDrawable(0);
        array.recycle();
    }

    public Drawable getDivider() {
        return divider;
    }

    public void setDivider(Drawable divider) {
        this.divider = divider;
    }

    public void setDividerTintColor(int color) {
        this.divider.setTint(color);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (orientation == VERTICAL)
            drawVertical(canvas, recyclerView);
        else drawHorizontal(canvas, recyclerView);
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        final int top = recyclerView.getPaddingTop();
        final int bottom = recyclerView.getPaddingBottom();
        final int childCount = recyclerView.getChildCount();
        View view;
        RecyclerView.LayoutParams layoutParams;
        int left, right;
        for (int i = 0; i < childCount; i++) {
            view = recyclerView.getChildAt(i);
            layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            left = view.getRight() + layoutParams.rightMargin;
            right = left + divider.getIntrinsicWidth();
            divider.setBounds(left, top + dpToPixel(margin), right, bottom - dpToPixel(margin));
            divider.draw(canvas);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        final int left = recyclerView.getPaddingLeft();
        final int right = recyclerView.getWidth() - recyclerView.getPaddingRight();
        final int childCount = recyclerView.getChildCount();
        View view;
        RecyclerView.LayoutParams layoutParams;
        int top, bottom;
        for (int i = 0; i < childCount; i++) {
            view = recyclerView.getChildAt(i);
            layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            top = view.getBottom() + layoutParams.bottomMargin;
            bottom = top + divider.getIntrinsicHeight();
            divider.setBounds(left + dpToPixel(margin), top, right - dpToPixel(margin), bottom);
            divider.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(orientation==VERTICAL)
            outRect.set(0,0,0,divider.getIntrinsicHeight());
        else outRect.set(0,0,divider.getIntrinsicWidth(),0);
    }

    private int dpToPixel(int dp) {
        Resources resources = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }
}
