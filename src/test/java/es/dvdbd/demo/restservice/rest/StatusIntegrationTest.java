package es.dvdbd.demo.restservice.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;

import es.dvdbd.demo.restservice.call.controller.CallController;
import es.dvdbd.demo.restservice.call.entity.Call;
import es.dvdbd.demo.restservice.monit.controller.StatusController;
import es.dvdbd.demo.restservice.monit.entity.Status;

public class StatusIntegrationTest {
	private static HttpServer httpServer;
    private static WebTarget webTarget;
    private static final URI baseUri = URI.create("http://localhost:9090/restservice/");

    @BeforeClass
    public static void setup() throws Exception {

        ResourceConfig rc = new ResourceConfig(StatusController.class);
        
        httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
        httpServer.start();
        
        Client client = ClientBuilder.newClient();
        webTarget = client.target(baseUri);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        httpServer.shutdown();
    }

    @Test
    public void testGetStatus() {
        Response response = webTarget.path("status")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        Status statusEntity = response.readEntity(Status.class);
        assertEquals("Rest Service OK", statusEntity.getStatus()); 
    }
    
    @Test
    public void testGetStatusNotFound() {
    	Response response = webTarget.path("statusnotfound")
				.request()
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.get();
    	assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testPostStatusNotAllowed() {
        Response response = webTarget.path("status")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.post(Entity.json(null));
        assertEquals(Response.Status.METHOD_NOT_ALLOWED.getStatusCode(), response.getStatus());
    }


}
