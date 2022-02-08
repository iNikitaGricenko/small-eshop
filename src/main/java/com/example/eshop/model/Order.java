package com.example.eshop.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders e SET deleted=true, deleted_at=now() WHERE e.orders_id=?")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "count")
    private int count;

    @Column(name = "created", insertable = false)
    @Basic(optional = false)
    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Basic(optional = false)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "deleted")
    private boolean isDeleted = Boolean.FALSE;
    @Column(name = "deleted_at", insertable = false)
    private Date deletedAt;

    /*@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_orders",
            joinColumns = {@JoinColumn(
                    name = "orders_id",
                    referencedColumnName = "orders_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name="product_id",
                    referencedColumnName = "product_id"
            )})*/
    @Transient
    private Set<Product> products = new HashSet<>();

}
