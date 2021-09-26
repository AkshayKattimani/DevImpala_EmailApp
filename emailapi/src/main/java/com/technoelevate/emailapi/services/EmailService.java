package com.technoelevate.emailapi.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String to, String subject, String message) {
		boolean status = false;
		String from = "devimpala9@gmail.com";

//		Gmail variable
		String host = "smtp.gmail.com";
//       get properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES: " + properties);

//       SETTING PROPERTIES
//       host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//       step 1: to get session object..
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("devimpala9@gmail.com", "8050195884");
			}

		});
		session.setDebug(true);

//       step 2: compose message[text, multimedia]
		MimeMessage m = new MimeMessage(session);

		try {

//    	   from email
			m.setFrom(from);

//		adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//		adding subject to message
			m.setSubject(subject);

//		adding text to message
			m.setText(message);

//		step 3: sending message using transport class
			Transport.send(m);
			System.out.println("Mail sent...");
			status = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

}
