package com.shariqparwez.orderfulfillment.fulfillmentcenterone.service;

import java.util.Date;
import java.util.List;

/**
 * Represents an order
 * 
 * @author Shariq Parwez
 *
 */
public class Order {

   private String firstName;
   private String lastName;
   private String email;
   private String orderNumber;
   private Date timeOrderPlaced;
   private List<OrderItem> orderItems;

   public Order() {

   }

   public Order(String firstName, String lastName, String email,
         String orderNumber, Date timeOrderPlaced, List<OrderItem> orderItems) {
      super();
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.orderNumber = orderNumber;
      this.timeOrderPlaced = timeOrderPlaced;
      this.orderItems = orderItems;
   }

   /**
    * @return the firstName
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * @param firstName
    *           the firstName to set
    */
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   /**
    * @return the lastName
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * @param lastName
    *           the lastName to set
    */
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   /**
    * @return the email
    */
   public String getEmail() {
      return email;
   }

   /**
    * @param email
    *           the email to set
    */
   public void setEmail(String email) {
      this.email = email;
   }

   /**
    * @return the orderNumber
    */
   public String getOrderNumber() {
      return orderNumber;
   }

   /**
    * @param orderNumber
    *           the orderNumber to set
    */
   public void setOrderNumber(String orderNumber) {
      this.orderNumber = orderNumber;
   }

   /**
    * @return the timeOrderPlaced
    */
   public Date getTimeOrderPlaced() {
      return timeOrderPlaced;
   }

   /**
    * @param timeOrderPlaced
    *           the timeOrderPlaced to set
    */
   public void setTimeOrderPlaced(Date timeOrderPlaced) {
      this.timeOrderPlaced = timeOrderPlaced;
   }

   /**
    * @return the orderItems
    */
   public List<OrderItem> getOrderItems() {
      return orderItems;
   }

   /**
    * @param orderItems
    *           the orderItems to set
    */
   public void setOrderItems(List<OrderItem> orderItems) {
      this.orderItems = orderItems;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Order [");
      if (firstName != null) {
         builder.append("firstName=");
         builder.append(firstName);
         builder.append(", ");
      }
      if (lastName != null) {
         builder.append("lastName=");
         builder.append(lastName);
         builder.append(", ");
      }
      if (email != null) {
         builder.append("email=");
         builder.append(email);
         builder.append(", ");
      }
      if (orderNumber != null) {
         builder.append("orderNumber=");
         builder.append(orderNumber);
         builder.append(", ");
      }
      if (timeOrderPlaced != null) {
         builder.append("timeOrderPlaced=");
         builder.append(timeOrderPlaced);
         builder.append(", ");
      }
      if (orderItems != null) {
         builder.append("orderItems=");
         builder.append(orderItems);
      }
      builder.append("]");
      return builder.toString();
   }
}
