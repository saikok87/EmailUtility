package com.test.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1")
public interface IEmailController {
	
	@GET
	@Path("/test")
	public String test();
	
	@GET
	@Path("/mail/test")
	@Produces(MediaType.APPLICATION_JSON)
	public Response mailTest();
}