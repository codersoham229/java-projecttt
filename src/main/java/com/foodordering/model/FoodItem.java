package com.foodordering.model;

import java.math.BigDecimal;

public class FoodItem {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;

    public FoodItem(int id, String name, String description, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public String getCategory() { return category; }
}
