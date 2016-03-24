package com.ring.mycollections;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ring.llog.LLog;
import com.ring.mvp.ActivityPresenter;
import com.ring.tools.custom.MSGDialog;
import com.ring.tools.utils.SysUtils;

public class MainActivity extends ActivityPresenter<MainViewDelegate> implements View.OnClickListener {

    private Toolbar toolbar;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.fab, R.id.btn_click);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (v.getId() == R.id.btn_click) {
            MSGDialog msgDialog = new MSGDialog(this,true);
            msgDialog.setLoadingMsg("loading...");
            msgDialog.showLoading();
        }

        LLog.i("info");
        LLog.i("tag", "234");
        LLog.d("tag", "234", this, toolbar);
        LLog.json("{\"status\":1020,\"desc\":\"NO_RESULT\"}");
        LLog.w(SysUtils.getVerCode(MainActivity.this));
        LLog.w(SysUtils.getVerName(MainActivity.this));
        LLog.w(SysUtils.isNetworkAvailable(MainActivity.this));
        LLog.w(SysUtils.isSDCardAvailable());
        LLog.e(viewDelegate.getRootView().getContext());
    }
}
