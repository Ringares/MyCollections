package com.ring.tools.custom.likeanimation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.ring.tools.R;

/**
 * Created by ring
 * on 16/3/29.
 */
public class LikeAnimationView extends FrameLayout implements View.OnClickListener {
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    private boolean isChecked;
    private AnimatorSet animatorSet;
    private ImageView ivStar;
    private CircleView vCircle;
    private DotsView vDotsView;
    private OnCheckedChangeListener onCheckedChangeListener;

    public LikeAnimationView(Context context) {
        super(context);
        init();
    }


    public LikeAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LikeAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LikeAnimationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_like_button, this, true);
        ivStar = (ImageView) inflate.findViewById(R.id.ivStar);
        vCircle = (CircleView) inflate.findViewById(R.id.vCircle);
        vDotsView = (DotsView) inflate.findViewById(R.id.vDotsView);
        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        vCircle.measure(MeasureSpec.makeMeasureSpec((int) (measuredWidth * 0.4), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec((int) (measuredWidth * 0.4), MeasureSpec.EXACTLY));
        vDotsView.measure(MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.AT_MOST));
        ivStar.measure(MeasureSpec.makeMeasureSpec((int) (measuredWidth * 0.4), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec((int) (measuredWidth * 0.4), MeasureSpec.EXACTLY));
    }

    @Override
    public void onClick(View v) {
        isChecked = !isChecked;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(isChecked);
        }
        ivStar.setImageResource(isChecked ? R.drawable.ic_star_rate_on : R.drawable.ic_star_rate_off);

        if (animatorSet != null) {
            animatorSet.end();
//            animatorSet.cancel();
        }

        if (isChecked) {
            ivStar.animate().cancel();
            ivStar.setScaleX(0);
            ivStar.setScaleY(0);
            vCircle.setInnerCircleRadiusProgress(0);
            vCircle.setOuterCircleRadiusProgress(0);

            animatorSet = new AnimatorSet();

            ObjectAnimator outerCircleAnimator = ObjectAnimator.ofFloat(vCircle, CircleView.OUTER_CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
            outerCircleAnimator.setDuration(250);
            outerCircleAnimator.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator innerCircleAnimator = ObjectAnimator.ofFloat(vCircle, CircleView.INNER_CIRCLE_RADIUS_PROGRESS, 0.1f, 1f);
            innerCircleAnimator.setDuration(200);
            innerCircleAnimator.setStartDelay(200);
            innerCircleAnimator.setInterpolator(DECCELERATE_INTERPOLATOR);

            ObjectAnimator starScaleYAnimator = ObjectAnimator.ofFloat(ivStar, "scaleY", 0.2f, 1f);
            starScaleYAnimator.setDuration(350);
            starScaleYAnimator.setStartDelay(250);
            starScaleYAnimator.setInterpolator(OVERSHOOT_INTERPOLATOR);

            ObjectAnimator starScaleXAnimator = ObjectAnimator.ofFloat(ivStar, "scaleX", 0.2f, 1f);
            starScaleXAnimator.setDuration(350);
            starScaleXAnimator.setStartDelay(250);
            starScaleXAnimator.setInterpolator(OVERSHOOT_INTERPOLATOR);


            ObjectAnimator dotsAnimator = ObjectAnimator.ofFloat(vDotsView, DotsView.DOTS_PROGRESS, 0, 1f);
            dotsAnimator.setDuration(900);
            dotsAnimator.setStartDelay(50);
            dotsAnimator.setInterpolator(ACCELERATE_DECELERATE_INTERPOLATOR);


            animatorSet.playTogether(
                    outerCircleAnimator,
                    innerCircleAnimator,
                    starScaleYAnimator,
                    starScaleXAnimator,
                    dotsAnimator);
            animatorSet.start();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ivStar.animate().scaleX(0.7f).scaleY(0.7f).setDuration(150).setInterpolator(DECCELERATE_INTERPOLATOR);
                setPressed(true);
                break;

            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                boolean isInside = (x > 0 && x < getWidth() && y > 0 && y < getHeight());
                if (isPressed() != isInside) {
                    setPressed(isInside);
                    ivStar.animate().scaleX(1).scaleY(1).setInterpolator(DECCELERATE_INTERPOLATOR);
                }
                break;

            case MotionEvent.ACTION_UP:
                ivStar.animate().scaleX(1).scaleY(1).setInterpolator(DECCELERATE_INTERPOLATOR);
                if (isPressed()) {
                    performClick();
                    setPressed(false);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener l) {
        this.onCheckedChangeListener = l;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(boolean flag);
    }
}
