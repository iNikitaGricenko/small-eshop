package com.example.eshop.model.product;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter @Setter
public class Product {

    @Id
    @Column(nullable = false)
    private Long product_id;
    private String name;
    private int price;
    private int count;

}
