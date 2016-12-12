package com.test.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {


	private Properties emailProperties;
	
	/**
	 * @param emailProperties the emailProperties to set
	 */
	public void setEmailProperties(Properties emailProperties) {
		this.emailProperties = emailProperties;
	}


	public void sendMail(String from,String to,String cc,String subject,String messageText,boolean isHtml) throws Exception {
			
		Session session = Session.getInstance(this.emailProperties,null);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		
		InternetAddress[] address = {new InternetAddress(to)};
		
		message.setRecipients(Message.RecipientType.TO, address);
		
		if(cc!=null && !cc.trim().isEmpty()){
			String ccArray[] = new String[10];
			if(cc.contains(",")){
				ccArray = cc.split(",");
				// Adding all email ids in DistributionGroup as CC
				for(int i=0;i<ccArray.length;i++){
					message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(ccArray[i]));
				}
			}
			else{
				message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			}
		}
		
		message.setSubject(subject);
		message.setSentDate(new Date());
		
		if(isHtml)
			message.setContent(messageText, "text/html; charset=utf-8");
		else
			message.setText(messageText);
		
		Transport.send(message);
	}
}
