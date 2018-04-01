package sur.snapps.sentoff.rest.config;

import javax.servlet.Filter;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.glassfish.jersey.server.model.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
    	super(MyObjectMapperProvider.class);
        registerEndpoints();
        configureSwagger();
    }
    
    static class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

    	private ObjectMapper objectMapper;
    	
    	public MyObjectMapperProvider() {
    		objectMapper = new ObjectMapper();
    		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    		objectMapper.registerModule(new JavaTimeModule());
		}
    	
		@Override
		public ObjectMapper getContext(Class<?> type) {
			return objectMapper;
		}
    	
    }

    private void registerEndpoints() {
        registerFinder(new PackageNamesScanner(new String[] {"sur.snapps.sentoff.rest.controller"}, true));
    }

    private void configureSwagger() {
        registerResources(Resource.builder(ApiListingResource.class).path("/api").build());
        register(SwaggerSerializers.class);
        BeanConfig beanConfig = new BeanConfig();
        // TODO get this from maven or properties file
        beanConfig.setVersion("0.0.1");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/sentoff/");
        beanConfig.setResourcePackage("sur.snapps.sentoff.rest.controller");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }

    @Bean
    private Filter requestLogger() {
    	return new RequestLoggingFilter();
    }
    
}
