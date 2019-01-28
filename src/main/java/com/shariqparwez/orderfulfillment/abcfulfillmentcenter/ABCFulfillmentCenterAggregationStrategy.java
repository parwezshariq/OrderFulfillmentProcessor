package com.shariqparwez.orderfulfillment.abcfulfillmentcenter;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aggregate the old and new exchange body content. As a result of aggregation,
 * we should have a list of XML strings.
 * 
 * @author Shariq Parwez
 *
 */
public class ABCFulfillmentCenterAggregationStrategy implements
      org.apache.camel.processor.aggregate.AggregationStrategy {

   private static final Logger log = LoggerFactory
         .getLogger(ABCFulfillmentCenterAggregationStrategy.class);

   /*
    * (non-Javadoc)
    * 
    * @see
    * org.apache.camel.processor.aggregate.AggregationStrategy#aggregate(org
    * .apache.camel.Exchange, org.apache.camel.Exchange)
    */
   @Override
   public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
      // We need to store the list of messages in the old exchange; however,
      // the first new exchange processed will mean the old exchange is null.
      if (oldExchange == null) {
         log.info("Old exchange aggregation");
         // This is the first message to aggregate, replace the body content
         // string with a list.
         List<Object> exchangeList = new ArrayList<Object>();
         exchangeList.add(newExchange.getIn().getBody());
         newExchange.getIn().setBody(exchangeList);
         return newExchange;
      } else {
         // Need to aggregate, add the new message into the old message
         // exchange.
         log.info("New exchange aggregation");
         List<Object> exchangeList = oldExchange.getIn().getBody(List.class);
         exchangeList.add(newExchange.getIn().getBody());
         return oldExchange;
      }
   }

}
