package org.generated.project.interfaces.rest;

import java.util.HashMap;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.generated.project.application.LoginData;
import org.generated.project.application.ValidateParam;
import org.generated.project.domain.services.EmployeeService;
import org.generated.project.infrastructure.AESUtils;

import com.google.inject.servlet.RequestParameters;

import io.swagger.annotations.Api;

@Api("Login")
@Path("psa")
public class LoginAPIResource {
	@Inject
	private EmployeeService login;
	
	final String secretKey = "JH4KL6XA@ByC!$";


	@Path("loginService")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> loginService(@Valid @RequestParameters LoginData data) {
		
		HashMap<String, String> response = new HashMap<String, String>();
		

		boolean flag =ValidateParam.isNull(data.getDasId());
		boolean flag1 =ValidateParam.isNull(data.getPassword());
		
		if(flag) {
		response.put("statusCode", "500");
		response.put("statusMsg", "Please enter required Dasid");
		}
		else if(flag1) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Password");
			
		}else {
			String encryptedString = AESUtils.encrypt(data.getPassword().toString(), secretKey);
			data.setPassword(encryptedString);
			
		
				
		boolean action = login.loginService(data);
		
		if(action) { 
			response.put("statusCode", "201");
			response.put("statusMsg", "Login Successful");
			
		}else {
			response.put("statusCode", "500");
			response.put("statusMsg", "Username or Password is incorrect");
		}
		}
		return response;
	}

}