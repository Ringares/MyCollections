package ringares.com.coordinatorlayoutdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by ring
 * on 17/4/11.
 */

public class TopCollapsingActivity extends AppCompatActivity {

    private RecyclerView rv;
    private DemoAdapter demoAdapter;
    private CollapsingToolbarLayout collapsingToolBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, TopCollapsingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_collapsing);

        ArrayList<DataBean> datas = new ArrayList<>();
        datas.add(new DataBean("Info", getString(R.string.text)));
        datas.add(new DataBean("Info", getString(R.string.text)));
        datas.add(new DataBean("Info", getString(R.string.text)));

        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);
        demoAdapter = new DemoAdapter(R.layout.adapter_demo, datas);
        rv.setAdapter(demoAdapter);

        collapsingToolBar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolBar);
        collapsingToolBar.setTitle("CollapsingToolbarLayout");

        ImageView iv_image = (ImageView) findViewById(R.id.iv_image);
        iv_image.setBackground(getResources().getDrawable(R.drawable.bg));
//        Bitmap bg_bitmap = drawableToBitmap(iv_image.getDrawable());
        Bitmap bg_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        Palette.from(bg_bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int bgColor = palette.getDarkVibrantColor(0xff000000);
                int titleColor = palette.getLightVibrantColor(0xffffffff);

                collapsingToolBar.setContentScrimColor(bgColor);
                collapsingToolBar.setCollapsedTitleTextColor(titleColor);
                collapsingToolBar.setExpandedTitleColor(titleColor);
            }
        });

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        //canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);

        return bitmap;

    }
}
