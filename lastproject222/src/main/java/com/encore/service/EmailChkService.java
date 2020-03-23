package com.encore.service;

import com.sun.mail.smtp.SMTPTransport;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailChkService {
	public void send(String username, String password, String recipientEmail, String ccEmail, String title,
			String message) throws AddressException, MessagingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtps.auth", "true");

		props.put("mail.smtps.quitwait", "false");

		Session session = Session.getInstance(props, null);

		final MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress("uncleminsung@gmail.com"));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
		}

		msg.setSubject(title);
		msg.setContent(message, "text/html; charset=utf-8");
		msg.setSentDate(new Date());
		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
		t.connect("smtp.gmail.com", username, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();

	}
}
