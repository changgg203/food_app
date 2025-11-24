package com.example.food_app.model;

public class Notification {
    private String title;
    private String message;
    private String time;
    private int icon;

    public Notification(String title, String message, String time, int icon) {
        this.title = title;
        this.message = message;
        this.time = time;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
