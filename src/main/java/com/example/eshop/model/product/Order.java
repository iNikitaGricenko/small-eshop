package com.example.eshop.model.product;

import com.example.eshop.model.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders e SET deleted=true, deleted_at=now() WHERE e.orders_id=?")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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

    @Column(name = "deleted", nullable = false, insertable = false)
    private boolean isDeleted = Boolean.FALSE;
    @Column(name = "deleted_at", insertable = false)
    private Date deleted;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_orders",
            joinColumns = {@JoinColumn(
                    name = "orders_id",
                    referencedColumnName = "orders_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name="product_id",
                    referencedColumnName = "product_id"
            )})
    private List<Product> products = new ArrayList<>();

}
