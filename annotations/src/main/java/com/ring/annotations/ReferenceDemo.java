package com.ring.annotations;

/**
 * Created by ring
 * on 16/12/14.
 * <p>
 * 说明说明说明说明
 * {@link com.ring.annotations.option_exception.ExceptionCase }</p>
 * <p>
 * {@linkplain com.ring.annotations.option_exception.ExceptionCase 1212}
 * <p>
 * {@link com.ring.annotations.option_annotation.AnnotationCase }
 * <p>
 * {@link #equals(Object)}
 * <p><p>
 */

public class ReferenceDemo {

    /**
     * 方法说明
     *
     * @param agr1 参数1
     * @param arg2 参数2
     * @return true
     * @see android.widget.Toast
     * @deprecated 已废弃，建议使用{@link #demoMethod2(int, int)} (int)} (int)}
     * <p>
     * {@link ReferenceDemo2#demoMethod(int, int)}
     */
    public boolean demoMethod(int agr1, int arg2) {

        return true;
    }


    public void demoMethod2(int agr1, int arg2) {

    }
}
