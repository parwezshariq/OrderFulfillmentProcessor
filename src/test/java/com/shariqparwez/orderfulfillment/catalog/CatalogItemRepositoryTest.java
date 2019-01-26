package com.shariqparwez.orderfulfillment.catalog;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shariqparwez.orderfulfillment.catalog.CatalogItemEntity;
import com.shariqparwez.orderfulfillment.catalog.CatalogItemRepository;
import com.shariqparwez.orderfulfillment.order.OrderItemEntity;
import com.shariqparwez.orderfulfillment.test.BaseJpaRepositoryTest;

public class CatalogItemRepositoryTest extends BaseJpaRepositoryTest {

   @Inject
   private CatalogItemRepository catalogItemRepository;
   
   @Before
   public void setUp() throws Exception {
   }

   @After
   public void tearDown() throws Exception {
   }

   @Test
   public void testFindAllSuccess() {
      List<CatalogItemEntity> catalogItems = catalogItemRepository.findAll();
      assertNotNull(catalogItems);
      assertFalse(catalogItems.isEmpty());
   }

   @Test
   public void testOrderOrderItemsSuccess() {
      List<CatalogItemEntity> catalogItems = catalogItemRepository.findAll();
      assertNotNull(catalogItems);
      assertFalse(catalogItems.isEmpty());
      CatalogItemEntity catalogItem = catalogItems.get(0);
      Set<OrderItemEntity> orderItems = catalogItem.getOrderItems();
      assertNotNull(orderItems);
      assertFalse(orderItems.isEmpty());
   }
}
