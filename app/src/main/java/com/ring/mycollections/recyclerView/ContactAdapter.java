package com.ring.mycollections.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ring.llog.LLog;
import com.ring.mycollections.R;
import com.ring.mycollections.data.Contact;
import com.ring.tools.custom.likeanimation.LikeAnimationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ring
 * on 16/3/25.
 */

public class ContactAdapter<T extends Contact> extends RecyclerView.Adapter {
    private static final int TYPE_LOADING_MORE = -1;
    private final RecyclerView.LayoutManager layoutManager;

    private List<T> dataList = new ArrayList<>();
    private boolean needLoadMore = false;

    public ContactAdapter(@NonNull List<T> dataList) {
        this.dataList = dataList;
        layoutManager = null;
    }

    public void enableLoadMore() {
        needLoadMore = true;
    }

    public void unableLoadMore() {
        needLoadMore = false;
    }

    public void addData(@NonNull List<T> newData) {
        int oldCount = getItemCount();
        dataList.addAll(newData);
        notifyItemRangeInserted(oldCount, dataList.size() - oldCount);
    }

    @Override
    public int getItemViewType(int position) {
        if (needLoadMore) {
            if (position == getItemCount() - 1) {
                return TYPE_LOADING_MORE;
            }
        }

        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();//activity
        switch (viewType) {
            case TYPE_LOADING_MORE:
                View precess = LayoutInflater.from(context).inflate(R.layout.view_process_footer, parent, false);
                return new ProgressViewHolder(precess);
            default:

                View itemView = LayoutInflater.from(context).inflate(R.layout.adapter_item_string, parent, false);
                return new ContactViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_LOADING_MORE:

                break;
            default:
                T contact = dataList.get(position);

                ((ContactViewHolder) holder).tv_name.setText(contact.getName());
//                if (contact.isOnline()) {
//                    ((ContactViewHolder) holder).btn.setText("message");
//                    ((ContactViewHolder) holder).btn.setEnabled(true);
//                } else {
//                    ((ContactViewHolder) holder).btn.setText("offline");
//                    ((ContactViewHolder) holder).btn.setEnabled(false);
//
//                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return needLoadMore ? dataList.size() + 1 : dataList.size();
    }

    public int getItemColumnSpan(int postion) {
        if (getItemViewType(postion) == TYPE_LOADING_MORE) {
            return 2;
        } else {
            return 1;

        }
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tv_name;
        private final LikeAnimationView btn;

        public ContactViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_text);
            btn = (LikeAnimationView) itemView.findViewById(R.id.btn);
            btn.setOnCheckedChangeListener(new LikeAnimationView.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(boolean flag) {
                    LLog.i("LikeAnimationView => " + flag +" "+getAdapterPosition());
                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            LLog.i("itemclick => " + getLayoutPosition());
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }
}
