package com.ring.mycollections;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ring.mvp.ViewDelegate;
import com.ring.mycollections.recyclerView.SpacesItemDecoration;
import com.ring.tools.custom.ScrollInAnimViewGroup;
import com.ring.tools.utils.DisplayUtils;

/**
 * Created by ring
 * on 16/1/19.
 */
public class MainViewDelegate extends ViewDelegate {

    private RecyclerView rv_list;
    private Toolbar toolbar;

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        toolbar = getView(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(((Toolbar) toolbar));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setLogo(R.mipmap.ic_launcher);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getView(R.id.toolbar).setPadding(0, DisplayUtils.getStatusBarHeight(getActivity().getApplicationContext()), 0, 0);

        FloatingActionButton fab = getView(R.id.fab);
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getPresenter().getRxBus().send(new LongClickEvent());
                return true;
            }
        });
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

        ScrollInAnimViewGroup scrollInAnimViewGroup = getView(R.id.swipeAnimViewGroup);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.adapter_item_test, null);
        View inflate2 = getActivity().getLayoutInflater().inflate(R.layout.adapter_item_test, null);
        View inflate3 = getActivity().getLayoutInflater().inflate(R.layout.adapter_item_test, null);
        scrollInAnimViewGroup.addViews(inflate, inflate2, inflate3);
    }

    public void setListData(RecyclerView.Adapter adapter) {
        rv_list.setAdapter(adapter);
    }

    public void scrollListToPosition(int position) {
        rv_list.scrollToPosition(position);
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

    static class LongClickEvent {
        @Override
        public String toString() {
            return "LongClickEvent";
        }
    }
}
