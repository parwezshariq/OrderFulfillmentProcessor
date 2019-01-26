package com.shariqparwez.orderfulfillment.test;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Bean for creating and destroying the orders database inside Apache Derby.
 * 
 * @author Shariq Parwez
 * 
 */
public class DerbyDatabaseBean {

   private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

   /**
    * Called by Spring bean initialization. Creates the schema and database
    * table structure for the orders database.
    * 
    * @throws Exception
    */
   public void create() throws Exception {

      try {
         jdbcTemplate.execute("drop table orders.orderItem");
         jdbcTemplate.execute("drop table orders.ordertype");
         jdbcTemplate.execute("drop table orders.catalogitem");
         jdbcTemplate.execute("drop table orders.customer");
         jdbcTemplate.execute("drop schema orders");
      } catch (Throwable e) {
      }

      jdbcTemplate.execute("CREATE SCHEMA orders");
      jdbcTemplate
            .execute("create table orders.customer (id integer not null, firstName varchar(200) not null, lastName varchar(200) not null, email varchar(200) not null, primary key (id))");
      jdbcTemplate
            .execute("create table orders.catalogitem (id integer not null, itemNumber varchar(200) not null, itemName varchar(200) not null, itemType varchar(200) not null, primary key (id))");
      jdbcTemplate
            .execute("create table orders.ordertype (id integer not null, customer_id integer not null, orderNumber varchar(200) not null, timeOrderPlaced timestamp not null, lastUpdate timestamp not null, status varchar(200) not null, primary key (id))");
      jdbcTemplate
            .execute("alter table orders.ordertype add constraint orders_fk_1 foreign key (customer_id) references orders.customer (id)");
      jdbcTemplate
            .execute("create table orders.orderItem (id integer not null, order_id integer not null, catalogitem_id integer not null, status varchar(200) not null, price decimal(20,5), lastUpdate timestamp not null, quantity integer not null, primary key (id))");
      jdbcTemplate
            .execute("alter table orders.orderItem add constraint orderItem_fk_1 foreign key (order_id) references orders.ordertype (id)");
      jdbcTemplate
            .execute("alter table orders.orderItem add constraint orderItem_fk_2 foreign key (catalogitem_id) references orders.catalogitem (id)");
   }

   /**
    * Tears down the orders database in Apache Derby as part of the Spring
    * container life-cycle.
    * 
    * @throws Exception
    */
   public void destroy() throws Exception {

      try {
         jdbcTemplate.execute("drop table orders.orderItem");
         jdbcTemplate.execute("drop table orders.ordertype");
         jdbcTemplate.execute("drop table orders.catalogitem");
         jdbcTemplate.execute("drop table orders.customer");
         jdbcTemplate.execute("drop schema orders");
      } catch (Throwable e) {
         // ignore
      }
   }

   /**
    * @param jdbcTemplate
    *           the jdbcTemplate to set
    */
   public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
   }

}
