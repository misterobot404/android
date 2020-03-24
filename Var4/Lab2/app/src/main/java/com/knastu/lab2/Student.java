package com.knastu.lab2;

public class Student {

    String name;
    double rating;

    public Student(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

