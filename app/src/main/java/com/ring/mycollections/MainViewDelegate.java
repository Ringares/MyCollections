package com.ring.mycollections;

import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.ring.mvp.ViewDelegate;

/**
 * Created by ring
 * on 16/1/19.
 */
public class MainViewDelegate extends ViewDelegate{
    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        TextView tv = getView(R.id.tv);
        tv.setText("1212121");

        FloatingActionButton fab =  getView(R.id.fab);
    }


}
