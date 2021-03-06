package com.ring.mycollections;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ring.llog.LLog;
import com.ring.mvp.ActivityPresenter;
import com.ring.mycollections.data.Contact;
import com.ring.mycollections.recyclerView.ContactAdapter;
import com.ring.tools.custom.MSGDialog;
import com.ring.tools.recyclerview.EndlessRecyclerViewScrollListener;
import com.ring.tools.utils.SysUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends ActivityPresenter<MainViewDelegate> implements View.OnClickListener {

    private Toolbar toolbar;
    private List<Contact> contactsList = new ArrayList<>();
    private ContactAdapter<Contact> adapter;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        viewDelegate.setOnClickListener(this, R.id.fab);
    }

    @Override
    protected void bindRxObservable() {
        super.bindRxObservable();
        subscriptions.add(getRxBus().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                if (event instanceof MainViewDelegate.LoadMoreDataEvent) {
                    loadMoreData(((MainViewDelegate.LoadMoreDataEvent) event).page);
                } else if (event instanceof MainViewDelegate.LongClickEvent) {
                    SecondActivity.start(MainActivity.this);
                }
            }
        }));
    }

    private void loadMoreData(int page) {
        LLog.i("add page " + page);
        rx.Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        adapter.addData(Contact.createContactsList(10));

                    }
                });
    }

    private void toWeChatScan() {
        try {
            //利用Intent打开微信
            Uri uri = Uri.parse("weixin://dl/scan");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (Exception e) {
            //若无法正常跳转，在此进行错误处理
            Toast.makeText(getApplicationContext(), "无法跳转到微信，请检查您是否安装了微信！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
//            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
            toWeChatScan();

            if (contactsList.size() != 0) {
                contactsList.add(0, new Contact("Tome", true));
                adapter.notifyItemInserted(0);
                viewDelegate.scrollListToPosition(0);
            } else {
                MSGDialog msgDialog = new MSGDialog(this, true);
                msgDialog.setLoadingMsg("loading...");
                msgDialog.showLoading();
                msgDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        contactsList = Contact.createContactsList(10);
                        adapter = new ContactAdapter<>(contactsList);
                        adapter.enableLoadMore();
//                        adapter.setEndlessScrollListener(new ContactAdapter.EndlessScrollListener() {
//                            @Override
//                            public void onLoadMore(int position) {
////                                Observable.timer(1, TimeUnit.SECONDS)
////                                        .observeOn(AndroidSchedulers.mainThread())
////                                        .subscribe(new Action1<Long>() {
////                                            @Override
////                                            public void call(Long aLong) {
////                                                loadMoreData(0);
////                                            }
////                                        });
//                                loadMoreData(0);
//                            }
//                        });
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        ((RecyclerView) viewDelegate.getView(R.id.rv_list)).setLayoutManager(layoutManager);

                        ((RecyclerView) viewDelegate.getView(R.id.rv_list)).addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
                            @Override
                            public void onLoadMore(int page, int totalItemsCount) {
                                loadMoreData(page);
                            }

                            @Override
                            public void onLoadPre(int page, int totalItemsCount) {
                                contactsList.add(0, new Contact("Tome", true));
                                adapter.notifyItemInserted(0);
                            }
                        });
                        MainActivity.this.viewDelegate.setListData(adapter);

                    }
                });
            }
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
