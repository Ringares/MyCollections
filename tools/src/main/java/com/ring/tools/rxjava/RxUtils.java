package com.ring.tools.rxjava;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ring
 * on 16/1/7.
 */
public class RxUtils {

    /**
     * 在EditText中文字改变后,如果delayTime内没有再次改变触发
     *
     * @param et        EditText 引用
     * @param delayTime 延迟触发的时间 ms
     * @return
     */
    public static Observable<String> onTextChangedDelayed(final EditText et, long delayTime) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String text = s.toString().trim();
                        subscriber.onNext(text);
                    }
                });
            }
        })
                .debounce(delayTime, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<String> onTextChanged(final EditText et) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String text = s.toString().trim();
                        subscriber.onNext(text);
                    }
                });
            }
        });
    }

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
