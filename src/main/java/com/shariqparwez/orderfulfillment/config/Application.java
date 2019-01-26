package com.shariqparwez.orderfulfillment.config;

import org.springframework.context.annotation.*;

/**
 * Main application configuration for the order fulfillment processor.
 * 
 * @author Shariq Parwez
 * 
 */
@Configuration
@ComponentScan(basePackages = "com.shariqparwez.orderfulfillment")
@PropertySource("classpath:order-fulfillment.properties")
public class Application {

}
