package com.ring.tools.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ring
 * on 15/12/17.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void toast(Context ctx, int stringId) {
        toast(ctx, ctx.getResources().getString(stringId), Toast.LENGTH_SHORT);
    }

    public static void toast(Context ctx, String string) {
        toast(ctx, string, Toast.LENGTH_SHORT);
    }

    public static void toast(Context ctx, String string, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(ctx.getApplicationContext(), string, duration);
        } else {
            mToast.setText(string);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
}
