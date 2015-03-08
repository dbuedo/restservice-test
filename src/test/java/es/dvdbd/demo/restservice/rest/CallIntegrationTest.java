package es.dvdbd.demo.restservice.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;

import es.dvdbd.demo.restservice.call.controller.CallController;
import es.dvdbd.demo.restservice.call.entity.Call;

public class CallIntegrationTest {
	private static HttpServer httpServer;
    private static WebTarget webTarget;
    private static final URI baseUri = URI.create("http://localhost:9090/restservice/");

    @BeforeClass
    public static void setup() throws Exception {
        ResourceConfig rc = new ResourceConfig(CallController.class);
        Set set = rc.getSingletons();
        
        
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
    public void testGetAllCalls() {
        Response response = webTarget.path("call")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        List<Call> callsEntity = (List<Call>) response.readEntity(List.class);
        
        assertFalse(callsEntity.isEmpty()); 
    }
    
    @Test
    public void testGetCallById() {
        Response response = webTarget.path("call").path("1")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        Call callEntity = response.readEntity(Call.class);        
        assertEquals(Long.valueOf(1), callEntity.getId()); 
    }
    
    @Test
    public void testGetCallByUnkownId() {
        Response response = webTarget.path("call").path("9999")
        						.request()
        						.accept(MediaType.APPLICATION_JSON_VALUE)
        						.get();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
    
}
