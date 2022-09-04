package com.pam.chatlib.interfaces;

import android.view.View;

public interface AdapterClickListener {
    void onItemClick(int pos, Object item, View view);

    boolean onLongItemClick(int pos, Object item, View view);
}
