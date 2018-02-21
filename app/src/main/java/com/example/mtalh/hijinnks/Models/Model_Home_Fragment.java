package com.example.mtalh.hijinnks.Models;

import java.util.ArrayList;

/**
 * Created by CP on 1/25/2018.
 */

public class Model_Home_Fragment {
    String user_name;
    int user_profil_Image;
    String intrest;
    ArrayList<PostMedia> media;
    String title_of_event;

    public Model_Home_Fragment(String user_name, int user_profil_Image, String intrest, ArrayList<PostMedia> media, String title_of_event) {
        this.user_name = user_name;
        this.user_profil_Image = user_profil_Image;
        this.intrest = intrest;
        this.media = media;
        this.title_of_event = title_of_event;
    }

    public ArrayList<PostMedia> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<PostMedia> media) {
        this.media = media;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_profil_Image() {
        return user_profil_Image;
    }

    public void setUser_profil_Image(int user_profil_Image) {
        this.user_profil_Image = user_profil_Image;
    }

    public String getIntrest() {
        return intrest;
    }

    public void setIntrest(String intrest) {
        this.intrest = intrest;
    }

    public String getTitle_of_event() {
        return title_of_event;
    }

    public void setTitle_of_event(String title_of_event) {
        this.title_of_event = title_of_event;
    }

}
