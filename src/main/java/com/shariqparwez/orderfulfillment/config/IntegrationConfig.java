package com.shariqparwez.orderfulfillment.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsConfiguration;
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
	public ConnectionFactory jmsConnectionFactory() {
		return new ActiveMQConnectionFactory(environment.getProperty("activemq.broker.url"));
	}
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public PooledConnectionFactory pooledConnectionFactory() {
		PooledConnectionFactory factory = new PooledConnectionFactory();
		factory.setConnectionFactory(jmsConnectionFactory());
		factory.setMaxConnections(Integer.parseInt(environment.getProperty("pooledConnectionFactory.maxConnections")));
		return factory;
	}
	
	@Bean
	public JmsConfiguration jmsConfiguration() {
		JmsConfiguration jmsConfiguration = new JmsConfiguration();
		jmsConfiguration.setConnectionFactory(pooledConnectionFactory());
		return jmsConfiguration;
	}
	
	@Bean
	public ActiveMQComponent activeMq() {
		ActiveMQComponent activeMq = new ActiveMQComponent();
		activeMq.setConfiguration(jmsConfiguration());
		return activeMq;
	}
	
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
				.beanRef("orderItemMessageTranslator", "transformToOrderItemMessage")
				//.to("log:com.shariqparwez.orderfulfillment.order?level=INFO");
				.to("activemq:queue:ORDER_ITEM_PROCESSING");
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
