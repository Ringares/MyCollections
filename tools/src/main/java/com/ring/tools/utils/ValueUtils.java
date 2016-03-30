package com.ring.tools.utils;

/**
 * Created by ring
 * on 16/3/29.
 */
public class ValueUtils {

    public static double mapValueFromRangeToRange(double value, double fromLow, double fromHigh, double toLow, double toHigh) {
        return toLow + ((value - fromLow) / (fromHigh - fromLow) * (toHigh - toLow));
    }

    /**
     * 取边界中的有效值
     * @param value
     * @param low
     * @param high
     * @return
     */
    public static double clamp(double value, double low, double high) {
        return Math.min(Math.max(value, low), high);
    }
}
