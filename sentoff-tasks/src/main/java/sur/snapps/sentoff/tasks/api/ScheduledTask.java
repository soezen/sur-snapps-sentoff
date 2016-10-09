package sur.snapps.sentoff.tasks.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	public Map<String, Object> getActions() {
		Map<String, Object> actions = new HashMap<>();
		// TODO provide absolute url?
		actions.put("EXECUTE", new Action("/" + getName() + "/execute", "POST"));
		if (status.equals(TaskStatus.ACTIVE)) {
			actions.put("CANCEL", new Action("/" + getName() + "/cancel", "POST"));
		}
		return actions;
	}
	
	class Action {
		String url;
		String method;
		
		public Action(String url, String method) {
			this.url = url;
			this.method = method;
		}
		
		public String getMethod() {
			return method;
		}
		
		public String getUrl() {
			return url;
		}
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
