package com.ring.tools.custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.ring.tools.R;


/**
 * 显示自定义toast
 * Created by ring
 * on 16/3/9.
 */
public class CustomToast {

    private static Toast mCustomToast;

    /**
     * @param ctx
     * @param text
     * @param duration
     * @param isRight  自定义的Toast正确和错误的提示用两种颜色区分
     */
    public static void toast(Context ctx, String text, int duration, boolean isRight) {
        Resources resources = ctx.getResources();
        if (mCustomToast == null) {
            mCustomToast = new Toast(ctx);
            mCustomToast.setGravity(Gravity.BOTTOM, 0, resources.getDisplayMetrics().heightPixels / 8);
        }

        TextView textView = new TextView(ctx);

        int padding = dip2px(ctx, 10);
        textView.setMaxWidth((int) (resources.getDisplayMetrics().widthPixels / 1.2));
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundDrawable(isRight ?
                resources.getDrawable(R.drawable.shape_bg_toast_green) : resources.getDrawable(R.drawable.shape_bg_toast_red));

        mCustomToast.setDuration(duration);
        mCustomToast.setView(textView);
        mCustomToast.show();
    }

    public static void toast(Context ctx, String text, boolean isRight) {
        toast(ctx, text, Toast.LENGTH_LONG, isRight);
    }

    public static void toast(Context ctx, int stringId, boolean isRight) {
        toast(ctx, ctx.getResources().getString(stringId), Toast.LENGTH_LONG, isRight);
    }

    private static float scale = -1;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private static int dip2px(Context context, float dpValue) {
        if (scale == -1) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dpValue * scale + 0.5f);
    }
}

