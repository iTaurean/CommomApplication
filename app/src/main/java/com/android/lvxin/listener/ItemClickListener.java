package com.android.lvxin.listener;

import android.view.View;

/**
 * @ClassName: ItemClickListener
 * @Description: the click listener of the RecyclerView's item
 * @Author: lvxin
 * @Date: 12/1/15 14:42
 */
public interface ItemClickListener {
    void onItemClick(View view, int position);
}
