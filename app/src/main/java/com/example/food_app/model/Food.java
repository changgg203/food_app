package com.example.food_app.model;

public class Food {
    private String name;
    private String description;
    private float rating;
    private String time;
    private int imageResId;

    public Food(String name, String description, float rating, String time, int imageResId) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.time = time;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
