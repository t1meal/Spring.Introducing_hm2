package ru.gb.main.utils;

import java.util.Random;

public class Product {
    private final Integer id;
    private final String title;
    private final Integer cost;

    public Product(int id, String title) {
        this.id = id;
        this.title = title;
        Random random = new Random();
        this.cost = random.nextInt(100);
    }
    public String getProductToString(){
        String id = Integer.toString(this.id);
        String cost = Integer.toString(this.cost);
        return id + " " + this.title + " " + cost;
    }

    public Integer getId() {
        return id;
    }
}
