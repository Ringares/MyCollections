package com.ring.tools.samples;

/**
 * Created by ring
 * on 15/12/15.
 *
 * 利用类的静态初始化会在类被加载时触发的原理,实现比较好的 懒汉式单例
 */
public class SingleInstanceSample {

    public static SingleInstanceSample getInstance() {
        return SingleInstanceSampleHolder.sInstance;
    }

    private static class SingleInstanceSampleHolder {
        public static final SingleInstanceSample sInstance = new SingleInstanceSample();
    }
}
