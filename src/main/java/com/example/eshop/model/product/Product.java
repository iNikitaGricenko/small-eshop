package com.example.eshop.model.product;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE product_id=?")
@Getter @Setter
public class Product {

    @Id
    @Column(name = "product_id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price")
    private int price;
    private int count;
    private boolean deleted = Boolean.FALSE;

}
