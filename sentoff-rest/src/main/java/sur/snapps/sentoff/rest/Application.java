package sur.snapps.sentoff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import sur.snapps.sentoff.rest.config.CheckConfig;
import sur.snapps.sentoff.rest.config.DatabaseConfig;
import sur.snapps.sentoff.rest.config.RestConfig;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Import({DatabaseConfig.class, CheckConfig.class, RestConfig.class})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class).profiles("deploy");
    }
}
