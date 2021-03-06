package sur.snapps.sentoff.tasks.config;

import java.io.IOException;

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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    private static final Log LOG = LogFactory.getLog(JerseyConfig.class);

    // TODO common with sentoff-rest jersey config, refactor
    
    public JerseyConfig() {
        registerEndpoints();
        configureJackson();
    }

    private void registerEndpoints() {
        registerFinder(new PackageNamesScanner(new String[] {"sur.snapps.sentoff.tasks.controller"}, true));
    }

    private void configureJackson() {
        register(JacksonFeature.class);
    }

    @Bean
    public Filter requestLogger(){
        AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

            @Override
            protected void beforeRequest(HttpServletRequest request, String message) {
                // do nothing
            }

            @Override
            protected void afterRequest(HttpServletRequest request, String message) {
                LOG.debug(message);
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
