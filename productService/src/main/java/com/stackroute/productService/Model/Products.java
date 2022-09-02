package com.stackroute.productService.Model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Products {
    @Id
    @ApiModelProperty(notes="Enter the positive product Id")
    int productId;
    int sellerId;
    String productName;
    double productPrice;
    String category;
    String subCategory;
    String description;
    String status;

    public Products(Integer productId, Integer sellerId, String productName, double productPrice, String category, String subCategory, String description, String status) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.category = category;
        this.subCategory = subCategory;
        this.description = description;
        this.status = status;
    }

    public Products() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", sellerId=" + sellerId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
