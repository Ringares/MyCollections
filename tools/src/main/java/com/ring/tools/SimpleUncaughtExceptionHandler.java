package com.ring.tools;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by ring
 * on 15/12/15.
 * 全局异常
 *
 * 使用:
 * 在Application的onCreate()中
 * Thread.setDefaultUncaughtExceptionHandler(new SimpleUncaughtExceptionHandler());
 */
public class SimpleUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String LOGTAG = "ExceptionHandler";

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //读取stacktrace信息
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        ex.printStackTrace(printWriter);
        String errorReport = result.toString();
        Log.i(LOGTAG, "uncaughtException errorReport=" + errorReport);

        /**收集完异常后,可以选择干掉进程*/
        //android.os.Process.killProcess(android.os.Process.myPid());
    }
}

