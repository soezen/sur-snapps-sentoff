package sur.snapps.sentoff.tasks.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ErrorHandler;

/**
 * @author sur
 * @since 29/09/2016
 */
public class ScheduledTaskErrorHandler implements ErrorHandler {

    private static final Log LOG = LogFactory.getLog(ScheduledTaskErrorHandler.class);

    @Override
    public void handleError(Throwable throwable) {
        if (throwable instanceof ScheduledTaskError) {
            Task task = ((ScheduledTaskError) throwable).getTask();
            LOG.error("Unexpected error occurred in sentoff task " + task.getName());
            LOG.debug("Unexpected error stacktrace", throwable);
        } else  {
            LOG.error("Unexpected error occurred in scheduled task", throwable);
        }
    }
}
