package com.ring.tools.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ring.llog.LLog;

/**
 * Created by ring
 * on 15/12/15.
 */
public class SysUtils {

    /**
     * 获取进程名,通常是包名
     *
     * @param appContext
     * @return
     */
    public static String getProcessName(Context appContext) {
        String currentProcessName = "";
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                currentProcessName = processInfo.processName;
                break;
            }
        }
        return currentProcessName;
    }

    /**
     * 判断主进程，则对比进程名是否和包名相同即可
     *
     * 判断为某个进程，在mainifest这样这样声明
     * <service android:name=".DroidService" android:process=":service"></service>
     * 其对应的完整进程名为com.droidyue.avoidforceclosedemo:service，我们判断可以使用如下代码
     * "com.droidyue.avoidforceclosedemo:service".equals(processName);
     *
     * @param appContext
     * @return
     */
    public static boolean isMainProcess(Context appContext) {
        return appContext.getPackageName().equals(getProcessName(appContext));
    }

    /**
     * 获取软件版本名
     * versionCode 1
     * versionName "1.0"
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String versionName = "0000";
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取软件code 类似于18这种
     * versionCode 1
     * versionName "1.0"
     */
    public static int getVerCode(Context context) {
        int code = -1;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            code = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        try {
            netInfo = cm.getActiveNetworkInfo();
        } catch (SecurityException e) {
            LLog.e(e.toString());
        }
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }// end isNetworkAvailable

    public static boolean isSDCardAvailable() {
        String mounted = "mounted";// 正常
        String temp = android.os.Environment.getExternalStorageState();
        if (temp.equals(mounted)) {
            return true;
        } else {
            return false;
        }
    }


}
