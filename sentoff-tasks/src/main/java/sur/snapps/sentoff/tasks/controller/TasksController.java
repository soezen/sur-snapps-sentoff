package sur.snapps.sentoff.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.tasks.api.ScheduleResponse;
import sur.snapps.sentoff.tasks.schedule.ScheduledTaskTrigger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Component
@Path("/tasks")
public class TasksController {

	@Autowired
	private Map<String, ScheduledTaskTrigger> scheduledTasks;
	
	@GET
	@Path("/schedule")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleResponse schedule() {
		ScheduleResponse response = new ScheduleResponse();
		for (ScheduledTaskTrigger trigger : scheduledTasks.values()) {
			response.addScheduledTask(trigger.getScheduledTask());
		}
		return response;
	}


	@POST
	@Path("/{taskName}/cancel")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleResponse cancel(@PathParam("taskName") String taskName) {
		if (!scheduledTasks.containsKey(taskName)) {
			return new ScheduleResponse();
		}
		ScheduledTaskTrigger trigger = scheduledTasks.get(taskName);
		trigger.cancel();

		ScheduleResponse response = new ScheduleResponse();
		response.addScheduledTask(trigger.getScheduledTask());
		return response;
	}
	
	@POST
	@Path("/{taskName}/execute")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleResponse execute(@PathParam("taskName") String taskName) {
		if (!scheduledTasks.containsKey(taskName)) {
			return new ScheduleResponse();
		}
		ScheduledTaskTrigger trigger = scheduledTasks.get(taskName);
		trigger.execute();
		

		ScheduleResponse response = new ScheduleResponse();
		response.addScheduledTask(trigger.getScheduledTask());
		return response;
	}


		// TODO add links to possible actions on a task

	// TODO cancel scheduled tasks
	// TODO security on certain operations
}
