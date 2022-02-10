package com.example.eshop.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private float price;
    private int vatRate;
    private int count;
    private String available;
    private String description;
    private String color;
}
