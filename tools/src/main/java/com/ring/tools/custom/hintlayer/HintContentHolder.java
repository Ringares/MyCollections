package com.ring.tools.custom.hintlayer;

import android.widget.FrameLayout;

public abstract class HintContentHolder extends ContentHolder {
    public FrameLayout.LayoutParams getParentLayoutParams(int width, int height, int gravity) {
        return new FrameLayout.LayoutParams(width, height, gravity);
    }

    public FrameLayout.LayoutParams getParentLayoutParams(int width, int height, int gravity,
                                                          int marginLeft, int marginTop,
                                                          int marginRight, int marginBottom) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height, gravity);
        layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        return layoutParams;
    }
}
