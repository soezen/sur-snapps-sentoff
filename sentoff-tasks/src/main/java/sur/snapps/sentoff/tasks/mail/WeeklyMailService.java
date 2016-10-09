package sur.snapps.sentoff.tasks.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sur.snapps.sentoff.reports.AmountSpentReportService;
import sur.snapps.sentoff.tasks.schedule.Task;

@Component
public class WeeklyMailService implements Task {

	public static final String TASK_NAME = "WEEKLY_MAIL";

	@Autowired
	private AmountSpentReportService amountSpentReportService;

	@Override
	public String getName() {
		return TASK_NAME;
	}

	@Override
	public void run() {
		mailReport();
	}
	
	public void mailReport() {
		System.out.println(amountSpentReportService.generateReport());
	}
}
