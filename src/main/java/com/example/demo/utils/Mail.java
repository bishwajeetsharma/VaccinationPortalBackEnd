/**
 * 
 */
package com.example.demo.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author PRATAP
 *
 */
public class Mail {

	public static String sendMail(String[] recipients, String subject, String messageContent) {
		System.out.println("checking mail part");

		// Sender's email ID needs to be mentioned
		String from = "medicalreliefassoc@gmail.com";

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("medicalreliefassoc@gmail.com", "AvraKedavra@12345");

			}

		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			InternetAddress[] recipientAddress = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				recipientAddress[i] = new InternetAddress(recipients[i].trim());
			}

			// Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setRecipients(Message.RecipientType.TO, recipientAddress);

			// Set Subject: header field
			message.setSubject(subject.trim());

			// Now set the actual message
			message.setText(messageContent.trim());

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return "";
	}
}
