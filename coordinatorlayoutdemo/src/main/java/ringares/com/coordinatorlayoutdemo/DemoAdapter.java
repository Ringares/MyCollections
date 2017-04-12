package ringares.com.coordinatorlayoutdemo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ring
 * on 17/4/11.
 */

public class DemoAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {


    public DemoAdapter(int layoutResId, List<DataBean> data) {
        super(layoutResId, data);
    }

    public DemoAdapter(List data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.tv_text, item.text);
        helper.setText(R.id.tv_title, item.title);
    }

}
