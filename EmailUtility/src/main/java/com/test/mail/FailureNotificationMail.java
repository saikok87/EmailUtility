/**
 * 
 */
package com.test.mail;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class FailureNotificationMail extends SendMailActivity {

	
	@Value("$prop{test.failure.mail.toAddr}")
	private String toAddress;
	
//	@Value("${test.failure.mail.ccAddr}")
//	private String ccAddress;
	
	@Value("$prop{test.failure.mail.fromAddr}")
	private String fromAddress;

	@Value("$prop{test.failure.mail.subject}")
	private String subject;

	@Value("$prop{test.failure.mail.message}")
	private String message;
	
	
	
	protected Map<MailConstants, String> formatMailMessage(String requestId) {
		
		Map<MailConstants, String> mailMessage = new HashMap<MailConstants, String>();
		
		String tMessage = String.format(message, requestId);
		
		mailMessage.put(MailConstants.TO_ADDRESS, toAddress);
		mailMessage.put(MailConstants.CC_ADDRESS, null);
		mailMessage.put(MailConstants.FROM_ADDRESS, fromAddress);
		mailMessage.put(MailConstants.MAIL_MESSAGE, tMessage);
		mailMessage.put(MailConstants.MAIL_SUBJECT, subject);
		mailMessage.put(MailConstants.IS_HTML, "true");
		
		return mailMessage;
	}

	
	@Autowired
	@Qualifier("emailUtil")
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}

}
