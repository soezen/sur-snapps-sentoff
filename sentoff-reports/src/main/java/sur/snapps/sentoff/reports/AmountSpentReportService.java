package sur.snapps.sentoff.reports;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import sur.snapps.sentoff.reports.svg.SVGGenerator;

@Component
public class AmountSpentReportService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SVGGenerator svgGenerator;
	
	public String generateReport() {
		try {
		    // TODO make mail service
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.addTo("rogge.suzan@gmail.com");
			helper.setFrom("noreply@sentoff.snapps.sur");
			svgGenerator.generateSVG();
			helper.setText("<html><body><img src='cid:test'></img></body></html>", true);
			helper.addInline("test", ResourceUtils.getFile("classpath:out.jpg"));
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR sending mail";
		}
		return "REPORT";
		

		// TODO log failures (in db or file, or both?)
	}
}


// TODO subscribe + unsubscribe via rest link
// TODO save emails in db 

//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