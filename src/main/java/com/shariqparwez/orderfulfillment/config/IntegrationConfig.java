package com.shariqparwez.orderfulfillment.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.shariqparwez.orderfulfillment.order.OrderStatus;

@Configuration
public class IntegrationConfig extends CamelConfiguration {

	@Inject
	private Environment environment;
	
	@Inject
	private DataSource dataSource;
	
	@Bean
	public SqlComponent sql() {
		SqlComponent sqlComponent = new SqlComponent();
		sqlComponent.setDataSource(dataSource);
		return sqlComponent;
	}
	
	@Bean
	public RouteBuilder newWebsiteOrderRoute() {
		return new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("sql:"
						+ "select id from orders.ordertype where status = '"
						+ OrderStatus.NEW.getCode()
						+ "'"
						+ "?"
						+ "consumer.onConsume=update orders.ordertype set status = '"
						+ OrderStatus.PROCESSING.getCode()
						+ "' where id = :#id")
				.to("log:com.shariqparwez.orderfulfillment.order?level=INFO");
			}
		};
	}
	
	/*@Override
	public List<RouteBuilder> routes(){
		List<RouteBuilder> routeList = new ArrayList<RouteBuilder>();
		
		routeList.add(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				from("file://" + environment.getProperty("order.fulfillment.center.1.outbound.folder")
						+ "?noop=true")
				.to("file://" + environment.getProperty("order.fulfillment.center.1.outbound.folder")
						+ "/test");
			}
		});
		
		return routeList;
	}*/
	
}
