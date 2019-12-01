package codingRound.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import codingRound.commonLib.CommonFunctionsLib;

public class Sendmail {

	public void SendMail(String to, String replyTo, String subject, String path, String username, String password,
			String bodyData) throws IOException {

		final String username_email = username;
		final String password_email = password;

		Properties props = new Properties();
		props.put("mail.smtp.host", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.from", "automation-htdigital@htdigital.in");
		props.put("mail.from.alias", "Automation Report");
		File extPath = new File(path);
		String rep_start_time = extPath.getName();
		String rep_date = rep_start_time.substring(0, 10);
		String rep_time = rep_start_time.substring(11, 23).replace("_", ":");
		String final_subject = subject + " : " + rep_date + " " + rep_time;

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username_email, password_email);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(session.getProperty("mail.from"),
					session.getProperty("mail.from.alias"), "UTF8"));
			message.setReplyTo(InternetAddress.parse(replyTo));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(final_subject);

			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText(bodyData);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);

			CommonFunctionsLib common = new CommonFunctionsLib();
			File directoryToSend = new File(path);
			List<File> fileList = new ArrayList<File>();
			common.getAllFiles(directoryToSend, fileList);

			for (int i = 0; i < fileList.size(); i++) {
				MimeBodyPart messageBodyPart2 = new MimeBodyPart();
				String filename = fileList.get(i).toString();
				DataSource source = new FileDataSource(filename);
				messageBodyPart2.setDataHandler(new DataHandler(source));
				messageBodyPart2.setFileName(fileList.get(i).getName());
				multipart.addBodyPart(messageBodyPart2);
			}
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}

