package ringares.com.coordinatorlayoutdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ring
 * on 17/4/11.
 */

public class SwipeDismissActivity extends AppCompatActivity {

//    private RecyclerView rv;
//    private DemoAdapter demoAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, SwipeDismissActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dismiss);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);

        View cardView = findViewById(R.id.cardView);

        final SwipeDismissBehavior<View> swipe
                = new SwipeDismissBehavior();

        swipe.setSwipeDirection(
                SwipeDismissBehavior.SWIPE_DIRECTION_ANY);

        swipe.setListener(
                new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {

                    }

                    @Override
                    public void onDragStateChanged(int state) {
                    }
                });

        CoordinatorLayout.LayoutParams coordinatorParams =
                (CoordinatorLayout.LayoutParams) cardView.getLayoutParams();

        coordinatorParams.setBehavior(swipe);

//        ArrayList<DataBean> datas = new ArrayList<>();
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//        datas.add(new DataBean("Info", getString(R.string.text)));
//
//
//
//
//
//
//        rv = (RecyclerView) findViewById(rv);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        rv.setLayoutManager(layoutManager);
//        demoAdapter = new DemoAdapter(R.layout.adapter_demo, datas);
//        rv.setAdapter(demoAdapter);
    }
}
