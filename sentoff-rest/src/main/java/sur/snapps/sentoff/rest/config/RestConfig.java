package sur.snapps.sentoff.rest.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Configuration
@ComponentScan("sur.snapps.sentoff.domain.check")
public class RestConfig {

    private static final Log LOG = LogFactory.getLog(RestConfig.class);

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v2/api-docs").allowedOrigins("*");
                registry.addMapping("/spending/add").allowedOrigins("*");
            }
        };
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
                System.out.println(message);
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
