package es.dvdbd.demo.restservice.conf;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebInitializer // implements WebApplicationInitializer 
{

	private static final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

	//@Override
	public void onStartup(ServletContext ctx) throws ServletException {
		logger.info("initializating web application"); 
		// Listeners
		ctx.addListener(ContextLoaderListener.class);
		ctx.setInitParameter(ContextLoader.CONTEXT_CLASS_PARAM, AnnotationConfigWebApplicationContext.class.getName());
		ctx.setInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "es.dvdbd.demo.restservice.conf");

		// Register Spring Security Filter chain
		// ctx.addFilter(SPRING_SECURITY_FILTER_NAME,
		// DelegatingFilterProxy.class)
		// .addMappingForUrlPatterns(
		// EnumSet.<DispatcherType> of(DispatcherType.REQUEST,
		// DispatcherType.FORWARD), false, "/*");

		// Register Jersey 2.0 servlet
		Dynamic servletRegistration = ctx.addServlet("dispatcher", ServletContainer.class.getName());

		servletRegistration.addMapping("/*");
		servletRegistration.setLoadOnStartup(1);
		servletRegistration.setInitParameter("javax.ws.rs.Application",	JerseyConfig.class.getName());
	}

}
