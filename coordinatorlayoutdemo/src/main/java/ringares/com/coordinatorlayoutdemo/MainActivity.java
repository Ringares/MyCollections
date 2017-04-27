package ringares.com.coordinatorlayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * https://guides.codepath.com/android/Design-Support-Library
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_1:
                TopNavBarHideActivity.start(this);
                break;

            case R.id.btn_2:
                TopCollapsingActivity.start(this);
                break;

            case R.id.btn_3:
                TopBtmScrollActivity.start(this);
                break;

            case R.id.btn_4:
                TopBtmScrollActivity2.start(this);
                break;
        }
    }
}
