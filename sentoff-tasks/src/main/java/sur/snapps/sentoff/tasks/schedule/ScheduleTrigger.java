package sur.snapps.sentoff.tasks.schedule;

import java.util.concurrent.ScheduledFuture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class ScheduleTrigger {
	private static final Log LOG = LogFactory.getLog(ScheduleTrigger.class);
	
	private CronTrigger cronTrigger;
	private TaskScheduler scheduler;
	private Task task;
	private ScheduledFuture<?> future;
	
	public ScheduleTrigger(TaskScheduler scheduler, Task task) {
		this.scheduler = scheduler;
		this.task = task;
	}
	
	public void schedule(String cronExpression) {
		if (future != null) {
			LOG.debug("Cancelling task : " + task);
			future.cancel(true);
		}
		// TODO are you certain that the overwritten cronTrigger does not keep going?
		cronTrigger = new CronTrigger(cronExpression);
		future = scheduler.schedule(task, cronTrigger);
	}
	
	public String getCronExpression() {
		return cronTrigger == null ? null : cronTrigger.getExpression();
	}
	
}
