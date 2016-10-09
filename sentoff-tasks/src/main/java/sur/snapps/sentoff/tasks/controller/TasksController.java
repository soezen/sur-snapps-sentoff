package sur.snapps.sentoff.tasks.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sur.snapps.sentoff.tasks.schedule.ScheduleTrigger;

@Component
@Path("/tasks")
public class TasksController {

	@Autowired
	private Map<String, ScheduleTrigger> scheduledTasks;
	
	@GET
	@Path("/schedule")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleResponse schedule() {
		ScheduleResponse response = new ScheduleResponse();
		for (Entry<String, ScheduleTrigger> entry : scheduledTasks.entrySet()) {
			ScheduleTrigger trigger = entry.getValue();
			ScheduledTask scheduledTask = new ScheduledTask();
			// TODO add links to possible actions on a task
			scheduledTask.setName(entry.getKey());
			scheduledTask.setSchedule(trigger.getCronExpression());
			response.addScheduledTask(scheduledTask);
		}
		return response;
	}
	
	// TODO logging with aop on ReschedulingRunnable
	// TODO cancel scheduled tasks
	// TODO security on certain operations
}
