package sur.snapps.sentoff.tasks.api;

import sur.snapps.sentoff.tasks.schedule.TaskStatus;

import java.util.Date;

public class ScheduledTask {

	private String name;
	private String schedule;
	private TaskStatus status;
	private Date nextExecutionTime;
	
	public String getName() {
		return name;
	}
	
	public String getSchedule() {
		return schedule;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public Date getNextExecutionTime() {
		return nextExecutionTime;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public void setNextExecutionTime(Date nextExecutionTime) {
		this.nextExecutionTime = nextExecutionTime;
	}
}
