package com.example.eshop.model.order;

import com.example.eshop.model.product.Product;
import com.example.eshop.model.user.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "orders")
@SQLDelete(sql = "UPDATE orders e " +
        "SET deleted=true, deleted_at=now() " +
        "WHERE e.orders_id=?")
@Getter @Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "description", length = 1024)
    private String description;

    @Transient
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

    @ElementCollection
    @CollectionTable(name = "user_orders", joinColumns = {
            @JoinColumn(name = "order_id", referencedColumnName = "orders_id")})
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "product_id"))})
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return address.equals(order.address) &&
                user.equals(order.user) &&
                products.equals(order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, user, products);
    }
}
