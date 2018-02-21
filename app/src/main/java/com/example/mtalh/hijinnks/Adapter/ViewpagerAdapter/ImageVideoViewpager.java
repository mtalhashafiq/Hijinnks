package com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.mtalh.hijinnks.Models.Model_Image_Video_ViewPager;
import com.example.mtalh.hijinnks.R;

import java.util.ArrayList;

/**
 * Created by CP on 2/7/2018.
 */

public class ImageVideoViewpager extends PagerAdapter {
    boolean video_pause_play = false;
    VideoView videoView;
    private ArrayList<Model_Image_Video_ViewPager> modelArrayList;
    private LayoutInflater inflater;
    private Context context;

    public ImageVideoViewpager(ArrayList<Model_Image_Video_ViewPager> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = LayoutInflater.from(container.getContext()).inflate(R.layout.image_video_viewpager_layout, container, false);

//        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_vp);
        videoView = (VideoView) imageLayout.findViewById(R.id.video_vp);

        if (modelArrayList.get(position).isIs_video()) {
            imageView.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(modelArrayList.get(position).getVideo_model());
            videoView.start();
        } else {
            imageView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(Drawable.createFromPath(modelArrayList.get(position).getImage_model()));
//                imageView.setImageResource(Integer.parseInt(modelArrayList.get(position).getImage_model()));
            }
        }
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (video_pause_play == false) {

                   videoView.start();
                    video_pause_play = true;

                } else {
                  videoView.pause();
                    video_pause_play = false;
                }


                return false;
            }
        });
        Log.d("CURRENTPOSITION: - ", "" + position);
        Toast.makeText(context, "CP :-" + position, Toast.LENGTH_SHORT).show();
        container.addView(imageLayout, 0);

        return imageLayout;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
