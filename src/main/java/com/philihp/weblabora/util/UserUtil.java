package com.philihp.weblabora.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Hex;

import com.philihp.weblabora.jpa.User;

public class UserUtil {
	
	private UserUtil() {
	}
	
	public static String md5(String srcString) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		final MessageDigest md = MessageDigest.getInstance("MD5");
		final byte[] hash = md.digest(srcString.getBytes("UTF-8"));
		return Hex.encodeHexString(hash);
	}

	public static void sendEmail(String recepient, String subject,  String text, final String password) throws MessagingException,
			AddressException {
		Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		//props.put("mail.debug", "true");
		//props.put("mail.smtp.debug", "true");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("weblabora.app@gmail.com", password);
			}
		  });

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("weblabora.app@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient, false));
		message.setSubject(subject);
		message.setText(text);
		Transport.send(message);
		System.out.println("Message Sent");
	}
}
