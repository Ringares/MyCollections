package com.ring.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ring
 * on 16/1/19.
 *
 * View 的代理, 通过代理来控制view的显示
 */
public interface IDelegate {
    void create(LayoutInflater i, ViewGroup v, Bundle b);

    View getRootView();

    void initWidget();
}
