package com.ring.tools.rxbus;

import rx.functions.Action1;

/**
 * Created by ring
 * on 16/1/18.
 */
public class RxBusDemo {

    public void send(){
        RxBus.getInstance().send(new Object());
    }

    public RxBusDemo(){
        RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof Object){
                    //response
                }
            }
        });
    }
}
