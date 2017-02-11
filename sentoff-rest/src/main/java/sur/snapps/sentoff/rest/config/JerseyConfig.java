package sur.snapps.sentoff.rest.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.glassfish.jersey.server.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import sur.snapps.sentoff.domain.Message;
import sur.snapps.sentoff.domain.repo.MessageRepository;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    private static final Log LOG = LogFactory.getLog(JerseyConfig.class);
    
    @Autowired
    private MessageRepository messageRepository;

    public JerseyConfig() {
        registerEndpoints();
        configureSwagger();
        configureJackson();
    }

    private void registerEndpoints() {
        registerFinder(new PackageNamesScanner(new String[] {"sur.snapps.sentoff.rest.controller"}, true));
    }

    private void configureJackson() {
        register(JacksonFeature.class);
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
    public Filter requestLogger(){
        AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

            @Override
            protected void beforeRequest(HttpServletRequest request, String message) {
            }

            @Override
            protected void afterRequest(HttpServletRequest request, String message) {
                LOG.debug(message);
                if ("GET".equals(request.getMethod())) {
                	return;
                }
            	Message msg = new Message();
            	msg.setDate(new Date());
            	msg.setMethod(request.getMethod());
            	msg.setUri(request.getRequestURI());
            	ContentCachingRequestWrapper wrapper =
    					WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
            	String payload = null;
				if (wrapper != null) {
    				byte[] buf = wrapper.getContentAsByteArray();
    				if (buf.length > 0) {
    					int length = Math.min(buf.length, getMaxPayloadLength());
    					try {
    						payload = new String(buf, 0, length, wrapper.getCharacterEncoding());
    					}
    					catch (UnsupportedEncodingException ex) {
    						payload = "[unknown]";
    					}
    				}
    			}
				// TODO cleanup method
				if (payload == null) {
					return;
				}
            	msg.setPayload(payload);
            	messageRepository.addMessage(msg);
            }

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                MyHttpServletResponseWrapper responseWrapper = new MyHttpServletResponseWrapper(response);
                super.doFilterInternal(request, responseWrapper, filterChain);
                LOG.debug(responseWrapper.getOutputStream().toString());
            }
        };
        f.setIncludePayload(true);
        f.setIncludeQueryString(true);
        f.setAfterMessagePrefix("REQUEST : ");
        f.setAfterMessageSuffix("");
        return f;
    }

    private class MyHttpServletResponseWrapper extends HttpServletResponseWrapper {

        private ServletOutputStream outputStream;

        MyHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
            super(response);
            outputStream = new MyServletOutputStream(response.getOutputStream());
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return outputStream;
        }
    }

    private class MyServletOutputStream extends ServletOutputStream {

        private ServletOutputStream original;
        private StringBuilder stringBuilder;

        MyServletOutputStream(ServletOutputStream original) {
            this.original = original;
            stringBuilder = new StringBuilder("RESPONSE : ");
        }

        @Override
        public void write(int b) throws IOException {
            original.write(b);
        }

        @Override
        public boolean isReady() {
            return original.isReady();
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            original.setWriteListener(writeListener);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            stringBuilder.append(new String(b, off, len, "UTF-8"));
            original.write(b, off, len);
        }

        @Override
        public String toString() {
            return stringBuilder.toString();
        }
    }
}
