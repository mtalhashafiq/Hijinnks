package com.example.mtalh.hijinnks.Models;

import java.io.Serializable;

/**
 * Created by mtalh on 08-Feb-18.
 */

public class PostMedia implements Serializable {

    private String type;
    private String media_url;
    private String video_thumbnail;

    public PostMedia(String type, String media_url, String video_thumbnail) {
        this.type = type;
        this.media_url = media_url;
        if (video_thumbnail != null)
            this.video_thumbnail = video_thumbnail;
    }

    public String getVideo_thumbnail() {
        return video_thumbnail;
    }

    public void setVideo_thumbnail(String video_thumbnail) {
        this.video_thumbnail = video_thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }
}
