package com.stackroute.favouriteService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
public class Favourite {
    @Id
    String userId;
    List<Product> products;
    public Favourite(String userId, List<Product> products) {
        super();
        this.userId = userId;
        this.products = products;
    }
    public Favourite() {
        super();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    @Override
    public String toString() {
        return "Favourite [userId=" + userId + ", products=" + products + "]";
    }



}