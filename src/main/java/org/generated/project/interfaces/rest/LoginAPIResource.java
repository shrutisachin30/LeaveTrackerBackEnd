package org.generated.project.interfaces.rest;

import java.util.HashMap;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.generated.project.application.ChangePasswordRequest;
import org.generated.project.application.LoginData;
import org.generated.project.application.ValidateParam;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.generated.project.domain.services.EmployeeService;
import org.generated.project.infrastructure.AESUtils;
import com.google.inject.servlet.RequestParameters;

import io.swagger.annotations.Api;

@Api("Login")
@Path("psa")
public class LoginAPIResource {
	@Inject
	private EmployeeService login;

	@Inject
	private EmployeeService service;

	final String secretKey = "JH4KL6XA@ByC!$";

	@Path("loginService")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> loginService(@Valid @RequestParameters LoginData data) {

		HashMap<String, String> response = new HashMap<String, String>();

		boolean flag = ValidateParam.isNull(data.getDasId());
		boolean flag1 = ValidateParam.isNull(data.getPassword());

		if (flag) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Dasid");
		} else if (flag1) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Password");

		} else {
			String encryptedString = AESUtils.encrypt(data.getPassword().toString(), secretKey);
			data.setPassword(encryptedString);

			boolean action = login.loginService(data);

			if (action) {
				response.put("statusCode", "201");
				response.put("statusMsg", "Login Successful");

			} else {
				response.put("statusCode", "500");
				response.put("statusMsg", "Username or Password is incorrect");
			}
		}
		return response;
	}

	@Path("changePassword")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap changePassword(@RequestParameters ChangePasswordRequest cpRequest) {
		System.out.println(cpRequest);

		String str = service.changePassword(cpRequest);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Password changed successfully");
			response.put("statusCode", "201");

		} else {
			response.put("statusMsg", "Incorrect Old Password!!");
			response.put("statusCode", "500");

		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Path("getEmployee/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap getEmployee(@PathParam("id") String id) {
		// return Response.status(200).entity(service.getEmpDetails(new
		// EmployeeId(id))).build();
		Employee emp = service.getEmpDetails(new EmployeeId(id));
		HashMap res = new HashMap();
		res.put("dasId", emp.getId());
		res.put("employeeId", emp.getEmployeeId());
		res.put("name", emp.getName());
		res.put("gcmLevel", emp.getGcmLevel());
		res.put("contactNo", emp.getMobile());
		res.put("emailId", emp.getEmail());
		res.put("reportingManager", emp.getReportingManager());
		res.put("projectName", emp.getProjectName());
		res.put("domain", emp.getDomain());
		res.put("jobRole", emp.getJobRole());
		
		
		return res;
	}

	@Path("updateEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployee(@RequestParameters Employee emp) {
		return Response.status(200).entity(service.updateEmployee(emp)).build();
	}
}