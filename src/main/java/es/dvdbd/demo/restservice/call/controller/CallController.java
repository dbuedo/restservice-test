package es.dvdbd.demo.restservice.call.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.dvdbd.demo.restservice.call.entity.Call;
import es.dvdbd.demo.restservice.call.service.CallService;

@Component
@Path("/call")
@Produces(MediaType.APPLICATION_JSON)
public class CallController {

	private final static Logger logger = LoggerFactory.getLogger(CallController.class);
	
	@Autowired
	private CallService service;
	
	public CallController() {
		logger.info("instanciated CallController");
	}
	
	@GET
	public List<Call> findAll() {
		return service.findAll();
	}
	
	@GET @Path("/{id}")
	public Call findById(@PathParam("id") Long id) {
	    return service.findById(id);
	}
}
