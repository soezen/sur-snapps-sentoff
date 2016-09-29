package sur.snapps.sentoff.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.tasks.api.ScheduleResponse;
import sur.snapps.sentoff.tasks.api.ScheduledTask;
import sur.snapps.sentoff.tasks.mail.WeeklyMailService;
import sur.snapps.sentoff.tasks.schedule.ScheduleTrigger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

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
		for (ScheduleTrigger trigger : scheduledTasks.values()) {
			response.addScheduledTask(getScheduledTask(trigger));
		}
		return response;
	}


	// TODO add task name in path
	@POST
	@Path("/cancel")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleResponse cancel() {
		ScheduleTrigger trigger = scheduledTasks.get(WeeklyMailService.TASK_NAME);
		trigger.cancel();

		ScheduleResponse response = new ScheduleResponse();
		response.addScheduledTask(getScheduledTask(trigger));
		return response;
	}

	private ScheduledTask getScheduledTask(ScheduleTrigger trigger) {
		// TODO put this method in a mapper
		ScheduledTask scheduledTask = new ScheduledTask();
		// TODO add links to possible actions on a task
		scheduledTask.setName(trigger.getTaskName());
		scheduledTask.setSchedule(trigger.getCronExpression());
		return scheduledTask;
	}

	// TODO cancel scheduled tasks
	// TODO security on certain operations
}
