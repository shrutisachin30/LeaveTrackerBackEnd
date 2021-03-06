package org.generated.project.interfaces.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.generated.project.application.EmployeeParam;
import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.services.LeaveDataService;
import org.json.JSONObject;
import com.google.inject.servlet.RequestParameters;
import io.swagger.annotations.Api;

/**
 * <h2>LeaveDataResource</h2>
 * <p>
 * This program is  controller Service and provides api for all methods
 * </p>
 * @author Sourav Donkar,Shruti Karde,Subasri Venkatesan
 * @since 2021-09-23
 */

@Api("leave")
@Path("psa")
public class LeaveDataResource {

	@Inject
	private LeaveDataService leaveDataService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("applyLeave")
	// service for applying leave
	public HashMap<String, String> applyLeave(@RequestParameters LeaveData leaveDataObject) {

		String str = leaveDataService.applyLeave(leaveDataObject);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Leave Applied Successfully");
			response.put("statusCode", "201");

		} else if (str.equalsIgnoreCase("Already Exist")) {
			response.put("statusMsg", "Cannot apply leave on same dates, Select different dates");
			response.put("statusCode", "500");

		}

		else {
			response.put("statusMsg", "Technical Error");
			response.put("statusCode", "500");

		}

		return response;

	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("retrieveLeaveData/{id}")
	// service for retrieving the leave data
	public Response retrieveLeaveData(@PathParam("id") String id) {

		ArrayList list = new ArrayList<>();
		List<Object> listData = leaveDataService.retriveLeaveData(id);

		System.out.println(listData);
		if (listData != null && listData.size() >= 0) {

			for (int i = 0; i < listData.size(); i++) {
				JSONObject obj = new JSONObject();
				Object[] objArray = (Object[]) listData.get(i);

				obj.put("typeOfLeave", objArray[2].toString());
				obj.put("status", objArray[3].toString());
				obj.put("updatedBy", objArray[4].toString());
				Date startDate = null;
				Date endDate = null;
				Date updatedOn = null;
				try {

					startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objArray[0].toString());
					endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objArray[1].toString());
					updatedOn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objArray[5].toString());
					DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd");
					String output = outputFormatter.format(startDate);
					obj.put("startDate", output);
					String output1 = outputFormatter.format(endDate);
					obj.put("endDate", output1);
					String output2 = outputFormatter.format(updatedOn);
					obj.put("updatedOn", output2);
				} catch (ParseException e) {

					e.printStackTrace();
				}
				list.add(obj);

			}

		} else {

			return Response.status(200).entity("No Data Present").build();

		}

		return Response.status(200).entity(list.toString()).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("cancelLeave")
	// service for canceling the leave data
	public HashMap<String, String> cancelLeave(@RequestParameters EmployeeParam eparam) {
		System.out.println(eparam);
		String str = leaveDataService.cancelLeave(eparam);

		HashMap<String, String> response = new HashMap<String, String>();

		if (str.equalsIgnoreCase("success")) {

			response.put("statusMsg", "Leave Cancelled");
			response.put("statusCode", "201");

		} else {
			response.put("statusMsg", "Technical Error");
			response.put("statusCode", "500");

		}

		return response;

	}

}
