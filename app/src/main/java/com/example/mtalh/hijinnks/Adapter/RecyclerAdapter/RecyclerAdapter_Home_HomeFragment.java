package com.example.mtalh.hijinnks.Adapter.RecyclerAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtalh.hijinnks.Activites.Social.Comments;
import com.example.mtalh.hijinnks.Adapter.ViewpagerAdapter.ViewPager_Home_Adapter;
import com.example.mtalh.hijinnks.CustomeUI.ViewPager.ClickableViewPager;
import com.example.mtalh.hijinnks.Fragments.Map_Fragment;
import com.example.mtalh.hijinnks.Fragments.User_Profile_Fragment;
import com.example.mtalh.hijinnks.Models.Model_Home_Fragment;
import com.example.mtalh.hijinnks.Models.Model_ViewPager_Home;
import com.example.mtalh.hijinnks.R;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by mtalh on 24-Oct-17.
 */

public class RecyclerAdapter_Home_HomeFragment extends RecyclerView.Adapter<RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder> implements  ViewPager_Home_Adapter.ItemClickListener_Pager /*
        implements ViewPager_Home_Adapter.itemClick*/ {


    static ItemClickListener itemClickListener;
    String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    String videourl2 = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    String videourl = "http://www.demonuts.com/Demonuts/smallvideo.mp4";
    String ImageUrl_1 = "https://fakeimg.pl/350x200/?text=World&font=lobster";
    String ImageUrl_2 = "https://fakeimg.pl/250x100/ff0000/";
    String video_array[] = {TEST_URL, videourl, videourl2};
    String image_array[] = {ImageUrl_1, ImageUrl_2};
    int images_drawable_array[] = {R.drawable.person5, R.drawable.person4};
    Context context;
    Bitmap bmbm1, bmbm2;
    boolean video_pause_play = false;

    private ViewPager_Home_Adapter viewPager_home_adapter;
    private boolean like_event = false;
    private boolean view_event = false;
    private ArrayList<Model_Home_Fragment> model_home_fragments = new ArrayList<>();
    private ArrayList<Model_ViewPager_Home> model_viewPager_homes = new ArrayList<>();
    private Map_Fragment mapFragment = new Map_Fragment();
    private User_Profile_Fragment user_profile_fragment = new User_Profile_Fragment();

    //    private List<HomeListModel> mData;
    public RecyclerAdapter_Home_HomeFragment(ArrayList<Model_Home_Fragment> model_home_fragments, Context context) {
        this.model_home_fragments = model_home_fragments;
        this.context = context;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_home_homefragment, parent, false);
        RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder recyclerviewholder = new RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder(view);
        return recyclerviewholder;

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.user_Name.setText(model_home_fragments.get(position).getUser_name());
        holder.user_profile_image.setImageResource(model_home_fragments.get(position).getUser_profil_Image());
        holder.intrest_textview.setText(model_home_fragments.get(position).getIntrest());
        holder.title_of_event.setText(model_home_fragments.get(position).getTitle_of_event());
        viewPager_home_adapter = new ViewPager_Home_Adapter(model_home_fragments.get(position).getMedia(), context);
        viewPager_home_adapter.notifyDataSetChanged();
        holder.viewPager_home.setAdapter(viewPager_home_adapter);
        holder.inkPageIndicator.setViewPager(holder.viewPager_home);





        /*final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.BOTTOM;
        final ViewPagerIndicator viewPagerIndicator = new ViewPagerIndicator(context);
        viewPagerIndicator.setSelectedDotColor(R.color.white);
        holder.viewPager_home.addView(viewPagerIndicator, layoutParams);*/
      /*  for (int i = 0; i < video_array.length; i++) {
            model_viewPager_homes.add(new Model_ViewPager_Home(video_array[i], true));
        }*/
        // if (model_viewPager_homes.size() == 0) {
           /* for (int i = 0; i > image_array.length; i++) {
                model_viewPager_homes.add(new Model_ViewPager_Home(image_array[i]));
            }*/
            /*for (int i = 0; i > video_array.length; i++) {
                model_viewPager_homes.add(new Model_ViewPager_Home(video_array[i],true));
            }*/
            /*MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videourl);
            Bitmap bmFrame = mediaMetadataRetriever.getFrameAtTime(5000000);

            MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
            mediaMetadataRetriever2.setDataSource(videourl2);
            Bitmap bmFrame2 = mediaMetadataRetriever.getFrameAtTime(5000000);*/

//           Bitmap bmThumbnail = ThumbnailUtils.createVideoThumbnail(videourl, MediaStore.Video.Thumbnails.MINI_KIND);


            /*try {
                bmbm1 = retriveVideoFrameFromVideo(videourl);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            try {
                bmbm2 = retriveVideoFrameFromVideo(TEST_URL);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }*/

//            model_viewPager_homes.add(new Model_ViewPager_Home(videourl, true, bmbm1));
//            model_viewPager_homes.add(new Model_ViewPager_Home(videourl, true, bmbm1));
//            model_viewPager_homes.add(new Model_ViewPager_Home(TEST_URL, true, bmbm2));
//            model_viewPager_homes.add(new Model_ViewPager_Home(ImageUrl_2));
//            model_viewPager_homes.add(new Model_ViewPager_Home(ImageUrl_1));
//            model_viewPager_homes.add(new Model_ViewPager_Home(TEST_URL, true, bmbm2));
        // }
//        Model_Home_Fragment item = model_home_fragments.get(position);

       /* viewPager_home_adapter=new ViewPager_Home_Adapter(model_viewPager_homes,context);
        holder.viewPager_home.setAdapter(viewPager_home_adapter);*/

//     viewPager_home_adapter.notifyDataSetChanged();



  /*
        holder.videoView.setVideoURI(Uri.parse(model_home_fragments.get(position).getVideo_url()));
        holder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (video_pause_play == false) {

                    holder.videoView.start();
                    holder.video_play_pause.setImageResource(R.drawable.video_play_play);
                    video_pause_play = true;
                    holder.video_background_play.setVisibility(View.INVISIBLE);
                    holder.video_play_pause.setVisibility(View.INVISIBLE);
                } else {
                    holder.videoView.pause();
                    holder.video_play_pause.setImageResource(R.drawable.video_play_play);
                    holder.video_background_play.setVisibility(View.VISIBLE);
                    holder.video_play_pause.setVisibility(View.VISIBLE);
                    video_pause_play = false;
                }
                return false;
            }
        });*/

      /*  viewPager_home_adapter.SsetClickListener(new ViewPager_Home_Adapter.ItemClickListener_Pager() {
            @Override
            public void OnItemClick(int Pos) {
                Toast.makeText(context, "gfffffffffff", Toast.LENGTH_SHORT).show();
            }
        });*/
     /*   holder.viewPager_home.setOnViewPagerClickListener(new ClickableViewPager.OnClickListener() {
            @Override
            public void onViewPagerClick(ViewPager viewPager) {
                AppCompatActivity activity = (AppCompatActivity) viewPager.getContext();
                Intent viewpager_home_full = new Intent(activity, HomeFullViewpager.class);
//                    viewpager_home_full.putExtra("array_list", arrayList_get);
                viewpager_home_full.putExtra("position", position);
                viewpager_home_full.putExtra("dataArray", (Serializable) model_home_fragments.get(holder.getAdapterPosition()).getMedia());
                activity.startActivity(viewpager_home_full);
            }
        });*/


        holder.viewPager_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                Intent viewpager_home_full = new Intent(activity, com.example.mtalh.hijinnks.Activites.Tab.HomeFullViewpager.class);
////                    viewpager_home_full.putExtra("array_list", arrayList_get);
//                viewpager_home_full.putExtra("position", position);
//                viewpager_home_full.putExtra("dataArray", (Serializable) model_home_fragments.get(holder.getAdapterPosition()).getMedia());
//                activity.startActivity(viewpager_home_full);
            }
        });
        holder.open_map_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_framelayout, mapFragment).addToBackStack(null).commit();

            }
        });
        holder.user_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_framelayout, user_profile_fragment).addToBackStack(null).commit();

            }
        });
        holder.comment_section_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent comment_activity = new Intent(activity, Comments.class);
                activity.startActivity(comment_activity);
            }
        });
//        holder.like_events.setText("");
//        holder.rsvpd_events.setText("");
    /*    holder.view_event_layout.setOnClickListener(new View.OnClickListener() {
            int rsvpd_event_counter = 0;
            @Override
            public void onClick(View view) {
                if (holder.view_event_layout.getTag().equals("unview")) {
                    holder.view_event_layout.setTag("view");
                    holder.view_event_image.setImageResource(R.drawable.blue_circle);
                    holder.view_event_image_eye.setImageResource(R.drawable.view_icon_white);
                    rsvpd_event_counter++;
                    holder.rsvpd_events.setText(String.valueOf(rsvpd_event_counter));
                } else {
                    holder.like_event_layout.setTag("unview");
                    holder.view_event_image.setImageResource(R.drawable.ic_share_icon_circle);
                    holder.view_event_image_eye.setImageResource(R.drawable.view_icon_gray);
                    rsvpd_event_counter--;
                    holder.rsvpd_events.setText(String.valueOf(rsvpd_event_counter));

                }
            }
        });*/

        holder.view_event_layout.setOnClickListener(new View.OnClickListener() {
            boolean check = false;
            int view_event_counter = 0;

            @Override
            public void onClick(View view) {
                if (check == false) {
                    check = true;
                    holder.view_event_image.setImageResource(R.drawable.blue_circle);
                    holder.view_event_image_eye.setImageResource(R.drawable.view_icon_white);
                    view_event_counter++;
                    holder.rsvpd_events.setText(String.valueOf(view_event_counter));

                } else {
                    check = false;
                    holder.view_event_image.setImageResource(R.drawable.ic_share_icon_circle);
                    holder.view_event_image_eye.setImageResource(R.drawable.view_icon_gray);
                    view_event_counter--;
                    holder.rsvpd_events.setText(String.valueOf(view_event_counter));

                }
            }
        });
        holder.like_event_layout.setOnClickListener(new View.OnClickListener() {
            int likes_event_counter = 0;

            @Override
            public void onClick(View view) {
                if (holder.like_event_layout.getTag().equals("unlike")) {
                    holder.like_event_layout.setTag("like");
                    holder.like_event_image.setImageResource(R.drawable.red_circle);
                    holder.like_event_image_heart.setImageResource(R.drawable.heart_white);
                    likes_event_counter++;
                    holder.like_events.setText(String.valueOf(likes_event_counter));

                } else {
                    holder.like_event_layout.setTag("unlike");
                    holder.like_event_image.setImageResource(R.drawable.ic_share_icon_circle);
                    holder.like_event_image_heart.setImageResource(R.drawable.heart_gray);
                    likes_event_counter--;
                    holder.like_events.setText(String.valueOf(likes_event_counter));

                }
            }
        });

viewPager_home_adapter.SsetClickListener(this);
    }


    @Override
    public int getItemCount() {
        return model_home_fragments.size();
    }
   /* @Override
    public void setItemClick(View view, int position, RecyclerAdapter_Home_HomeFragment.RecyclerViewHolder holder) {
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        Intent viewpager_home_full = new Intent(activity, com.example.mtalh.hijinnks.Activites.Tab.HomeFullViewpager.class);
//                    viewpager_home_full.putExtra("array_list", arrayList_get);
        viewpager_home_full.putExtra("position", position);
        viewpager_home_full.putExtra("dataArray", (Serializable) model_home_fragments.get(holder.getAdapterPosition()).getMedia());
        activity.startActivity(viewpager_home_full);
    }*/


    public void SsetClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @Override
    public void OnItemClick(int Pos) {
        Toast.makeText(context, "ddddddddd", Toast.LENGTH_SHORT).show();
    }


/*    @Override
    public void itemClick(View v, int position, RecyclerViewHolder holder) {
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        Intent viewpager_home_full = new Intent(activity, com.example.mtalh.hijinnks.Activites.Tab.HomeFullViewpager.class);
//                    viewpager_home_full.putExtra("array_list", arrayList_get);
        viewpager_home_full.putExtra("position", position);
        viewpager_home_full.putExtra("dataArray", (Serializable) model_home_fragments.get(holder.getAdapterPosition()).getMedia());
        activity.startActivity(viewpager_home_full);
    }*/

    public interface ItemClickListener {
        public void OnItemClick(int Pos);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView user_Name, intrest_textview, title_of_event;
        /*  VideoView videoView;
          ImageView video_background_play, video_play_pause;
           ProgressBar progess_bar_video_main_class;*/
        LinearLayout open_map_fragment;
        ClickableViewPager viewPager_home;
        CircleImageView user_profile_image;
        LinearLayout comment_section_screen;
        InkPageIndicator inkPageIndicator;
        RelativeLayout like_event_layout, view_event_layout, share_event_layout;
        CircleImageView like_event_image, view_event_image;
        ImageView like_event_image_heart, view_event_image_eye;
        TextView like_events, rsvpd_events;

        public RecyclerViewHolder(View view) {
            super(view);
            user_Name = (TextView) view.findViewById(R.id.profile_name);
            intrest_textview = (TextView) view.findViewById(R.id.intrest_textview);
            title_of_event = (TextView) view.findViewById(R.id.title_of_event);
            viewPager_home = (ClickableViewPager) view.findViewById(R.id.viewpager_home);
            /*videoView = (VideoView) view.findViewById(R.id.videoview);
            video_background_play = (ImageView) view.findViewById(R.id.video_background_play);
            video_play_pause = (ImageView) view.findViewById(R.id.video_play);
            progess_bar_video_main_class = (ProgressBar) view.findViewById(R.id.progess_bar_video);*/
            open_map_fragment = (LinearLayout) view.findViewById(R.id.open_map_fragment);
            comment_section_screen = (LinearLayout) view.findViewById(R.id.comment_section_screen);
            user_profile_image = (CircleImageView) view.findViewById(R.id.user_profile_image);
            inkPageIndicator = (InkPageIndicator) view.findViewById(R.id.indicator);
            like_event_layout = (RelativeLayout) view.findViewById(R.id.like_event_layout);
            view_event_layout = (RelativeLayout) view.findViewById(R.id.view_event_layout);
            share_event_layout = (RelativeLayout) view.findViewById(R.id.share_event_layout);
            like_event_image = (CircleImageView) view.findViewById(R.id.like_event_image);
            view_event_image = (CircleImageView) view.findViewById(R.id.view_event_image);
            like_event_image_heart = (ImageView) view.findViewById(R.id.like_event_image_heart);
            view_event_image_eye = (ImageView) view.findViewById(R.id.view_event_image_eye);
            like_events = (TextView) view.findViewById(R.id.like_event);
            rsvpd_events = (TextView) view.findViewById(R.id.rsvpd_event);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }
/*public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }*/
 /*   class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        Bitmap bitmap;

        public ImageDownloaderTask(ImageView imageView) {
            imageViewReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return retriveVideoFrameFromVideo(params[0]);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (isCancelled()) {
                bitmap = null;
            }

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    if (bitmap != null) {
//                        imageView.setImageBitmap(bitmap);
                    } else {
                        Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.person1);
                        imageView.setImageDrawable(placeholder);
                    }
                }
            }
        }
    }*/
}