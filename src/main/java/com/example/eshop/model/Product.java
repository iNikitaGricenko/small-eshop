package com.example.eshop.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Embeddable;

@Embeddable
@Document("products")
@Data
public class Product {

    @Id
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
