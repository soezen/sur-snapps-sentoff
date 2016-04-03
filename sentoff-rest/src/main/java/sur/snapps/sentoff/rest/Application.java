package sur.snapps.sentoff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sur.snapps.sentoff.rest.config.DatabaseConfig;
import sur.snapps.sentoff.rest.config.RestConfig;
import sur.snapps.sentoff.rest.config.SwaggerConfig;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@EnableSwagger2
@Import({RestConfig.class, DatabaseConfig.class, SwaggerConfig.class})
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
