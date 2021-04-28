package com.example.lab3;

public class Food {
    Integer image;
    String name, weight, price;

    public Food(int image, String name, String weight, String price) {
        this.image = image;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
