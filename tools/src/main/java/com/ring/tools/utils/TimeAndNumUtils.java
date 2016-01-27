package com.ring.tools.utils;

import java.text.SimpleDateFormat;

/**
 * Created by ring
 * on 15/12/17.
 */
public class TimeAndNumUtils {

    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy.MM.dd");
    public static SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy.MM.dd.HH");
    public static SimpleDateFormat formatter4 = new SimpleDateFormat("MM-dd HH:mm");

    /**
     * @return 具体时间
     */
    public static String formatDuring(Long itemTime) {
        return formatter.format(itemTime);
    }

    /**
     * @return 若发帖时间离现在很近 将该毫秒数转换为 距离现在多久 否则 转化为具体时间
     */
    public static String formatDuringTime(Long itemTime) {
        long mss = java.util.Calendar.getInstance().getTimeInMillis()
                - itemTime;
        long purehours = mss / (1000 * 60 * 60);
        if (purehours > 3)
            return formatter3.format(itemTime);
        else if (purehours >= 1)
            return purehours + "小时前";
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        if (minutes >= 1)
            return minutes + "分钟前";
        return "刚刚";
    }

    /**
     * 将String转换为整数
     */
    public static int stringTo2Int(String str) {
        // 将整数转化为两位的string
        int num;
        char[] c = str.toCharArray();
        if ("0".equals(c[0])) {
            num = Integer.parseInt(String.valueOf(c[1]));
        } else {
            num = Integer.parseInt(String.valueOf(c));
        }
        return num;
    }

    /**
     * 将整数转化为两位的string
     */
    public static String intTo2String(int num) {
        String result = "";
        if (num < 10) {
            result = "0" + num;
        } else {
            result = num + "";
        }
        // // 将整数转化为两位的string
        // StringBuffer sb = new StringBuffer();
        // if (num < 10) {
        // sb.append("0" + num);
        // } else {
        // sb.append(num + "");
        // }
        return result;
    }
}
