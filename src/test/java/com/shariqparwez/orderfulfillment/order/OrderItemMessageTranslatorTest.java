package com.shariqparwez.orderfulfillment.order;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.shariqparwez.orderfulfillment.catalog.CatalogItemEntity;
import com.shariqparwez.orderfulfillment.catalog.CatalogItemRepository;
import com.shariqparwez.orderfulfillment.config.Application;
import com.shariqparwez.orderfulfillment.customer.CustomerEntity;
import com.shariqparwez.orderfulfillment.customer.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@ActiveProfiles("test")
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager")
@TestExecutionListeners(
      listeners = { DependencyInjectionTestExecutionListener.class,
            DirtiesContextTestExecutionListener.class,
            TransactionalTestExecutionListener.class })
@Transactional
public class OrderItemMessageTranslatorTest {

   @Inject
   private OrderItemMessageTranslator translator;

   @Inject
   private CustomerRepository customerRepository;

   @Inject
   private CatalogItemRepository catalogItemRepository;

   @Inject
   private OrderItemRepository orderItemRepository;

   @Inject
   private OrderRepository orderRepository;

   private Long orderId;

   @Before
   public void setUp() {
      CustomerEntity customer = new CustomerEntity();
      customer.setFirstName("First");
      customer.setLastName("Last");
      customer.setEmail("firstlast@test.com");
      customerRepository.save(customer);
      CatalogItemEntity catalogItem = new CatalogItemEntity();
      catalogItem.setItemName("TestItem");
      catalogItem.setItemNumber("1234X");
      catalogItem.setItemType("AnItemType");
      catalogItemRepository.save(catalogItem);
      OrderEntity order = new OrderEntity();
      order.setCustomer(customer);
      order.setLastUpdate(new Date(System.currentTimeMillis()));
      order.setOrderNumber("1234");
      order.setStatus("N");
      order.setTimeOrderPlaced(new Date(System.currentTimeMillis()));
      orderRepository.save(order);
      orderId = order.getId();
      OrderItemEntity orderItem = new OrderItemEntity();
      orderItem.setCatalogItem(catalogItem);
      orderItem.setLastUpdate(new Date(System.currentTimeMillis()));
      orderItem.setPrice(new BigDecimal(1));
      orderItem.setQuantity(1);
      orderItem.setStatus("N");
      orderItem.setOrder(order);
      orderItemRepository.save(orderItem);
   }

   @Test
   public void test_transformToOrderItemMessageSuccess() throws Exception {
      Map<String, Object> orderIds = new HashMap<String, Object>();
      orderIds.put("id", orderId);
      String xml = translator.transformToOrderItemMessage(orderIds);
      System.err.println(xml);
      assertNotNull(xml);
   }
}
