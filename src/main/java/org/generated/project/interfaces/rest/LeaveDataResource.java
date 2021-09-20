 package org.generated.project.interfaces.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.model.LeaveDataId;
import org.generated.project.domain.services.LeaveDataService;

import com.google.inject.servlet.RequestParameters;

import io.swagger.annotations.Api;

import java.util.stream.Collectors;
@Api("leave")
@Path("psa")
public class LeaveDataResource {

	
	  @Inject 
	  private LeaveDataService leaveDataService;
	  
	  
	  @POST	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("applyLeave") 
	  public HashMap applyLeave(@RequestParameters LeaveData	  leaveDataObject) {
	  
	  String str = leaveDataService.applyLeave(leaveDataObject);
	  
	  HashMap<String, String> response = new HashMap<String, String>();
		
		if(str.equalsIgnoreCase("success")) {
			
			response.put("statusMsg", "Leave Applied Successfully");
			response.put("statusCode", "201");
			
		}else {
			response.put("statusMsg", "Technical Error");
			response.put("statusCode", "500");	
			
			
			
		}
		
		return response;
	  
	  
	  }
	  
	  
	  
	  @GET	  
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("retrieveLeaveData/{id}")
	  public HashMap retrieveLeaveData(@PathParam("id") Long id) {
		  
		  HashMap<String,String> obj= new HashMap();
	  
	  List<Object> listData = leaveDataService.retriveLeaveData(id);
	  
	  HashMap<String, String> response = new HashMap<String, String>();
	  System.out.println(listData);
			  if(listData!=null && listData.size()>=0) {
		  
			response.put("statusCode", "201");
			
			ArrayList list =new ArrayList<>();
			
			
			for(int i=0; i<listData.size();i++) {
				Object[] objArray =(Object[]) listData.get(i);
				obj.put("typeOfLeave",objArray[2].toString());
				obj.put("status",objArray[3].toString());
				 Date startDate = null;
				 Date endDate = null;
				try {
					startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objArray[0].toString());
					endDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objArray[1].toString());
					DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
					String output = outputFormatter.format(startDate); 
					obj.put("startDate",output);
					output = outputFormatter.format(endDate); 
					obj.put("endDate",output);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				list.add(obj);
				
			}
			
			response.put("Leave Data for Employee : ",list.toString());
			
		}else {
			
			response.put("statusCode", "201");			
			response.put("statusMsg", "No Data Present");
			
		}
		
		return response;
	  
	  }
	  
	  
	  
	  @POST
      @Path("CancelLeave/{id}")
      public Response cancelLeave(@PathParam("id") int id) {
       
         
           String response= leaveDataService.cancelLeave(id);
           return Response.status(201).entity(response).build();
      }

}
