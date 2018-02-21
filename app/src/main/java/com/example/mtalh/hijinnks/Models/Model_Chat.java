package com.example.mtalh.hijinnks.Models;

/**
 * Created by CP on 1/30/2018.
 */

public class Model_Chat {
    String sender_name, sender_message_time_sent,sender_message;
    int sender_profile_image;
    boolean sender_checked;


    String receiver_name,receiver_message_time_sent,receiver_message;
    int receiver_profile_image;

    public Model_Chat(String sender_name, String sender_message_time_sent, String sender_message, int sender_profile_image, boolean sender_checked) {
        this.sender_name = sender_name;
        this.sender_message_time_sent = sender_message_time_sent;
        this.sender_message = sender_message;
        this.sender_profile_image = sender_profile_image;
        this.sender_checked = sender_checked;
    }

    public Model_Chat(String receiver_name, String receiver_message_time_sent, String receiver_message, int receiver_profile_image) {
        this.receiver_name = receiver_name;
        this.receiver_message_time_sent = receiver_message_time_sent;
        this.receiver_message = receiver_message;
        this.receiver_profile_image = receiver_profile_image;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_message_time_sent() {
        return sender_message_time_sent;
    }

    public void setSender_message_time_sent(String sender_message_time_sent) {
        this.sender_message_time_sent = sender_message_time_sent;
    }

    public String getSender_message() {
        return sender_message;
    }

    public void setSender_message(String sender_message) {
        this.sender_message = sender_message;
    }

    public int getSender_profile_image() {
        return sender_profile_image;
    }

    public void setSender_profile_image(int sender_profile_image) {
        this.sender_profile_image = sender_profile_image;
    }

    public boolean isSender_checked() {
        return sender_checked;
    }

    public void setSender_checked(boolean sender_checked) {
        this.sender_checked = sender_checked;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_message_time_sent() {
        return receiver_message_time_sent;
    }

    public void setReceiver_message_time_sent(String receiver_message_time_sent) {
        this.receiver_message_time_sent = receiver_message_time_sent;
    }

    public String getReceiver_message() {
        return receiver_message;
    }

    public void setReceiver_message(String receiver_message) {
        this.receiver_message = receiver_message;
    }

    public int getReceiver_profile_image() {
        return receiver_profile_image;
    }

    public void setReceiver_profile_image(int receiver_profile_image) {
        this.receiver_profile_image = receiver_profile_image;
    }

}
