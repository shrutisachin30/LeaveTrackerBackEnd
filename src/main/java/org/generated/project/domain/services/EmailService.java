package org.generated.project.domain.services;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.seedstack.seed.Bind;

/**
 * <h2>EmailService</h2>
 * <p>
 * This program implements to send the otp to registered email
 * </p>
 * 
 * @author Sourav Donkar
 * @since 2021-10-01
 */

@Bind

public class EmailService {
	public static void main(String[] args) {

	}

	// method to send otp via e-mail
	public void sendmail(String subject, String message, String to, String from) {

		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("leavetracker.atos@gmail.com", "Atos#123");
			}
		});

		session.setDebug(true);

		MimeMessage m = new MimeMessage(session);

		try {

			m.setFrom(from);

			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			m.setSubject(subject);
			m.setContent(message, "text/html");
			Transport.send(m);

			System.out.println("Sent success...................");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
