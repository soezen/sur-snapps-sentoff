package sur.snapps.sentoff.tasks.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.support.CronTrigger;
import sur.snapps.sentoff.tasks.api.ScheduledTask;
import sur.snapps.sentoff.tasks.api.TaskStatus;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

public class ScheduledTaskTrigger implements Trigger {
	private static final Log LOG = LogFactory.getLog(ScheduledTaskTrigger.class);
	
	private Trigger delegate;
	private TaskScheduler scheduler;
	private TaskService taskService;
	private ScheduledFuture<?> future;
	private ScheduledTask scheduledTask;
	
	public ScheduledTaskTrigger(TaskScheduler scheduler, TaskService taskService) {
		this.scheduler = scheduler;
		this.taskService = taskService;
		this.scheduledTask = new ScheduledTask();
		scheduledTask.setName(taskService.getName());
		scheduledTask.setStatus(TaskStatus.INACTIVE);
	}

	public void cancel() {
		boolean cancelled = false;
		if (future != null) {
			cancelled = future.cancel(true);
		}

		if (cancelled) {
			scheduledTask.setStatus(TaskStatus.INACTIVE);
			scheduledTask.setNextExecutionTime(null);
			LOG.debug("Cancelled sentoff task " + taskService.getName());
		} else {
			LOG.debug("Unable to cancel sentoff task " + taskService.getName());
		}
	}
	
	public void schedule(String cronExpression) {
		cancel();

		if (cronExpression == null) {
			return;
		}

		// TODO are you certain that the overwritten cronTrigger does not keep going?
		delegate = new CronTrigger(cronExpression);
		scheduledTask.setSchedule(cronExpression);

		future = scheduler.schedule((Runnable) () -> {
            LOG.debug("Starting sentoff task " + taskService.getName());
            try {
                taskService.run();
                LOG.debug("Finished sentoff task " + taskService.getName() + " successfully");
            } catch (Throwable t) {
                throw new ScheduledTaskError(t, taskService);
            }
        }, this);
		scheduledTask.setStatus(TaskStatus.ACTIVE);
	}

	public ScheduledTask getScheduledTask() {
		return scheduledTask;
	}

	@Override
	public Date nextExecutionTime(TriggerContext triggerContext) {
		Date nextExecutionTime = delegate.nextExecutionTime(triggerContext);
		scheduledTask.setNextExecutionTime(nextExecutionTime);
		LOG.debug("Scheduling next execution of sentoff task " + taskService.getName() + " at " + nextExecutionTime);
		return nextExecutionTime;
	}
}
