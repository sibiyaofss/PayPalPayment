package com.paypal.resourceLoader;
/**
* Class to load all property files in the application
*
* @author  Antony Sibiya J
* @version 1.0
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
@Configuration
@PropertySource("classpath:paymentqlquery.properties")
public class PaymentResLoader {
	
	
	@Autowired
    Environment env;
	
	public String getValue(String key)
	{
		return env.getProperty(key);
	}
	
	public String lookUpSQL(String key)
	{
		return env.getProperty(key);
	}
     
	
	
	
}
