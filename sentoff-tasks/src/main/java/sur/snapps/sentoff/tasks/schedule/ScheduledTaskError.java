package sur.snapps.sentoff.tasks.schedule;

/**
 * @author sur
 * @since 29/09/2016
 */
public class ScheduledTaskError extends RuntimeException {

    private Task task;

    public ScheduledTaskError(Throwable cause, Task task) {
        super(cause);
        this.task = task;
    }

    public Task getTask() {
        return task;
    }


}
