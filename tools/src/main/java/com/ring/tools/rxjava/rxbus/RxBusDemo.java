package com.ring.tools.rxjava.rxbus;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by ring
 * on 16/1/18.
 */
public class RxBusDemo {

    public void send() {
        RxBusSingleton.getInstance().send(new Object());
    }

    public RxBusDemo() {
        RxBusSingleton.getInstance().toObserverable()
                .compose(new Observable.Transformer<Object, Object>() {
                    @Override
                    public Observable<Object> call(Observable<Object> objectObservable) {
                        return null;
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof Object) {
                            //response
                        }
                    }
                });
    }
}
