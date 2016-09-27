package sur.snapps.sentoff.tasks.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import sur.snapps.sentoff.reports.config.ReportsConfig;

@Import({ ReportsConfig.class })
@EnableScheduling
@Configuration
@ComponentScan("sur.snapps.sentoff.tasks")
public class TasksConfig {

	
}
