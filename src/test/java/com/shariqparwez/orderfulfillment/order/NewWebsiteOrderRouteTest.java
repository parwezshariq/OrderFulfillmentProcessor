package com.shariqparwez.orderfulfillment.order;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shariqparwez.orderfulfillment.config.IntegrationConfig;
import com.shariqparwez.orderfulfillment.test.TestIntegration;

/**
 * Test case for testing the execution of the SQL component-based route for
 * routing orders from the orders database to a log component.
 * 
 * @author Shariq Parwez
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestIntegration.class,
      IntegrationConfig.class })
public class NewWebsiteOrderRouteTest {

   @Inject
   private JdbcTemplate jdbcTemplate;

   /**
    * Set up test fixture
    * 
    * @throws Exception
    */
   @Before
   public void setUp() throws Exception {
      // Insert catalog and customer data
      jdbcTemplate
            .execute("insert into orders.catalogitem (id, itemnumber, itemname, itemtype) "
                  + "values (1, '078-1344200444', 'Build Your Own JavaScript Framework in Just 24 Hours', 'Book')");
      jdbcTemplate
            .execute("insert into orders.customer (id, firstname, lastname, email) "
                  + "values (1, 'Larry', 'Horse', 'larry@hello.com')");
   }

   /**
    * Tear down all test data.
    * 
    * @throws Exception
    */
   @After
   public void tearDown() throws Exception {
      jdbcTemplate.execute("delete from orders.orderItem");
      jdbcTemplate.execute("delete from orders.ordertype");
      jdbcTemplate.execute("delete from orders.catalogitem");
      jdbcTemplate.execute("delete from orders.customer");
   }

   /**
    * Test the successful routing of a new website order.
    * 
    * @throws Exception
    */
   @Test
   public void testNewWebsiteOrderRouteSuccess() throws Exception {
      jdbcTemplate
            .execute("insert into orders.ordertype (id, customer_id, orderNumber, timeorderplaced, lastupdate, status) "
                  + "values (1, 1, '1001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'N')");
      jdbcTemplate
            .execute("insert into orders.orderitem (id, order_id, catalogitem_id, status, price, quantity, lastupdate) "
                  + "values (1, 1, 1, 'N', 20.00, 1, CURRENT_TIMESTAMP)");
      Thread.sleep(5000);
      int total = jdbcTemplate.queryForObject(
            "select count(id) from orders.ordertype where status = 'P'",
            Integer.class);
      assertEquals(1, total);

   }
}
