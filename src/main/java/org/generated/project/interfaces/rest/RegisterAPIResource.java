package org.generated.project.interfaces.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import org.generated.project.application.CancelLeave;
import org.generated.project.application.DeactivateEmployee;
import org.generated.project.application.ValidateParam;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;

import org.generated.project.domain.services.EmployeeService;
import org.generated.project.infrastructure.AESUtils;

import com.google.inject.servlet.RequestParameters;

import io.swagger.annotations.Api;

@Api("Registeration")
@Path("psa")
public class RegisterAPIResource {

	@Inject
	private EmployeeService service;

	final String secretKey = "JH4KL6XA@ByC!$";

	@Path("registerEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public HashMap<String, String> employeeservice(@Valid @RequestParameters Employee emp) {

		String encryptedString = AESUtils.encrypt(emp.getPassword().toString(), secretKey);

		emp.setPassword(encryptedString);

		HashMap<String, String> response = new HashMap<String, String>();

		boolean flag = ValidateParam.isNull(emp.getName());
		boolean flag1 = ValidateParam.isNull(emp.getEmail());
		boolean flag2 = ValidateParam.isNull(emp.getPassword());
		boolean flag3 = ValidateParam.isNull(emp.getProjectName());
		boolean flag4 = ValidateParam.isNull(emp.getEmployeeId());
		boolean flag5 = ValidateParam.isNull(emp.getGcmLevel());
		boolean flag6 = ValidateParam.isNull(emp.getJobRole());
		boolean flag7 = ValidateParam.isNull(emp.getMobile());
		boolean flag8 = ValidateParam.isNull(emp.getReportingManager());
		boolean flag9 = ValidateParam.isNull(emp.getDomain());

		if (flag) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required name");
		} else if (flag1) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required email");
		} else if (flag2) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required password");
		} else if (flag3) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required projectname");
		} else if (flag4) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Dasid");
		} else if (flag5) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required GcmLevel");
		} else if (flag6) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required jobrole");
		} else if (flag7) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Mobile");
		} else if (flag8) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Reporting Manager");
		} else if (flag9) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required domain");
		}

		else {
			String result = service.employeeService(emp);

			if (result.equalsIgnoreCase("success")) {

				response.put("statusCode", "201");
				response.put("statusMsg", "Registration is Successful");

			} else {

				response.put("statusCode", "500");

				if (result.equalsIgnoreCase("alreadyExists")) {
					response.put("statusMsg", "User Already Exists");
				} else {
					response.put("statusMsg", "Registration Not Successful");
				}

			}

		}

		return response;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getEmployeeDetails")
	public Response getEmployeeDetails() {

		HashMap<String, String> obj = new HashMap();

		List<Employee> listData;

		listData = service.getEmployeeDetails();

		return Response.status(200).entity(listData).build();

	}

	@Path("forgotPassword/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> forgotPassword(@PathParam("id") String id) {

		HashMap<String, String> response = service.getRandomKey(id);
		String otp = response.get("otp");

		if (!otp.equalsIgnoreCase("0")) {

			response.put("statusMsg", "success");
			response.put("statusCode", "200");

		} else {
			response.put("statusMsg", "Fail :Data is not present");
			response.put("statusCode", "500");

		}

		return response;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("updatePassword")
	public HashMap updatePassword(@RequestParameters Employee emp) {
		System.out.println(emp);
		String str = service.updatePassword(emp);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Password updated Successfully");
			response.put("statusCode", "201");

		} else {
			response.put("statusMsg", "Password not updated");
			response.put("statusCode", "500");

		}

		return response;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("deactivateEmployee")
	public HashMap deactivateEmployee(@RequestParameters DeactivateEmployee demp) {
		System.out.println(demp);
		String str = service.deactivateEmployee(demp);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Employee deactivated");
			response.put("statusCode", "201");

		} else {
			response.put("statusMsg", "Fail :Data is not present");
			response.put("statusCode", "500");

		}

		return response;

	}

}