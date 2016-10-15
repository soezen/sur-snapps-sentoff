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
		// TODO use properties file
		// TODO use mailgun api
		mailSender.setHost("smtp.mailgun.org");
		mailSender.setPort(587);
		mailSender.setUsername("postmaster@sandboxfcc1830929c14e5ea22ce311eee2c976.mailgun.org");
		mailSender.setPassword("69fb8a8dc184fdc6e3c9a8692c4e41c7");
		mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
		mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
		mailSender.setDefaultEncoding("UTF-8");
		return mailSender;
	}
}
