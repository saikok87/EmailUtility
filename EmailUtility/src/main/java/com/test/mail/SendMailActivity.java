package com.test.mail;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SendMailActivity {
	
	protected MailUtil mailUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(SendMailActivity.class);
	
	protected abstract Map<MailConstants, String> formatMailMessage(String requestId);
	
	public abstract void setMailUtil(MailUtil mailUtil);

	public void doSendMail(String requestId) {
		
		try {
			
			Map<MailConstants, String> mailMessage = formatMailMessage(requestId);
			
			String isHtmlString = mailMessage.get(MailConstants.IS_HTML);
			boolean isHtml = true;
			
			if(isHtmlString == null || isHtmlString.trim().isEmpty())
				isHtml = false;
			
			
			mailUtil.sendMail(mailMessage.get(MailConstants.FROM_ADDRESS), mailMessage.get(MailConstants.TO_ADDRESS), 
					mailMessage.get(MailConstants.CC_ADDRESS), 
					mailMessage.get(MailConstants.MAIL_SUBJECT), 
					mailMessage.get(MailConstants.MAIL_MESSAGE), isHtml);
		} catch (Exception e) {
			
			logger.error("UNIQUE_ID= " + requestId
					+ " MESSAGE= Exception while sending the mail. ERR_MSG=\"" ,e);
		}
		

	}
}
