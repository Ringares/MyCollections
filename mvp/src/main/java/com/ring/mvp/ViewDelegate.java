package com.ring.mvp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ring
 * on 16/1/19.
 */
public abstract class ViewDelegate implements IDelegate {
    protected final SparseArray<View> mViews = new SparseArray<>();
    protected View rootView;

    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootLayoutId(), container, false);
    }

    public View getRootView() {
        return rootView;
    }

    @Override
    public void initWidget() {
    }

    public void setOnClickListener(View.OnClickListener listener, @IdRes int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    private  <T extends View> T bindView(@IdRes int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T getView(@IdRes int id) {
        return (T) bindView(id);
    }

    protected abstract int getRootLayoutId();

}
