package com.example.mtalh.hijinnks.Models;

/**
 * Created by CP on 1/29/2018.
 */

public class ModelChatList {
    String profileName;
    String lastSeen;
    int profileImage;
    boolean model_swipe_check=false;

    public ModelChatList(String profileName, String lastSeen, int profileImage,boolean model_swipe_check) {

        this.profileName = profileName;
        this.lastSeen = lastSeen;
        this.profileImage = profileImage;
        this.model_swipe_check=model_swipe_check;
    }

    public boolean isModel_swipe_check() {
        return model_swipe_check;
    }

    public void setModel_swipe_check(boolean model_swipe_check) {
        this.model_swipe_check = model_swipe_check;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }
}
