package org.generated.project.interfaces.rest;

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
import org.generated.project.application.EmployeeParam;
import org.generated.project.application.ValidateParam;
import org.generated.project.domain.model.Employee;
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
	// service for registering employee
	public HashMap<String, String> employeeservice(@Valid @RequestParameters Employee emp) {

		String encryptedString = AESUtils.encrypt(emp.getPassword().toString(), secretKey);

		emp.setPassword(encryptedString);

		HashMap<String, String> response = new HashMap<String, String>();

		boolean name = ValidateParam.isNull(emp.getName());
		boolean email = ValidateParam.isNull(emp.getEmail());
		boolean password = ValidateParam.isNull(emp.getPassword());
		boolean projectName = ValidateParam.isNull(emp.getProjectName());
		boolean employeeId = ValidateParam.isNull(emp.getEmployeeId());
		boolean gcmLevel = ValidateParam.isNull(emp.getGcmLevel());
		boolean jobRole = ValidateParam.isNull(emp.getJobRole());
		boolean mobile = ValidateParam.isNull(emp.getMobile());
		boolean reportingManager = ValidateParam.isNull(emp.getReportingManager());
		boolean domain = ValidateParam.isNull(emp.getDomain());

		if (name) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required name");
		} else if (email) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required email");
		} else if (password) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required password");
		} else if (projectName) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required projectname");
		} else if (employeeId) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Dasid");
		} else if (gcmLevel) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required GcmLevel");
		} else if (jobRole) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required jobrole");
		} else if (mobile) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Mobile");
		} else if (reportingManager) {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required Reporting Manager");
		} else if (domain) {
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
	// service for getting the employeeDetails
	public Response getEmployeeDetails() {

		HashMap<String, String> obj = new HashMap();

		List<Employee> listData;

		listData = service.getEmployeeDetails();

		return Response.status(200).entity(listData).build();

	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("exportData/{domain}/{startDate}/{endDate}")
	/* service for exporting leave details */
	public Response exportData(@PathParam("domain") String domain, @PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate) {
		System.out.print("domain" + domain);
		System.out.print("startDate" + startDate);
		System.out.print("endDate" + endDate);

		HashMap<String, String> obj = new HashMap();

		List<Object> listData;

		listData = service.exportData(domain, startDate, endDate);

		return Response.status(200).entity(listData).build();

	}

	@Path("forgotPassword/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// service for sending OTP to registered emailId
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

	// service for updating password

	public HashMap<String, String> updatePassword(@RequestParameters Employee emp) {
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

	// service for activate/deactivating employee
	public HashMap<String, String> deactivateEmployee(@RequestParameters EmployeeParam eparam) {
		System.out.println(eparam);
		String str = service.deactivateEmployee(eparam);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Employee deactivated");
			response.put("statusCode", "201");

		} else {
			response.put("statusMsg", str);
			response.put("statusCode", "500");

		}

		return response;

	}

}