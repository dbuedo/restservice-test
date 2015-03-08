package es.dvdbd.demo.restservice.monit.controller;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import es.dvdbd.demo.restservice.monit.entity.Status;

@Component
@Path("/status")
public class StatusController {

	private final static Logger logger = LoggerFactory.getLogger(StatusController.class);
	
	public StatusController() {
		logger.info("instanciated StatusController");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Status status() {
		return new Status("Rest Service OK");
	}
	
}
