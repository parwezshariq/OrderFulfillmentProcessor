package com.shariqparwez.orderfulfillment.order;

import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMessageTranslator {
	
	private static final Logger log = LoggerFactory.getLogger(OrderItemMessageTranslator.class);

	@Inject
	private OrderService orderService;
	
	public String transformToOrderItemMessage(Map<String, Object> orderIds) {
		String output = null;
		
		try {
			if(orderIds == null) {
				throw new Exception("Order id was not bound to the method via integration framework.");
			}
			if(!orderIds.containsKey("id")) {
				throw new Exception("Could not find a valid key of 'id' for the order ID.");
			}
			if(orderIds.get("id") == null || !(orderIds.get("id") instanceof Long)) {
				throw new Exception("The order ID was not correctly provided or formatted.");
			}
			
			output = orderService.processCreateOrderMessage((Long) orderIds.get("id"));
			
		} catch (Exception e) {
			log.error("Order processing failed: " + e.getMessage(), e);
		}
		
		return output;
	}
	
}
