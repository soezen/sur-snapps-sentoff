package sur.snapps.sentoff.tasks.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

public class ScheduleTrigger implements Trigger {
	private static final Log LOG = LogFactory.getLog(ScheduleTrigger.class);
	
	private Trigger delegate;
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
		delegate = new CronTrigger(cronExpression);
		future = scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				LOG.debug("Starting sentoff task " + task.getName());
				try {
					task.run();
					LOG.debug("Finished sentoff task " + task.getName() + " successfully");
				} catch (Throwable t) {
					throw new ScheduledTaskError(t, task);
				}
			}
		}, this);
	}
	
	public String getCronExpression() {
		if (delegate instanceof CronTrigger) {
			return ((CronTrigger) delegate).getExpression();
		}
		return null;
	}

	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		Date nextExecutionTime = delegate.nextExecutionTime(triggerContext);
		LOG.debug("Scheduling next execution of sentoff task " + task.getName() + " at " + nextExecutionTime);
		return nextExecutionTime;
	}
}
