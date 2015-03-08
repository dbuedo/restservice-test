package es.dvdbd.demo.restservice.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
	private final static Logger logger = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);
			
	@Override
	public Response toResponse(WebApplicationException exception) {
		logger.error("WebApplicationException : {}", exception.getMessage());
		
		
		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(exception.getResponse().getStatus());
		error.setCode(exception.getResponse().getStatus());
		error.setMessage(exception.getResponse().getStatusInfo().getReasonPhrase());
		
		return Response.status(error.getStatus())
				.entity(error)
				.type(MediaType.APPLICATION_JSON)
				.build(); 
	}
	
}


