package ringares.com.coordinatorlayoutdemo.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import ringares.com.coordinatorlayoutdemo.R;

/**
 * Created by ring
 * on 17/4/19.
 */

public class TopBtmScrollBehavior extends CoordinatorLayout.Behavior {

    private int id;

    public TopBtmScrollBehavior() {
    }

    public TopBtmScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.CustomBehaviorStyle);
        id = typedArray.getResourceId(R.styleable.CustomBehaviorStyle_anchor_id, -1);
        typedArray.recycle();
    }


    /**
     *
     * @param parent
     * @param child
     * @param dependency 被依赖的 view
     * @return true 如果 child 依赖的 view 是 dependency
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //确定依赖 class 的方式
        //return dependency.getClass() == AppBarLayout.class;

        //灵活的 xml id 的方式
        return dependency.getId() == id;
    }

    /**
     * @param parent
     * @param child
     * @param dependency
     * @return true 如果 child 的大小和位置被 behavior 改变了
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setTranslationY(-dependency.getTop());
        return true;
    }
}
