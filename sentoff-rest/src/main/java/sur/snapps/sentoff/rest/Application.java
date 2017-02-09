package sur.snapps.sentoff.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
    	new SpringApplicationBuilder(Application.class)
    		.web(true).profiles("deploy").run();
    }
<<<<<<< Updated upstream

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class).profiles("deploy");
    }
=======
    
>>>>>>> Stashed changes
}
