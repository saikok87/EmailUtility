package com.test.controller;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.mail.FailureNotificationMail;

@Component
public class EmailController implements IEmailController {
	
	@Autowired
	FailureNotificationMail failureNotificationMail;
	
	@Override
	public String test() {
		return "Rest is ready!!";
	}

	@Override
	public Response mailTest() {
		
		failureNotificationMail.doSendMail("123");
		
		return Response.ok("Mail Sent!!").build();
	}

}
