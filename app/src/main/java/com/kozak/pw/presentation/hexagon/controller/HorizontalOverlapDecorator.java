package com.kozak.pw.presentation.hexagon.controller;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by mindvalley on 31/05/2016.
 */
public class HorizontalOverlapDecorator extends RecyclerView.ItemDecoration {
    private int mSpaceHeight;
    private int mSpaceWidth;
    private final int mRowSize;

    public HorizontalOverlapDecorator(int rowSize, float mSpaceWidth, float mSpaceHeight) {
        this.mSpaceHeight = (int) mSpaceHeight;
        this.mSpaceWidth = (int) mSpaceWidth;
        this.mRowSize = rowSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        //the number of the items in two rows (one small row and one big row)
        int itemsInTwoRows = mRowSize * 2 - 1;
        //the number of the items in a small row
        int itemsCountInSmallRow = mRowSize - 1;
        //pad the small row by half of the size of an item in the big row
        int smallRow_padding_top_bottom = parent.getHeight() / (mRowSize * 2);
        // resize the items in the small row to make them equal to the items in big row by squeezing them
        int smallRow_padding_item_squeez = parent.getHeight() / (mRowSize * itemsCountInSmallRow);
        //shift the items so the interpolate
        int item_row_shift = -1 * (int) (smallRow_padding_top_bottom / Math.sqrt(3)) + mSpaceWidth;


        //calculate how each item should be shifted
        double offset_up = 0;
        double offset_down = 0;
        double offset_left = 0;
        double offset_right = item_row_shift;

        // the factor the will be used to shift the items in the middle of the small row
        double shift_factor = 1.5 * itemsCountInSmallRow - mRowSize;

        if (((position % itemsInTwoRows) >= 0) && ((position % itemsInTwoRows) < itemsCountInSmallRow) ) {
            offset_up = (shift_factor - ((position % itemsInTwoRows) - 1)) * smallRow_padding_item_squeez;
            offset_down = ((position % itemsInTwoRows) - shift_factor) * smallRow_padding_item_squeez;

        }

        //adjust the offset_up of the first item in the small row to be half of the size of one item in the big row
        if ((position % itemsInTwoRows) == 0) {
            offset_up = smallRow_padding_top_bottom;
        }
        //adjust the offset_bottom of the last item in the small row to be half of the size of one item in the big row
        if ((position % itemsInTwoRows) == itemsCountInSmallRow - 1) {
            offset_down = smallRow_padding_top_bottom;
        }

        outRect.set((int) Math.ceil(offset_left),
                (int) Math.ceil(offset_up + mSpaceHeight / 2),
                (int) Math.ceil(offset_right),
                (int) Math.ceil(offset_down + mSpaceHeight / 2));

    }
}