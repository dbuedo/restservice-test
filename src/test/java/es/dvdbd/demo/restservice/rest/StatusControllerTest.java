package es.dvdbd.demo.restservice.rest;

import static org.junit.Assert.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import es.dvdbd.demo.restservice.monit.controller.StatusController;
import es.dvdbd.demo.restservice.monit.entity.Status;

public class StatusControllerTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(StatusController.class);
	}

    @Test
    public void testGetStatus() {
        Response response = target("status")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.get();
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        
        Status statusEntity = response.readEntity(Status.class);
        assertEquals(statusEntity.getStatus(), "Rest Service OK"); 
    }
    

    @Test
    public void testPostStatusNotAllowed() {
        Response response = target("status")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.post(Entity.json(null));
        assertEquals(response.getStatus(), Response.Status.METHOD_NOT_ALLOWED.getStatusCode());
    }
}
