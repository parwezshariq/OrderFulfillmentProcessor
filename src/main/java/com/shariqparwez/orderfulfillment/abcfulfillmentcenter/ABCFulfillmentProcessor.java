package com.shariqparwez.orderfulfillment.abcfulfillmentcenter;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Processor used by Camel as a beanref to transform one or more order XML
 * messages into a list of Map objects for consumption by a CSV marshaller.
 * 
 * @author Shariq Parwez
 *
 */
@Component
public class ABCFulfillmentProcessor {

   private static final Logger log = LoggerFactory
         .getLogger(ABCFulfillmentProcessor.class);

   /**
    * Camel will call this method and bind the body of the inbound message to
    * the orders List parameter.
    * 
    * @param orders
    * @return
    * @throws Exception
    */
   public List<Map<String, Object>> processAggregate(List orders)
         throws Exception {
      log.info("Processing the aggregate");
      List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

      // 1 - Add the header first
      Map<String, Object> header = new LinkedHashMap<String, Object>(3);
      header.put("orderNumber", "Order Number");
      header.put("firstName", "First Name");
      header.put("lastName", "Last Name");
      results.add(header);

      try {
         if (orders != null) {
            // 2 - Add each order ID
            for (int i = 0; i < orders.size(); i++) {
               com.shariqparwez.orderfulfillment.generated.Order order =
                     unmarshallOrder((String) orders.get(i));
               Map<String, Object> row = new LinkedHashMap<String, Object>(3);
               row.put("orderNumber", order.getOrderType().getOrderNumber());
               row.put("firstName", order.getOrderType().getFirstName());
               row.put("lastName", order.getOrderType().getLastName());
               results.add(row);
            }
         }
      } catch (Exception e) {
         log.error(
               "An error occurred while trying to process messages for the abc fulfillment center: "
                     + e.getMessage(), e);
         throw e;
      }
      return results;
   }

   /**
    * Unmarshalls the XML order into the schema generated objects.
    * 
    * @param orderXml
    * @return
    * @throws Exception
    */
   protected com.shariqparwez.orderfulfillment.generated.Order unmarshallOrder(
         String orderXml) throws Exception {
      // Unmarshall the Order from an XML string to the generated order
      // class.
      JAXBContext context =
            JAXBContext
                  .newInstance(com.shariqparwez.orderfulfillment.generated.Order.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      return (com.shariqparwez.orderfulfillment.generated.Order) unmarshaller
            .unmarshal(new StringReader(orderXml));
   }

}
