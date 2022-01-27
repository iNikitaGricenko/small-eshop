package com.example.eshop.model.product;

import com.example.eshop.model.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders SET deleted = true WHERE orders_id=?")
@Getter @Setter
public class Order {

    @Id
    @Column(name = "orders_id", nullable = false)
    private Long id;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "count")
    private int count;

    @Column(name = "date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private boolean deleted = Boolean.FALSE;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_orders",
            joinColumns = {@JoinColumn(
                    name = "orders_id",
                    referencedColumnName = "orders_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name="product_id",
                    referencedColumnName = "product_id",
                    unique = false
            )})
    private Set<Product> products = new HashSet<>();

}
