package com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mtalh.hijinnks.Fragments.CarouselFragmentInfoWindow;
import com.example.mtalh.hijinnks.Fragments.Map_Fragment;

public class MyPagerAdapter extends FragmentPagerAdapter implements ViewPager.PageTransformer {
    public final static float BIG_SCALE = 1.3f;
    public final static float SMALL_SCALE = 1.0f;
    private Map_Fragment context;
    private FragmentManager fm;
    private float scale_pager;
    private float mScale = 0.8f;

    public MyPagerAdapter(Map_Fragment context, FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        if (position == Map_Fragment.CURRENT_PAGE)
            scale_pager = BIG_SCALE;
        else
            scale_pager = SMALL_SCALE;

        position = position % Map_Fragment.PAGES;
        return CarouselFragmentInfoWindow.newInstance(context, position, scale_pager);
    }

    @Override
    public int getCount() {
        return Map_Fragment.PAGES * Map_Fragment.LOOPS;
    }

    @Override
    public void transformPage(View page, float position) {

        if(position==0){
            ViewCompat.setScaleY(page, 1);
        }
        else{
            ViewCompat.setScaleY(page, mScale);
        }
    }
}
