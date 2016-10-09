package sur.snapps.sentoff.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class AmountSpentReportService {

	@Autowired
	private JavaMailSender mailSender;
	
	public String generateReport() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("rogge.suzan@gmail.com");
		message.setFrom("noreply@sentoff.snapps.sur");
		message.setText("Test");
		mailSender.send(message);
		return "REPORT";
	}
}

//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