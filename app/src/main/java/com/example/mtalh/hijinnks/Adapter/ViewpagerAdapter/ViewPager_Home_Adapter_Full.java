package com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mtalh.hijinnks.Models.PostMedia;
import com.example.mtalh.hijinnks.R;

import java.util.ArrayList;

import tcking.github.com.giraffeplayer2.VideoView;

/**
 * Created by CP on 2/8/2018.
 */

public class ViewPager_Home_Adapter_Full extends PagerAdapter {

    boolean video_pause_play = false;
    VideoView videoView;
    RelativeLayout image_video_layout;
    ImageView imageview_video_thumbanil;
    private ArrayList<PostMedia> modelArrayList;
    private LayoutInflater inflater;
    private Context context;
//    ArrayList modelArrayList = new ArrayList();
    public ViewPager_Home_Adapter_Full(ArrayList<PostMedia> modelArrayList, Context context) {
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
    public Object instantiateItem(ViewGroup container, final int position) {
        View imageLayout = LayoutInflater.from(container.getContext()).inflate(R.layout.list_viewpager_home, container, false);

//        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_vp_home);
        videoView = (VideoView) imageLayout.findViewById(R.id.video_vp_home);
        image_video_layout = (RelativeLayout) imageLayout.findViewById(R.id.image_video_layout);
        imageview_video_thumbanil = (ImageView) imageLayout.findViewById(R.id.image_for_video_thumbnail);


        if (modelArrayList.get(position).getType().equals("Video")) {
            imageView.setVisibility(View.GONE);
            image_video_layout.setVisibility(View.VISIBLE);
            videoView.setVideoPath(String.valueOf(Uri.parse(modelArrayList.get(position).getMedia_url())));
            videoView.setFingerprint(position);




//            imageview_video_thumbanil.setImageBitmap(modelArrayList.get(position).getBitmap_video());

           // Glide.with(context).load(modelArrayList.get(position).getVideo_thumbnail()).into(imageview_video_thumbanil);

//            videoView.start();
          /*  videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if (video_pause_play == false) {
                        imageview_video_thumbanil.setVisibility(View.GONE);
                        video_pause_play = true;
                        videoView.getPlayer().start();

                    } else {
                        video_pause_play = false;
                        videoView.getPlayer().pause();
                    }


                    return false;
                }
            });
            imageview_video_thumbanil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageview_video_thumbanil.setVisibility(View.GONE);
                    videoView.getPlayer().start();
                    videoView.setFingerprint(position);
                }
            });*/
        } else {
            imageView.setVisibility(View.VISIBLE);
            image_video_layout.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                imageView.setBackground(Drawable.createFromPath(modelArrayList.get(position).getMedia_url()));
//                Glide.with(context).load(images.getImageUrl()[position]).into(R.id.image_vp_home);
                Glide.with(context).load(modelArrayList.get(position).getMedia_url()).into((imageView));
            }
//            imageView.setImageResource(Integer.parseInt(modelArrayList.get(position).getImage()));
        }

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
