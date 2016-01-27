package com.ring.tools.rxbus;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by ring
 * on 16/1/18.
 * 单例存在
 */
public class RxBus {

    private RxBus(){}
    public static RxBus getInstance(){
        return SingleInstanceHolder.sInstance;
    }

    private static class SingleInstanceHolder{
        private static RxBus sInstance = new RxBus();
    }


    //private final PublishSubject<Object> _bus = PublishSubject.create();

    // If multiple threads are going to emit events to this
    // then it must be made thread-safe like this instead
    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

    public boolean hasObservers() {
        return _bus.hasObservers();
    }
}
