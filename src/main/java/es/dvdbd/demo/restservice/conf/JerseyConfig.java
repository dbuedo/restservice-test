package es.dvdbd.demo.restservice.conf;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import es.dvdbd.demo.restservice.call.controller.CallController;
import es.dvdbd.demo.restservice.error.ServerExceptionMapper;
import es.dvdbd.demo.restservice.monit.controller.StatusController;


@Configuration
public class JerseyConfig extends ResourceConfig {
	private static final Logger logger = LoggerFactory.getLogger(JerseyConfig.class);
	
    public JerseyConfig() {
    	logger.info("loading JerseyConfig");
        register(StatusController.class);
        register(CallController.class);
        
        register(ServerExceptionMapper.class);
    }
}
