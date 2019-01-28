package com.shariqparwez.orderfulfillment.fulfillmentcenterone.service;

import java.util.List;

/**
 * Represents an order request to the fulfillment center one RESTful web
 * service.
 * 
 * @author Shariq Parwez
 *
 */
public class OrderRequest {

   private List<Order> orders;

   public OrderRequest() {

   }

   public OrderRequest(List<Order> orders) {
      super();
      this.orders = orders;
   }

   /**
    * @return the orders
    */
   public List<Order> getOrders() {
      return orders;
   }

   /**
    * @param orders
    *           the orders to set
    */
   public void setOrders(List<Order> orders) {
      this.orders = orders;
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("OrderRequest [");
      if (orders != null) {
         builder.append("orders=");
         builder.append(orders);
      }
      builder.append("]");
      return builder.toString();
   }
}
