package com.ring.annotations.option_annotation;

import android.Manifest;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.StringRes;

import com.ring.annotations.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ring
 * on 15/12/21.
 * <p/>
 * Annotations 可以帮助你写出更有意义的契约，它的表现力要大于注释和文档，而且 Android Studio 可以利用这些 Annotations 帮你检测出潜伏的bug
 */
public class AnnotationCase {

    /********************************************************************
     * Nullness Annotation
     */
    public void setNonNull(@NonNull Object object) {}
    public void setNullable(@Nullable Object object) {}

    public void testNonNull() {
        setNonNull(null);
    }

    /********************************************************************
     * Resource Annotations
     * * @AnimatorRes, @AnimRes, @ArrayRes, @AttrRes, @BoolRes, @ColorRes,
     * * @DimenRes, @DrawableRes, @FractionRes, @IdRes, @IntegerRes, @InterpolatorRes, @LayoutRes,
     * * @MenuRes, @PluralsRes, @RawRes, @StringRes, @StyleableRes, @StyleRes, @XmlRes
     */

    public void setStringRes(@StringRes int id) {}

    public void testStringRes() {
        setStringRes(R.string.app_name);
        setStringRes(123);
    }
    /********************************************************************/



    /********************************************************************
     * Value Constraint Annotations
     * * @IntRange, @FloatRange, @Size
     */
    public void setIntegerValue(@IntRange(from = -1, to = 20) int value) {}

    public void testIntegerValue() {
        setIntegerValue(300);
    }




    public void setStringArrayValue(@Size(min = 2, max = 6) String[] strings) {}

    public void testStringArrayValue() {
        String[] stringArray = new String[1];
        setStringArrayValue(stringArray);
    }
    /********************************************************************/



    /********************************************************************
     * Thread Annotations
     * * @UiThread, @MainThread, @WorkerThread, @BinderThread
     * 一般用于方法和构造上
     */


    /********************************************************************/



    /********************************************************************
     * Permission Annotations
     * * @RequiresPermission
     */
    @RequiresPermission(Manifest.permission.SET_WALLPAPER)
    public void setWallPaper() {}

    public void testSetWallPaper() {
        setWallPaper();
    }



    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    public void coyFile() {}

    public void testcoyFile() {
        coyFile();
    }

    /********************************************************************/



    /********************************************************************
     * CheckResults Annotations
     * * @CheckResult
     */
    @CheckResult
    public boolean checkResult(){return false;}

    public void testCheckResult(){
        checkResult();
    }
    /********************************************************************/

    /********************************************************************
     * CallSuper Annotations
     * * @CallSuper
     */
    public static class View{
        @CallSuper
        public void onDraw(){
        }
    }

    public static class childView extends View{
        @Override
        public void onDraw(){
        }
    }
    /********************************************************************/


    /********************************************************************
     * Enumerated Annotations
     * * @IntDef
     */
    //Define the list of accepted constants
    @IntDef(flag = true, value = {NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS})
    //Tell the compiler not to store annotation data in the .class file
    @Retention(RetentionPolicy.SOURCE)
    //Declare the NavigationMode annotation
    public @interface NavigationMode {
    }

    //Declare the constants
    public static final int NAVIGATION_MODE_STANDARD = 0x001;
    public static final int NAVIGATION_MODE_LIST = 0x002;
    public static final int NAVIGATION_MODE_TABS = 0x003;

    //Attach the annotation
    public void setNavigationMode(@NavigationMode int mode) {
    }

    public void testSetNavigationMode() {
        setNavigationMode(NAVIGATION_MODE_LIST | NAVIGATION_MODE_STANDARD);
    }

    /********************************************************************/

}
