package com.ring.annotations;

import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by ring
 * on 16/12/14.
 * <p>
 * <p>
 * Once you have created a tree of views, there are typically a few types of
 * common operations you may wish to perform:
 * <ul>
 * <li><strong>Set properties:</strong> for example setting the text of a
 * {@link android.widget.TextView}. </li>
 * <li><strong>Set focus:</strong> Call {@link #demoMethod2(int, int)}.</li>
 * <li><strong>Set up listeners:</strong>You can register such a listener using
 * {@link #demoMethod2(int, int)}. </li>
 * <li><strong>Set visibility:</strong> You can hide or show views using
 * {@link #demoMethod2(int, int)} .</li>
 * </ul>
 * </p>
 *
 */

public class ReferenceDemo2 {

    /**
     * 方法说明
     *
     * @param agr1 参数1
     * @param arg2 参数2
     * @deprecated 已废弃，建议使用{@link #demoMethod2(int, int)} (int)} (int)}
     */
    public void demoMethod(int agr1, int arg2) {

    }


    /**
     * <p>
     * In fact, you can start by just
     * overriding {@link #onDraw(android.graphics.Canvas)}.
     * <table border="2" width="85%" align="center" cellpadding="5">
     * <thead>
     * <tr><th>Category</th> <th>Methods</th> <th>Description</th></tr>
     * </thead>
     * <p>
     * <tbody>
     * <tr>
     * <td rowspan="2">Creation</td>
     * <td>Constructors</td>
     * <td>The second form should parse and apply
     * any attributes defined in the layout file.
     * </td>
     * </tr>
     * <tr>
     * <td><code>{@link #onFinishInflate()}</code></td>
     * <td>Called after a view and all of its children has been inflated
     * from XML.</td>
     * </tr>
     * <p>
     * <tr>
     * <td rowspan="1">Layout</td>
     * <td><code>{@link #onMeasure(int, int)}</code></td>
     * <td>Called to determine the size requirements for this view and all
     * of its children.
     * </td>
     * </tr>
     * <p>
     * </table>
     * </p>
     *
     * @param agr1 arg1
     * @param arg2 arg2
     */
    public void demoMethod2(int agr1, int arg2) {

    }
}
