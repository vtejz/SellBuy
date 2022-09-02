package com.stackroute.favouriteService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

    @Id
    private int productId;
    private String productName;
    private String description;
    private String category;
    private String subCategory;
    private String status;

    public Product(int productId, String productName, String description, String category, String subCategory,
                   String status) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
        this.status = status;
    }

    public Product() {
        super();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * public Product(int productId, String productName, String description) {
     * super(); this.productId = productId; this.productName = productName;
     * this.description = description; }
     */
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
                + ", category=" + category + ", subCategory=" + subCategory + ", status=" + status + "]";
    }

//	@Override
//	public String toString() {
//		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
//				+ "]";
//	}

}

