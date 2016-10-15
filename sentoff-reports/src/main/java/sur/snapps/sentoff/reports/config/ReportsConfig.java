package sur.snapps.sentoff.reports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan("sur.snapps.sentoff.reports")
public class ReportsConfig {

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.sendgrid.net");
		mailSender.setPort(587);
		mailSender.setPassword("****");
		mailSender.setUsername("soezen");
		mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
		mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
		return mailSender;
	}
}
