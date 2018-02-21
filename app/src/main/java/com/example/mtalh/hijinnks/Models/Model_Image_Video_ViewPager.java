package com.example.mtalh.hijinnks.Models;

import android.net.Uri;

/**
 * Created by CP on 2/7/2018.
 */

public class Model_Image_Video_ViewPager {
    String Image_model;
    Uri video_model;
    boolean is_video;

    public Model_Image_Video_ViewPager(String image_model) {
        Image_model = image_model;
    }

    public Model_Image_Video_ViewPager(Uri video_model, boolean is_video) {
        this.video_model = video_model;
        this.is_video = is_video;
    }

    public boolean isIs_video() {
        return is_video;
    }

    public void setIs_video(boolean is_video) {
        this.is_video = is_video;
    }

    public String getImage_model() {
        return Image_model;
    }

    public void setImage_model(String image_model) {
        Image_model = image_model;
    }

    public Uri getVideo_model() {
        return video_model;
    }

    public void setVideo_model(Uri video_model) {
        this.video_model = video_model;
    }
}
