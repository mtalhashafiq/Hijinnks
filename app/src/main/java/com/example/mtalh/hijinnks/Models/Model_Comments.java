package com.example.mtalh.hijinnks.Models;

public class Model_Comments {

    public String name;
    public String Date;
    public String Comment;

    public Model_Comments(String name, String date, String comment) {

        this.name = name;
        Date = date;
        Comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
