package sur.snapps.sentoff.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Configuration
@ComponentScan({"sur.snapps.sentoff.domain.repo", "sur.snapps.sentoff.domain.mapper", "sur.snapps.sentoff.rest.util"})
public class DatabaseConfig {

    @Resource
    private Environment env;

    @Bean
    @Profile({"local", "deploy"})
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        return dataSource;
    }

}
