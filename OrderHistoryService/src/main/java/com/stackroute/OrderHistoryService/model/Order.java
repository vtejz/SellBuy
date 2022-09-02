package com.stackroute.OrderHistoryService.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Order {

   @Id
   private String orderId;
   private int buyerId;
   private int sellerId;
   private int productId;
   private String ProductName;

   private int quantity;
   private double orderTotal;
   @JsonSerialize(using = ToStringSerializer.class)
   LocalDateTime date;
   private String status;

   public Order() {
   }

   public Order(String productName, double orderTotal) {
      ProductName = productName;
      this.orderTotal = orderTotal;
   }

   public Order(String orderId, int buyerId, int sellerId, int productId, int quantity, LocalDateTime date, String status) {
      this.orderId = orderId;
      this.buyerId = buyerId;
      this.sellerId = sellerId;
      this.productId = productId;
      this.quantity = quantity;
      this.date = date;
      this.status = status;
   }

   public String getOrderId() {
      return orderId;
   }

   public void setOrderId(String orderId) {
      this.orderId = orderId;
   }

   public String getProductName() {
      return ProductName;
   }

   public void setProductName(String productName) {
      ProductName = productName;
   }

   public double getOrderTotal() {
      return orderTotal;
   }

   public void setOrderTotal(double orderTotal) {
      this.orderTotal = orderTotal;
   }

   public int getBuyerId() {
      return buyerId;
   }

   public void setBuyerId(int buyerId) {
      this.buyerId = buyerId;
   }

   public int getSellerId() {
      return sellerId;
   }

   public void setSellerId(int sellerId) {
      this.sellerId = sellerId;
   }

   public int getProductId() {
      return productId;
   }

   public void setProductId(int productId) {
      this.productId = productId;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   public LocalDateTime getDate() {
      return date;
   }

   public void setDate(LocalDateTime date) {
      this.date = LocalDateTime.now();
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   @Override
   public String toString() {
      return "Order{" +
              "orderId='" + orderId + '\'' +
              ", buyerId=" + buyerId +
              ", sellerId=" + sellerId +
              ", productId=" + productId +
              ", ProductName='" + ProductName + '\'' +
              ", quantity=" + quantity +
              ", orderTotal=" + orderTotal +
              ", date=" + date +
              ", status='" + status + '\'' +
              '}';
   }
}
