package sur.snapps.sentoff.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import sur.snapps.sentoff.tasks.config.JerseyConfig;
import sur.snapps.sentoff.tasks.config.TasksConfig;

/**
 * @author SUR
 * @since 27/09/2016
 */
@Import({ TasksConfig.class, JerseyConfig.class })
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
