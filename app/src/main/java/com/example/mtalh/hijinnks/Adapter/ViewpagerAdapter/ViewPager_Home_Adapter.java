package com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import com.example.mtalh.hijinnks.Adapter.RecyclerAdapter.RecyclerAdapter_Home_HomeFragment;
import com.example.mtalh.hijinnks.Fragments.HomeFragment;
import com.example.mtalh.hijinnks.Models.PostMedia;
import com.example.mtalh.hijinnks.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


/**
 * Created by CP on 2/8/2018.
 */

public class ViewPager_Home_Adapter extends PagerAdapter implements View.OnClickListener {
    static ItemClickListener_Pager itemClickListener_pager;
    boolean video_pause_play = false;
    RelativeLayout image_video_layout;
    ImageView imageview_video_thumbanil;
    ImageLoader imageLoader;
    String videourl2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    /*  ArrayList arrayList_get = media3;
      ArrayList<PostMedia> arrayList_get_custome;*/
    RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder holder;
    int posviewpager;
    private ArrayList<PostMedia> modelArrayList;
    private Context context;

    public ViewPager_Home_Adapter(ArrayList<PostMedia> modelArrayList, Context context) {
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

        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "i am clicked ", Toast.LENGTH_SHORT).show();
                /*AppCompatActivity activity = (AppCompatActivity) context;
                Intent viewpager_home_full = new Intent(activity, com.example.mtalh.hijinnks.Activites.HomeFullViewpager.class);
                viewpager_home_full.putExtra("array_postion", position);
                viewpager_home_full.putExtra("array_list", modelArrayList);
                activity.startActivity(viewpager_home_full);*/
            }
        });
        final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image_vp_home);
        final JCVideoPlayer videoView = (JCVideoPlayer) imageLayout.findViewById(R.id.video_vp_home);
        image_video_layout = (RelativeLayout) imageLayout.findViewById(R.id.image_video_layout);
        imageview_video_thumbanil = (ImageView) imageLayout.findViewById(R.id.image_for_video_thumbnail);


        if (modelArrayList.get(position).getType().equals("Video")) {
            imageView.setVisibility(View.GONE);
            image_video_layout.setVisibility(View.VISIBLE);
//            videoView.setVideoURI(Uri.parse(modelArrayList.get(position).getMedia_url()));
//            videoView.setVideoPath(String.valueOf(Uri.parse(videourl2)));

//                videoView.setVideoURI(Uri.parse(videourl2));
            //    Glide.with(context).load(modelArrayList.get(position).getVideo_thumbnail()).into(imageview_video_thumbanil);

            // videoView.setVideoURI(Uri.parse(modelArrayList.get(position).getMedia_url()));
//            videoView.setThumbImageViewScalType(ImageView.ScaleType.FIT_XY);

            String arrayList_Fullvideo = modelArrayList.get(position).getMedia_url();
            videoView.setUp(arrayList_Fullvideo, "", false);
            videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "vide is clicked", Toast.LENGTH_SHORT).show();
                }
            });
            ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
             ImageLoader imageLoader = HomeFragment.mImageLoader;
            imageLoader.getInstance().displayImage(arrayList_Fullvideo, videoView.ivThumb);
//            videoController.ivThumb.setThumbInCustomProject("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
           /* imageview_video_thumbanil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        imageview_video_thumbanil.setVisibility(View.GONE);
//                        videoView.setUp(Uri.parse(String.valueOf(arrayList_Fullvideo)));
                        videoView.setUp(arrayList_Fullvideo, "hiii",false);
                    videoView.setThumbImageViewScalType(ImageView.ScaleType.FIT_XY);


                }
            });*/

//            videoView.setSource(Uri.parse(videourl2));
//            videoView.start();

//            videoView.setVideoPath(String.valueOf(Uri.parse(String.valueOf(modelArrayList.get(position)))));
//videoView.getPlayer().start();
//             videoView.getVideoInfo().setBgColor(Color.GRAY).setAspectRatio(VideoInfo.AR_MATCH_PARENT);//config player

//            imageview_video_thumbanil.setImageBitmap(modelArrayList.get(position).getBitmap_video());

            // Glide.with(context).load(modelArrayList.get(position).getVideo_thumbnail()).into(imageview_video_thumbanil);
//            videoView.setFingerprint(position);
             /* videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                  AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Intent viewpager_home_full = new Intent(activity, com.example.mtalh.hijinnks.Activites.HomeFullViewpager.class);
                    viewpager_home_full.putExtra("array_list", arrayList_get);
                    activity.startActivity(viewpager_home_full);

                    return false;
                }
            });*/
              /* imageview_video_thumbanil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    clickListener.itemClick(view, position, holder);

//                    imageview_video_thumbanil.setVisibility(View.GONE);
//                    videoView.start();
                 AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Intent viewpager_home_full = new Intent(activity, com.example.mtalh.hijinnks.Activites.HomeFullViewpager.class);
                    viewpager_home_full.putExtra("array_list", arrayList_get);
                    activity.startActivity(viewpager_home_full);
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
               /*  imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        Intent viewpager_home_full = new Intent(activity, com.example.mtalh.hijinnks.Activites.HomeFullViewpager.class);
                        viewpager_home_full.putExtra("array_list", arrayList_get);
                        activity.startActivity(viewpager_home_full);
                    }
                });*/

//            imageView.setImageResource(Integer.parseInt(modelArrayList.get(position).getImage()));
        }
        posviewpager = position;
        Log.d("CURRENTPOSITION: - ", "" + position);
        //  Toast.makeText(context, "CP :-" + position, Toast.LENGTH_SHORT).show();
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

  /*  @Override
    public void onClick(View view) {
        itemClickListener_pager.OnItemClick(getItemPosition());

    }*/

    public void SsetClickListener(ItemClickListener_Pager listener) {
        this.itemClickListener_pager = listener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener_pager.OnItemClick(posviewpager);
    }

    public interface ItemClickListener_Pager {
        public void OnItemClick(int Pos);
    }
}
