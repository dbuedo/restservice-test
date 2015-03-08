package es.dvdbd.demo.restservice.rest;

import static org.junit.Assert.*;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import es.dvdbd.demo.restservice.call.controller.CallController;
import es.dvdbd.demo.restservice.call.entity.Call;
import es.dvdbd.demo.restservice.monit.controller.StatusController;
import es.dvdbd.demo.restservice.monit.entity.Status;

public class CallControllerTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(CallController.class);
	}
	
    @Test
    public void testGetAllCalls() {
        Response response = target("call")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        List<Call> callsEntity = (List<Call>) response.readEntity(List.class);
        
        assertFalse(callsEntity.isEmpty()); 
    }
    
    @Test
    public void testGetCallById() {
        Response response = target("call").path("1")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        Call callEntity = response.readEntity(Call.class);        
        assertEquals(Long.valueOf(1), callEntity.getId()); 
    }
    
    
}
