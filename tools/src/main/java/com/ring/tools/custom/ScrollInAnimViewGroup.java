package com.ring.tools.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;

/**
 * Created by ring
 * on 16/5/13.
 */
public class ScrollInAnimViewGroup extends FrameLayout {

    private boolean isJustAdded;
    private ArrayList<View> viewList = new ArrayList<>();

    public ScrollInAnimViewGroup(Context context) {
        super(context);
    }

    public ScrollInAnimViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollInAnimViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addViews(View... views) {
        for (int i = 0; i < views.length; i++) {
            this.addView(views[i]);
            viewList.add(views[i]);

        }
        isJustAdded = true;
        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                postDelayed(animRunnable, 0);
                ScrollInAnimViewGroup.this.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int totalHeight = 0;
        measureChildren(widthSpec, heightSpec);
        for (int i = 0; i < getChildCount(); i++) {
            totalHeight += getChildAt(i).getMeasuredHeight();
        }
        setMeasuredDimension(MeasureSpec.getSize(widthSpec), totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                childAt.layout(l, b, r, b + childAt.getMeasuredHeight());
            }
    }


    Runnable animRunnable = new Runnable() {
        @Override
        public void run() {
            AnimatorSet animatorSet = new AnimatorSet();
            ArrayList<Animator> animators = new ArrayList<>();
            int offset = getMeasuredHeight();
            for (int i = 0; i < viewList.size(); i++) {
                View target = viewList.get(i);
                ObjectAnimator viewAnim = ObjectAnimator.ofFloat(target, "translationY", 0, -offset).setDuration(200);
                viewAnim.setStartDelay(100 * i);
                animators.add(viewAnim);
                offset-=target.getMeasuredHeight();
            }
            animatorSet.playTogether(animators);
            animatorSet.start();
        }
    };
}
