package com.example.eshop.model.product;

import com.example.eshop.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @Column(nullable = false)
    private Long orders_id;
    private String address;
    private String description;
    private int count;
    private Date date;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    )
    @JsonBackReference
    private User user;

    @OneToMany
    @JoinTable(
            name = "user_orders",
            joinColumns = {@JoinColumn(
                    name = "orders_id",
                    referencedColumnName = "orders_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name="product_id",
                    referencedColumnName = "product_id",
                    unique = false
            )}
    )
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

}
