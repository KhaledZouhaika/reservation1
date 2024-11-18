package com.example.reservation;

public class DentistItem {
    private String name;
    private double price;
    private int imageResourceId; // Resource ID for the item's image

    public DentistItem(String name, double price, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}


