package sur.snapps.sentoff.tasks.schedule;

/**
 * @author sur
 * @since 29/09/2016
 */
public class ScheduledTaskError extends RuntimeException {

    private TaskService taskService;

    public ScheduledTaskError(Throwable cause, TaskService taskService) {
        super(cause);
        this.taskService = taskService;
    }

    public TaskService getTaskService() {
        return taskService;
    }


}
