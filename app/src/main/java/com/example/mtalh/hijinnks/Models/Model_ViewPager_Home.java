package com.example.mtalh.hijinnks.Models;

import android.graphics.Bitmap;

/**
 * Created by CP on 2/8/2018.
 */

public class Model_ViewPager_Home {

    String image;
    String video;
    boolean video_check;
    Bitmap bitmap_video;

    public Model_ViewPager_Home(String image) {
        this.image = image;
    }

    public Model_ViewPager_Home(String video, boolean video_check, Bitmap bitmap_video) {
        this.video = video;
        this.video_check = video_check;
        this.bitmap_video = bitmap_video;
    }

    public Bitmap getBitmap_video() {
        return bitmap_video;
    }

    public void setBitmap_video(Bitmap bitmap_video) {
        this.bitmap_video = bitmap_video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public boolean isVideo_check() {
        return video_check;
    }

    public void setVideo_check(boolean video_check) {
        this.video_check = video_check;
    }
}
