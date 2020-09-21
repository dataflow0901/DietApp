package com.diet;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

    private final int divWidth;
    private final int divWidth2;
    public RecyclerViewDecoration(int divWidth, int divWidth2)
    {
        this.divWidth = divWidth;
        this.divWidth2 =  divWidth2;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = divWidth;
        outRect.right = divWidth2;
        outRect.left = divWidth2;
    }
}