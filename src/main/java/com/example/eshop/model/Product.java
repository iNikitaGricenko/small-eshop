package com.example.eshop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("products")
@Getter @Setter
public class Product {

    @MongoId
    private String id;
    private String title;
    private String category;
    private String productCategory;
    private String articleNumber;
    private String price;
    private int vatRate;
    private String available;
    private String description;
    private String color;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", articleNumber='" + articleNumber + '\'' +
                ", price='" + price + '\'' +
                ", vatRate=" + vatRate +
                ", available='" + available + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
