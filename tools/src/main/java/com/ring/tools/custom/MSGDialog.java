package com.ring.tools.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ring.tools.R;


public class MSGDialog extends Dialog {

	private View bodyView;
	private LinearLayout ll_loading;
	private TextView tv_msg;

    public MSGDialog(Context context) {
        super(context, R.style.mobo_loading_dialog);
        setCancelable(false);
		setCanceledOnTouchOutside(false);
        init();
    }

    public MSGDialog(Context context, boolean canBeCancel) {
        super(context, R.style.mobo_loading_dialog);
        setCancelable(canBeCancel);
		setCanceledOnTouchOutside(false);
        init();
    }


    private void init() {
		bodyView = LayoutInflater.from(this.getContext()).inflate(R.layout.view_msgdialog_layout, null);
		ll_loading = (LinearLayout) bodyView.findViewById(R.id.ll_loading);
		ll_loading.setVisibility(View.GONE);
		tv_msg = (TextView) bodyView.findViewById(R.id.tv_msg);
        setContentView(bodyView);
	}

	public void setLoadingMsg(String msg){
		tv_msg.setText(msg);
	}


	public void showLoading() {
		ll_loading.setVisibility(View.VISIBLE);
        this.show();
	}

	
}
