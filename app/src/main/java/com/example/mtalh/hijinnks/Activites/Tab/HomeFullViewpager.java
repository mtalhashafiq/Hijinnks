package com.example.mtalh.hijinnks.Activites.Tab;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter.ViewPager_Home_Adapter_Full;
import com.example.mtalh.hijinnks.Models.PostMedia;
import com.example.mtalh.hijinnks.R;

import java.util.ArrayList;

import static com.example.mtalh.hijinnks.Fragments.HomeFragment.media3;

public class HomeFullViewpager extends AppCompatActivity {
    ViewPager home_viewpager_full;
    ArrayList arrayList_get = media3;
    ViewPager_Home_Adapter_Full viewPager_home_adapter_full;
    TextView number_of_item;
    int position;
    int current_postion;
    int total_number_item;
    //    ArrayList mData = new ArrayList();
    ViewPager_Home_Adapter_Full customPagerAdapter;
    private ArrayList<PostMedia> mediaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_full_viewpager);
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        home_viewpager_full = (ViewPager) findViewById(R.id.homeviewpaerfull);
        number_of_item = (TextView) findViewById(R.id.number_of_item);

        position = getIntent().getIntExtra("array_postion", 0);
        mediaArrayList = (ArrayList<PostMedia>) getIntent().getSerializableExtra("array_list");
        customPagerAdapter = new ViewPager_Home_Adapter_Full(mediaArrayList, HomeFullViewpager.this);
        customPagerAdapter.notifyDataSetChanged();

        home_viewpager_full.setAdapter(customPagerAdapter);
        home_viewpager_full.setCurrentItem(position);
        home_viewpager_full.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                total_number_item = mediaArrayList.size();
                number_of_item.setText(position + 1 + " of " + total_number_item);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//customPagerAdapter.notifyDataSetChanged();
//        if (getIntent().getExtras() != null) {
//            position = getIntent().getExtras().getInt("position", 0);
//            mData = (ArrayList<PostMedia>) getIntent().getExtras().getSerializable("dataArray");
//        }

//        number_of_item.setText((position + 1) + " of " + mData.size());
//        if (getIntent().getExtras() != null) {
           /* position = getIntent().getExtras().getInt("array_postion", 0);
            mData = (ArrayList<PostMedia>) getIntent().getExtras().getSerializable("array_list");
//        }*/

//        customPagerAdapter = new CustomPagerAdapter(mContext, mData.get(positiona).getObject());
//        customPagerAdapter.setmListener(Slider_Box.this, holder);

//    home_viewpager_full.setCurrentItem(position, true);


    }





       /* Bundle b = getIntent().getExtras();
        if (null != b) {
            ArrayList arr = b.getStringArrayList("array_list");
            viewPager_home_adapter_full = new ViewPager_Home_Adapter_Full(arr, getApplicationContext());
            viewPager_home_adapter_full.notifyDataSetChanged();
            home_viewpager_full.setAdapter(viewPager_home_adapter_full);

        }
*/
}
