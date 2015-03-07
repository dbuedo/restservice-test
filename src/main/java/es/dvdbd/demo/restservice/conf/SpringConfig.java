package es.dvdbd.demo.restservice.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  
@ComponentScan(basePackages = "es.dvdbd.demo.restservice")  
public class SpringConfig {  
private static final Logger logger = LoggerFactory.getLogger(SpringConfig.class);
	
    public SpringConfig() {
    	logger.info("loading SpringConfig");
    }
}