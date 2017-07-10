package ringares.com.coordinatorlayoutdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by ring
 * on 17/4/11.
 */

public class TopNavBarHideActivity extends AppCompatActivity {

    private RecyclerView rv;
    private DemoAdapter demoAdapter;
    private View rootView;

    public static void start(Context context) {
        Intent starter = new Intent(context, TopNavBarHideActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_nav_hide);

        ArrayList<DataBean> datas = new ArrayList<>();
        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));

        rootView = findViewById(R.id.main_content);
        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        demoAdapter = new DemoAdapter(R.layout.adapter_demo, datas);
        rv.setAdapter(demoAdapter);

        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getApplicationContext(), "pos: " + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                if (e.getAction() == MotionEvent.ACTION_UP && rv.findChildViewUnder(e.getX(), e.getY()) == null) {
                    System.out.println("onInterceptTouchEvent");

                    return true;
                } else {
                    return super.onInterceptTouchEvent(rv, e);
                }
            }
        });


    }
}
