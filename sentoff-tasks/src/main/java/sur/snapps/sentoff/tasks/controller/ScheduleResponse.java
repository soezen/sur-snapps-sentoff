package sur.snapps.sentoff.tasks.controller;

import java.util.ArrayList;
import java.util.List;

public class ScheduleResponse {

	private List<ScheduledTask> scheduledTasks = new ArrayList<>();

	public List<ScheduledTask> getScheduledTasks() {
		return scheduledTasks;
	}
	
	public void setScheduledTasks(List<ScheduledTask> scheduledTasks) {
		this.scheduledTasks = scheduledTasks;
	}
	
	public void addScheduledTask(ScheduledTask scheduledTask) {
		scheduledTasks.add(scheduledTask);
	}
}
