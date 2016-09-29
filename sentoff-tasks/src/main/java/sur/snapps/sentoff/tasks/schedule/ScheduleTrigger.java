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

	public void cancel() {
		boolean cancelled = false;
		if (future != null) {
			cancelled = future.cancel(true);
		}

		if (cancelled) {
			LOG.debug("Cancelled sentoff task " + task.getName());
		} else {
			LOG.debug("Unable to cancel sentoff task " + task.getName());
		}
	}
	
	public void schedule(String cronExpression) {
		cancel();

		if (cronExpression == null) {
			return;
		}

		// TODO are you certain that the overwritten cronTrigger does not keep going?
		delegate = new CronTrigger(cronExpression);
		future = scheduler.schedule((Runnable) () -> {
            LOG.debug("Starting sentoff task " + task.getName());
            try {
                task.run();
                LOG.debug("Finished sentoff task " + task.getName() + " successfully");
            } catch (Throwable t) {
                throw new ScheduledTaskError(t, task);
            }
        }, this);
	}
	
	public String getCronExpression() {
		if (delegate instanceof CronTrigger) {
			return ((CronTrigger) delegate).getExpression();
		}
		return null;
	}

	public String getTaskName() {
		return task.getName();
	}

	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		Date nextExecutionTime = delegate.nextExecutionTime(triggerContext);
		LOG.debug("Scheduling next execution of sentoff task " + task.getName() + " at " + nextExecutionTime);
		return nextExecutionTime;
	}
}
