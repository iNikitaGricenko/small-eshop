package com.example.eshop.model.product;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products e SET deleted = TRUE, deleted_at = now() WHERE e.product_id=?")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "count")
    private int count;

    @Column(name = "deleted")
    private boolean isDeleted = Boolean.FALSE;
    @Column(name = "deleted_at", insertable = false)
    private Date deletedAt;

}
