package Strategy;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email implements Str {

	private String msgBody = null;
	private String from = "ukmaDorm@ukma.kiev.ua";
	private String subject = "Звіт";
	
	public Email(String s){
		this.msgBody = s;
	}
	@Override
	public void doIt() {
		
		Charset.forName("cp1251").encode(msgBody);
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
				
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from, this.from));	
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"slavko.kasprishin@gmail.com", "Поселення в НаУКМА"));
			msg.setSubject(subject);
			msg.setText(msgBody);
			msg.setContent(msgBody, "text/html; charset=utf-8");
			Transport.send(msg);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	
		
	}

}
