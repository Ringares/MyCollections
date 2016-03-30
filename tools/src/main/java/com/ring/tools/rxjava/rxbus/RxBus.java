package com.ring.tools.rxjava.rxbus;

import com.ring.llog.LLog;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by ring
 * on 16/3/28.
 */
public class RxBus {

    //private final PublishSubject<Object> _bus = PublishSubject.create();

    // If multiple threads are going to emit events to this
    // then it must be made thread-safe like this instead
    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        LLog.d("RxBus send: " + o.toString());
        _bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

    public boolean hasObservers() {
        return _bus.hasObservers();
    }
}