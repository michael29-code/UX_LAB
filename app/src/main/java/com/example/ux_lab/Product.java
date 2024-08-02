package com.example.ux_lab;

public class Product {
    private final String name;
    private final String artist;
    private final String description;
    private int imageResId;

    public Product(String name, String artist, String description, int imageResId) {
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
