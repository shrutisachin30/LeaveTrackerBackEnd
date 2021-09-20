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

import org.generated.project.application.ValidateParam;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.generated.project.domain.services.EmployeeService;

import com.google.inject.servlet.RequestParameters;

import io.swagger.annotations.Api;

@Api("Registeration")
@Path("psa")
public class RegisterAPIResource {

	@Inject
	private EmployeeService service;
	


	@Path("registerEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, String> employeeservice(@Valid @RequestParameters Employee emp) {
		 String result = service.employeeService(emp);
		 
		 HashMap<String, String> response = new HashMap<String, String>();
		 
		 //EmployeeId id = new EmployeeId(emp.getId);
		 
		 boolean flag =ValidateParam.isNull(emp.getName());
		 boolean flag1 =ValidateParam.isNull(emp.getEmail());
		 boolean flag2 =ValidateParam.isNull(emp.getPassword());
		 boolean flag3 =ValidateParam.isNull(emp.getProjectName());
		 boolean flag4 =ValidateParam.isNull(emp.getDasId());
		 boolean flag5 =ValidateParam.isNull(emp.getGcmLevel());
		 boolean flag6 =ValidateParam.isNull(emp.getJobRole());
		 boolean flag7 =ValidateParam.isNull(emp.getMobile());
		 boolean flag8 =ValidateParam.isNull(emp.getReportingManager());
		 //boolean flag7 =ValidateParam.isNull(emp.getId());
		 //boolean flag8 =ValidateParam.isNull(emp.getDoj());
		 
		    if(flag)  {
			response.put("statusCode", "500");
			response.put("statusMsg", "Please enter required name");
			}
		    else if(flag1)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required email");
				}
		    else if(flag2)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required password");
				}
		    else if(flag3)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required projectname");
				}
		    else if(flag4)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required Dasid");
				}
		    else if(flag5)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required GcmLevel");
				}
		    
		    else if(flag6)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required jobrole");
				}
		    else if(flag7)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required Mobile");
				}
		    else if(flag8)  {
				response.put("statusCode", "500");
				response.put("statusMsg", "Please enter required Rm");
				}
//		    else if(flag7)  {
//				response.put("statusCode", "500");
//				response.put("statusMsg", "Please enter required id");
//				}
//		    else if(flag8)  {
//				response.put("statusCode", "500");
//				response.put("statusMsg", "Please enter required doj");
//				}
		    
		    
		   else {
				
				        if(result.equalsIgnoreCase("success")) {
						response.put("statusCode", "201");
						response.put("statusMsg", "Registration is Successful");
						
					}else {
						
						response.put("statusCode", "500");
						
						if(result.equalsIgnoreCase("alreadyExists")) {
							response.put("statusMsg", "User Already Exists");
						}else {
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
	  
	  HashMap<String,String> obj= new HashMap();

List<Employee> listData;

	listData = service.getEmployeeDetails();


//HashMap<String, String> response = new HashMap<String, String>();
//if(listData!=null && listData.size()>=0) {
//	  
//		response.put("statusCode", "201");
//		
//		ArrayList list =new ArrayList<>();
//		
//		
//		for(int i=0; i<listData.size();i++) {
//			Employee objArray = listData.get(i);
//			
//			list.add(obj);
//			
//		}
//		
//		response.put("Employee Data : ",list.toString());
//		
//	}else {
//		
//		response.put("statusCode", "201");			
//		response.put("statusMsg", "No Data Present");
//		
//	}
	
	return Response.status(200).entity(listData).build();

}
}