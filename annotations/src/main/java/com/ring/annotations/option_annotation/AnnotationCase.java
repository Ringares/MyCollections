package com.ring.annotations.option_annotation;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.ring.annotations.R;

/**
 * Created by ring
 * on 15/12/21.
 * <p/>
 * Annotations 可以帮助你写出更有意义的契约，它的表现力要大于注释和文档，而且 Android Studio 可以利用这些 Annotations 帮你检测出潜伏的bug
 */
public class AnnotationCase {

    public void setNonNull(@NonNull Object object) {

    }

    public void testNonNull() {
        setNonNull(null);
    }


    public void setStringRes(@StringRes int id) {
        /**
         * 资源标记
         *
         * @AnimatorRes, @AnimRes, @ArrayRes, @AttrRes, @BoolRes, @ColorRes,
         * @DimenRes, @DrawableRes, @FractionRes, @IdRes, @IntegerRes, @InterpolatorRes, @LayoutRes,
         * @MenuRes, @PluralsRes, @RawRes, @StringRes, @StyleableRes, @StyleRes, @XmlRes
         */

    }

    public void testStringRes() {
        setStringRes(R.string.app_name);
        //setStringRes(123);
    }

    public void setIntegerValue(@IntRange(from = -1, to = 20) int value) {

    }

    public void testIntegerValue() {
        setIntegerValue(300);
        //setStringRes(123);
    }
}
