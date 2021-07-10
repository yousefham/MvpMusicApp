package com.yosoftware.musicapp.core.General;

import android.view.View;

public interface OnItemClickListener<T> {
    void onItemClick(View view, int position, T item);
}
