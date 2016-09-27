package sur.snapps.sentoff.tasks.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sur.snapps.sentoff.reports.AmountSpentReportService;

@Component
public class WeeklyMailService {

	@Autowired
	private AmountSpentReportService amountSpentReportService;
	
	@Scheduled(cron = "${cron.reports.amount_spent}")
	public void mailReport() {
		System.out.println(amountSpentReportService.generateReport());
	}
}
