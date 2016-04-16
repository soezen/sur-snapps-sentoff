package sur.snapps.sentoff.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sur
 * @since 16/04/2016
 */
@Configuration
@ComponentScan("sur.snapps.sentoff.rest.controller")
public class RestConfig {

    @Bean
    public JerseyConfig jerseyConfig() {
        return new JerseyConfig();
    }
}
