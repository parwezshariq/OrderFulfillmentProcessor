package com.shariqparwez.orderfulfillment.test;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Spring configuration class used for test execution. Sets up beans to support
 * unit testing.
 * 
 * @author Shariq Parwez
 * 
 */
@Configuration
public class TestIntegration {

   /**
    * DataSource bean for the Apache Derby database.
    * 
    * @return
    */
   @Bean
   public DataSource dataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
      dataSource.setUrl("jdbc:derby:memory:orders;create=true");
      return dataSource;
   }

   /**
    * Spring JDBC Template used for querying the Derby database.
    * 
    * @return
    */
   @Bean
   public JdbcTemplate jdbcTemplate() {
      JdbcTemplate jdbc = new JdbcTemplate(dataSource());
      return jdbc;
   }

   /**
    * Derby database bean used for creating and destroying the derby database as
    * part of the Spring container lifecycle. Note that the bean annotation sets
    * initMethod equal to the DerbyDatabaseBean method create and sets
    * destroyMethod to the DerbyDatabaseBean method destroy.
    * 
    * @return
    */
   @Bean(initMethod = "create", destroyMethod = "destroy")
   public DerbyDatabaseBean derbyDatabaseBean() {
      DerbyDatabaseBean derby = new DerbyDatabaseBean();
      derby.setJdbcTemplate(jdbcTemplate());
      return derby;
   }

}
