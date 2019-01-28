package com.shariqparwez.orderfulfillment.order;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shariqparwez.orderfulfillment.config.Application;

/**
 * Note, this test requires Apache ActiveMQ to be started. The test will send
 * one message to the ActiveMQ queue named ORDER_ITEM_PROCESSING. The message
 * will contain the appropriate fulfillment center name. Once the test is
 * executed, you can verify the message was routed appropriately using the
 * ActiveMQ admin console.
 * 
 * @author Shariq Parwez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@ActiveProfiles("test")
@WebAppConfiguration
public class FulfillmentCenterContentBasedRouterTest {

   public String abcFulfillmentCenterMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Order xmlns=\"http://www.shariqparwez.com/orderfulfillment/Order\"><OrderType><FirstName>Jane</FirstName><LastName>Smith</LastName><Email>jane@somehow.com</Email><OrderNumber>1003</OrderNumber><TimeOrderPlaced>2014-10-24T12:09:21.330-05:00</TimeOrderPlaced><FulfillmentCenter>"
         + com.shariqparwez.orderfulfillment.generated.FulfillmentCenter.ABC_FULFILLMENT_CENTER
               .value()
         + "</FulfillmentCenter><OrderItems><ItemNumber>078-1344200444</ItemNumber><Price>20.00000</Price><Quantity>1</Quantity></OrderItems></OrderType></Order>";
   public String fulfillmentCenter1Message = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Order xmlns=\"http://www.shariqparwez.com/orderfulfillment/Order\"><OrderType><FirstName>Jane</FirstName><LastName>Smith</LastName><Email>jane@somehow.com</Email><OrderNumber>1003</OrderNumber><TimeOrderPlaced>2014-10-24T12:09:21.330-05:00</TimeOrderPlaced><FulfillmentCenter>"
         + com.shariqparwez.orderfulfillment.generated.FulfillmentCenter.FULFILLMENT_CENTER_ONE
               .value()
         + "</FulfillmentCenter><OrderItems><ItemNumber>078-1344200444</ItemNumber><Price>20.00000</Price><Quantity>1</Quantity></OrderItems></OrderType></Order>";

   @EndpointInject(uri = "activemq:queue:ORDER_ITEM_PROCESSING")
   ProducerTemplate producer;

   @Before
   public void setUp() {
   }

   @After
   public void tearDown() {

   }

   @Test
   public void test_sendMessage() throws Exception {
      producer.sendBody(abcFulfillmentCenterMessage);
      // producer.sendBody(fulfillmentCenter1Message);
   }
}
