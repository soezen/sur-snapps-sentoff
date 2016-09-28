package sur.snapps.sentoff.tasks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import sur.snapps.sentoff.reports.config.ReportsConfig;
import sur.snapps.sentoff.tasks.Tasks;
import sur.snapps.sentoff.tasks.mail.WeeklyMailService;
import sur.snapps.sentoff.tasks.schedule.ScheduleTrigger;

@Import({ ReportsConfig.class })
@EnableScheduling
@Configuration
@ComponentScan("sur.snapps.sentoff.tasks")
@PropertySource("cron.properties")
public class TasksConfig {
	
	@Autowired
	private WeeklyMailService weeklyMailService;
	
	@Value("${cron.reports.amount_spent}")
	private String cronReportSpentAmount;
	
	@Bean
	public TaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
	
	@Bean(name = Tasks.WEEKLY_MAIL_TASK_NAME)
	public ScheduleTrigger scheduleWeeklyMailService() {
		ScheduleTrigger trigger = new ScheduleTrigger(taskScheduler(), weeklyMailService);
		trigger.schedule(cronReportSpentAmount);
		return trigger;
	}
	
}
