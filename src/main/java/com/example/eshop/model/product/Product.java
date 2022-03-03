package com.example.eshop.model.product;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 &&
                title.equals(product.title) &&
                articleNumber.equals(product.articleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, articleNumber, price);
    }
}
