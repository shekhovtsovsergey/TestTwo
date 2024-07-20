package org.example.model;

import java.math.BigDecimal;

public class Products {
   private long id;
   private long userId;
   private String accountNumber;
   private BigDecimal balance;
   private String productType;

   public Products(long userId, String accountNumber, BigDecimal balance, String productType) {
      this.userId = userId;
      this.accountNumber = accountNumber;
      this.balance = balance;
      this.productType = productType;
   }

   public Products() {
   }

   public long getId() {
      return id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public long getUserId() {
      return userId;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public String getAccountNumber() {
      return accountNumber;
   }

   public void setAccountNumber(String accountNumber) {
      this.accountNumber = accountNumber;
   }

   public BigDecimal getBalance() {
      return balance;
   }

   public void setBalance(BigDecimal balance) {
      this.balance = balance;
   }

   public String getProductType() {
      return productType;
   }

   public void setProductType(String productType) {
      this.productType = productType;
   }
}
