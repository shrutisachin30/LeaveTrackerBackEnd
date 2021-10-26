package org.generated.project.interfaces.rest;

import java.util.ArrayList;
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
import org.generated.project.application.EmployeeParam;
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
	//service for validating the credentials and logging in
	public HashMap<String, String> loginService(@Valid @RequestParameters LoginData data) {

		HashMap<String, String> response = new HashMap<String, String>();

		boolean dasID = ValidateParam.isNull(data.getDasId());
		boolean password = ValidateParam.isNull(data.getPassword());

		if (dasID) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Dasid");
		} else if (password) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Password");

		} else {
			String encryptedString = AESUtils.encrypt(data.getPassword().toString(), secretKey);
			data.setPassword(encryptedString);

			ArrayList<Object> employeelist = login.loginService(data);

			if (employeelist != null && employeelist.size() > 0) {
				Object[] emp = (Object[]) employeelist.get(0);
				response.put("statusCode", "201");
				response.put("statusMsg", "Login Successful");
				response.put("isAdmin", emp[2].toString());
				response.put("isActive", emp[3].toString());

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
	//service for changing the password
	public HashMap<String, String> changePassword(@RequestParameters EmployeeParam eparam) {
		System.out.println(eparam);

		String str = service.changePassword(eparam);

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
	//service for getting the employeeDetails
	public HashMap<String, String> getEmployee(@PathParam("id") String id) {

		Employee emp = service.getEmpDetails(new EmployeeId(id));
		HashMap res = new HashMap();
		res.put("dasId", emp.getId());
		res.put("employeeId", emp.getEmployeeId());
		res.put("name", emp.getName());
		res.put("gcmLevel", emp.getGcmLevel());
		res.put("mobile", emp.getMobile());
		res.put("email", emp.getEmail());
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
	//service for updating the employeeDetails
	public Response updateEmployee(@RequestParameters Employee emp) {
		System.out.println("Update Employee" + emp.toString());
		return Response.status(200).entity(service.updateEmployee(emp)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("makeAdmin")
	//service for making employee as Admin
	public HashMap<String, String> isAdmin(@RequestParameters EmployeeId id) {
		System.out.println(id);
		String str = service.isAdmin(id);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Employee is now a admin");
			response.put("statusCode", "201");

		} else {
			response.put("statusMsg", "Fail :Data is not present");
			response.put("statusCode", "500");

		}

		return response;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("removeAdmin")
	//service for removing Admin status of Employee
	public HashMap<String, String> removeAdmin(@RequestParameters EmployeeId id) {
		System.out.println(id);
		String str = service.removeAdmin(id);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Employee is not a admin now");
			response.put("statusCode", "201");

		} else {
			response.put("statusMsg", "Fail :Data is not present");
			response.put("statusCode", "500");

		}

		return response;

	}

}