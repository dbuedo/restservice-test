package es.dvdbd.demo.restservice.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ServerExceptionMapper implements ExceptionMapper<Throwable> {
	private final static Logger logger = LoggerFactory.getLogger(ServerExceptionMapper.class);

	@Override
	public Response toResponse(Throwable exception) {
		logger.error("Internal Error : {}", exception.getMessage());
		
		ErrorResponse error = new ErrorResponse();
		error.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		error.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		error.setMessage("Internal Error. Please contact to service admin.");
		
		return Response.status(error.getStatus())
				.entity(error)
				.type(MediaType.APPLICATION_JSON)
				.build(); 
	}

}
