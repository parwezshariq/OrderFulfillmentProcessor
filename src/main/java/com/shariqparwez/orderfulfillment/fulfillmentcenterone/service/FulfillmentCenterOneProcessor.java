package com.shariqparwez.orderfulfillment.fulfillmentcenterone.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.shariqparwez.orderfulfillment.generated.OrderItemType;
import com.shariqparwez.orderfulfillment.generated.OrderType;

/**
 * Processor for the fulfillment center one restful web service route. Accepts
 * the order XML from the exchange, converts it to JSON format and then returns
 * the JSON as a string.
 * 
 * @author Shariq Parwez
 *
 */
@Component
public class FulfillmentCenterOneProcessor {

   private static final Logger log = LoggerFactory
         .getLogger(FulfillmentCenterOneProcessor.class);

   /**
    * Accepts the order XML from the route exchange's inbound message body and
    * then converts it to JSON format.
    * 
    * @param orderXml
    * @return
    */
   public String transformToOrderRequestMessage(String orderXml) {
      String output = null;
      try {
         if (orderXml == null) {
            throw new Exception(
                  "Order xml was not bound to the method via integration framework.");
         }

         output = processCreateOrderRequestMessage(orderXml);
      } catch (Exception e) {
         log.error(
               "Fulfillment center one message translation failed: "
                     + e.getMessage(), e);
      }
      return output;
   }

   protected String processCreateOrderRequestMessage(String orderXml)
         throws Exception {
      // 1 - Unmarshall the Order from an XML string to the generated order
      // class.
      JAXBContext context =
            JAXBContext
                  .newInstance(com.shariqparwez.orderfulfillment.generated.Order.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      com.shariqparwez.orderfulfillment.generated.Order order =
            (com.shariqparwez.orderfulfillment.generated.Order) unmarshaller
                  .unmarshal(new StringReader(orderXml));

      // 2 - Build an Order Request object and return the JSON-ified object
      return new Gson().toJson(buildOrderRequestType(order));
   }

   protected OrderRequest buildOrderRequestType(
         com.shariqparwez.orderfulfillment.generated.Order orderFromXml) {
      OrderType orderTypeFromXml = orderFromXml.getOrderType();

      // 1 - Build order item types
      List<OrderItemType> orderItemTypesFromXml =
            orderTypeFromXml.getOrderItems();
      List<com.shariqparwez.orderfulfillment.fulfillmentcenterone.service.OrderItem> orderItems =
            new ArrayList<com.shariqparwez.orderfulfillment.fulfillmentcenterone.service.OrderItem>();
      for (OrderItemType orderItemTypeFromXml : orderItemTypesFromXml) {
         orderItems
               .add(new com.shariqparwez.orderfulfillment.fulfillmentcenterone.service.OrderItem(
                     orderItemTypeFromXml.getItemNumber(), orderItemTypeFromXml
                           .getPrice(), orderItemTypeFromXml.getQuantity()));
      }

      // 2 - Build order
      List<com.shariqparwez.orderfulfillment.fulfillmentcenterone.service.Order> orders =
            new ArrayList<com.shariqparwez.orderfulfillment.fulfillmentcenterone.service.Order>();
      com.shariqparwez.orderfulfillment.fulfillmentcenterone.service.Order order =
            new com.shariqparwez.orderfulfillment.fulfillmentcenterone.service.Order();
      order.setFirstName(orderTypeFromXml.getFirstName());
      order.setLastName(order.getLastName());
      order.setEmail(orderTypeFromXml.getEmail());
      order.setOrderNumber(orderTypeFromXml.getOrderNumber());
      order.setTimeOrderPlaced(orderTypeFromXml.getTimeOrderPlaced()
            .toGregorianCalendar().getTime());
      order.setOrderItems(orderItems);
      orders.add(order);

      // 3 - Build order request
      OrderRequest orderRequest = new OrderRequest();
      orderRequest.setOrders(orders);

      // 4 - Return the order request
      return orderRequest;
   }

}
