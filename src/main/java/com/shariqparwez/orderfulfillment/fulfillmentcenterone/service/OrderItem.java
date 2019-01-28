package com.shariqparwez.orderfulfillment.fulfillmentcenterone.service;

import java.math.BigDecimal;

/**
 * Represents an order item.
 * 
 * @author Shariq Parwez
 *
 */
public class OrderItem {

   private String itemNumber;
   private BigDecimal price;
   private int quantity;

   public OrderItem() {

   }

   public OrderItem(String itemNumber, BigDecimal price, int quantity) {
      super();
      this.itemNumber = itemNumber;
      this.price = price;
      this.quantity = quantity;
   }

   /**
    * @return the itemNumber
    */
   public String getItemNumber() {
      return itemNumber;
   }

   /**
    * @param itemNumber
    *           the itemNumber to set
    */
   public void setItemNumber(String itemNumber) {
      this.itemNumber = itemNumber;
   }

   /**
    * @return the price
    */
   public BigDecimal getPrice() {
      return price;
   }

   /**
    * @param price
    *           the price to set
    */
   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   /**
    * @return the quantity
    */
   public int getQuantity() {
      return quantity;
   }

   /**
    * @param quantity
    *           the quantity to set
    */
   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("OrderItem [");
      if (itemNumber != null) {
         builder.append("itemNumber=");
         builder.append(itemNumber);
         builder.append(", ");
      }
      if (price != null) {
         builder.append("price=");
         builder.append(price);
         builder.append(", ");
      }
      builder.append("quantity=");
      builder.append(quantity);
      builder.append("]");
      return builder.toString();
   }

}
