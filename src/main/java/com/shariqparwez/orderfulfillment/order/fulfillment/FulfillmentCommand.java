package com.shariqparwez.orderfulfillment.order.fulfillment;

/**
 * Interface for all commands that handle order fulfillment processing.
 * 
 * @author Shariq Parwez
 */
public interface FulfillmentCommand {

   void execute(FulfillmentContext context);
}
