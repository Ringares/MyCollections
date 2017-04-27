package ringares.com.coordinatorlayoutdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by ring
 * on 17/4/11.
 */

public class TopBtmScrollActivity extends AppCompatActivity {

    private RecyclerView rv;
    private DemoAdapter demoAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, TopBtmScrollActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_btm_scroll);

        ArrayList<DataBean> datas = new ArrayList<>();
        datas.add(new DataBean("Info", getString(R.string.text)));
        datas.add(new DataBean("Info", getString(R.string.text)));
        datas.add(new DataBean("Info", getString(R.string.text)));
        datas.add(new DataBean("Info", getString(R.string.text)));
        datas.add(new DataBean("Info", getString(R.string.text)));
        datas.add(new DataBean("Info", getString(R.string.text)));

        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        demoAdapter = new DemoAdapter(R.layout.adapter_demo, datas);
        rv.setAdapter(demoAdapter);
    }
}
