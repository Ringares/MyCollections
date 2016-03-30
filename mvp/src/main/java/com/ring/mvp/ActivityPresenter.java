package com.ring.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ring.tools.rxjava.RxUtils;
import com.ring.tools.rxjava.rxbus.RxBus;

import java.lang.reflect.ParameterizedType;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by ring
 * on 16/1/19.
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity {
    protected T viewDelegate;
    protected CompositeSubscription subscriptions;
    private RxBus rxBus;

    private Class<T> getDelegateClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public ActivityPresenter() {
        try {
            viewDelegate = getDelegateClass().newInstance();

        } catch (InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (rxBus == null) {
            rxBus = new RxBus();
        }
        viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(viewDelegate.getRootView());
        viewDelegate.initWidget();
        bindEvenListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscriptions = new CompositeSubscription();
        bindRxObservable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        RxUtils.unsubscribe(subscriptions);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }

    /**
     * onCreate()时调用
     */
    protected void bindEvenListener() {
    }

    /**
     * onResume()时调用
     */
    protected void bindRxObservable() {
    }

    public RxBus getRxBus() {
        return rxBus;
    }
}
