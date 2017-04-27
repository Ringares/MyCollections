package ringares.com.coordinatorlayoutdemo.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by ring
 * on 17/4/20.
 */

public class ScrollBasedBehavior extends CoordinatorLayout.Behavior {
    private boolean isAnimate;

    public ScrollBasedBehavior() {
    }

    public ScrollBasedBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        System.out.println("-------onStartNestedScroll");
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        System.out.println("-------onNestedPreScroll");
//        consumed[1] = dy; //消耗多少滚动
        if (dy > 0 && !isAnimate && child.getTranslationY() < child.getHeight()) {
            child.setTranslationY(child.getTranslationY() + dy);
        } else if (dy < 0 && !isAnimate && child.getTranslationY() > 0) {
            child.setVisibility(View.VISIBLE);
            if (child.getTranslationY() + dy < 0) {
                child.setTranslationY(0);
            } else {
                child.setTranslationY(child.getTranslationY() + dy);
            }
        }
    }

//    @Override
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        System.out.println("-------onNestedScroll: dyConsumed=" + dyConsumed + " dyUnconsumed=" + dyUnconsumed);
//        if (dyConsumed > 0 && !isAnimate && child.getTranslationY() < child.getHeight()) {
//            child.setTranslationY(child.getTranslationY() + dyConsumed);
//        } else if (dyConsumed < 0 && !isAnimate && child.getTranslationY() > 0) {
//            child.setVisibility(View.VISIBLE);
//            if (child.getTranslationY() + dyConsumed < 0) {
//                child.setTranslationY(0);
//            } else {
//                child.setTranslationY(child.getTranslationY() + dyConsumed);
//            }
//        }
//    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        System.out.println("-------onStopNestedScroll");
        if (child.getTranslationY() < child.getHeight() / 2) {
            changeState(child, 0);
        } else {
            changeState(child, child.getHeight());
        }
    }


    private void changeState(final View view, final int scrollY) {
        ViewPropertyAnimator animator = view.animate().translationY(scrollY).setInterpolator(new FastOutSlowInInterpolator()).setDuration(200 * scrollY / view.getHeight());
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (view.getTranslationY() == view.getHeight()) {
                    view.setVisibility(View.GONE);
                }
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                view.setTranslationY(scrollY);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }
}
