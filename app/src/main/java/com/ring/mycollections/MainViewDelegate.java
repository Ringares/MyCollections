package com.ring.mycollections;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ring.mvp.ViewDelegate;
import com.ring.mycollections.recyclerView.SpacesItemDecoration;
import com.ring.tools.utils.DisplayUtils;

/**
 * Created by ring
 * on 16/1/19.
 */
public class MainViewDelegate extends ViewDelegate {

    private RecyclerView rv_list;

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        ((AppCompatActivity) getActivity()).setSupportActionBar(((Toolbar) getView(R.id.toolbar)));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);

        FloatingActionButton fab = getView(R.id.fab);

        rv_list = getView(R.id.rv_list);

//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return ((ContactAdapter) rv_list.getAdapter()).getItemColumnSpan(position);
//            }
//        });

        RecyclerView.ItemDecoration itemDecoration = new SpacesItemDecoration(
                DisplayUtils.dip2px(getActivity().getApplicationContext(), 8));

        rv_list.addItemDecoration(itemDecoration);
//        rv_list.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount) {
//                getPresenter().getRxBus().send(new LoadMoreDataEvent(page));
//            }
//        });
    }

    public void setListData(RecyclerView.Adapter adapter) {
        rv_list.setAdapter(adapter);
    }

    public void scrollListToPosition(int position) {
        rv_list.scrollToPosition(position);
    }

    protected void onListScrollToEnd() {

    }

    static class LoadMoreDataEvent {
        public int page;

        public LoadMoreDataEvent(int page) {
            this.page = page;
        }

        @Override
        public String toString() {
            return "LoadMoreDataEvent";
        }
    }
}
