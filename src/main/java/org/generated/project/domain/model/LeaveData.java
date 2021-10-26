package org.generated.project.domain.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.seedstack.business.domain.BaseAggregateRoot;
import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * <h2> Leave Data</h2> 
 * This program implements 
 * to store startDate,endDate ,typeOfLeave,status ,updatedBy ,updatedOn
 * and print the result  
 * <p> 
 *  
 * @author Shruti Karde 
 * @since 2021-09-01 
 */  
@Entity
@NamedQueries(value = {

		//@NamedQuery(name = "getEmployeeLeave", query = "select ld.startDate, ld.endDate , ld.typeOfLeave, ld.status , ld.updatedBy , ld.updatedOn ,ld.noOfDays From LeaveData ld where ld.employee.id=:dasId "

		@NamedQuery(name = "getEmployeeLeave", query = "select ld.startDate, ld.endDate , ld.typeOfLeave, ld.status , ld.updatedBy , ld.updatedOn, ld.noOfDays From LeaveData ld where ld.employee.id=:dasId "

				+ "order by ld.startDate ASC "),
		@NamedQuery(name = "cancelLeave", query = "update LeaveData ld set ld.status=:status,ld.updatedBy=:updatedBy,ld.updatedOn=:updatedOn where ld.startDate=:startDate and ld.endDate=:endDate and ld.employee=:dasId"),
		@NamedQuery(name = "checkLeaveData", query = "From LeaveData ld where ((ld.startDate=:startDate or ld.endDate=:endDate) or"
				+ " (ld.startDate between :startDate and :endDate) or"
				+ " (ld.endDate between :startDate and :endDate)) " + " and ld.employee=:dasId "
				+ " and ld.status='Applied'"),
		@NamedQuery(name = "changeStatus", query = "update LeaveData ld set ld.status=:status where ld.status ='Applied' and ld.endDate < now() - make_interval(0, 0, 0, 1) "),

		@NamedQuery(name = "deleteData", query = "delete LeaveData where startDate < now() - make_interval(1, 0, 0, 0)") })

public class LeaveData extends BaseAggregateRoot<LeaveDataId> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveDataId;

	@ManyToOne
	@JoinColumn(name = "dasId")
	private Employee employee;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "local")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "local")
	private Date endDate;

	private String typeOfLeave;
	private String status = "Applied";

	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "local")
	private Date updatedOn;
	//private String noOfDays;

	private String noOfDays;
	
	public LeaveData() {
		super();
	}

	public LeaveData(int leaveDataId) {

		this.leaveDataId = leaveDataId;

	}

	public LeaveData(Employee employee, int leaveDataId, Date startDate, Date endDate, String typeOfLeave,

			//String status, String updatedBy, Date updatedOn, String noOfDays) {

			String status, String updatedBy, Date updatedOn,String noOfDays) {


		this.employee = employee;
		this.leaveDataId = leaveDataId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeOfLeave = typeOfLeave;
		this.status = status;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.noOfDays = noOfDays;



	}

	@Override
	public String toString() {
		return "Employee [dasId= " + employee + ", startDate= " + startDate + ", endDate= " + endDate
				+ ", typeOfLeave= " + typeOfLeave + ",Status=" + status + ",updatedBy=" + updatedBy + ",updatedOn="

				+ updatedOn +"noOfDays"+ noOfDays+ "]";

	}

	public Date getStartDate() {

		return startDate;
	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(String typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public int getLeaveDataId() {
		return leaveDataId;
	}

	public void setLeaveDataId(int leaveDataId) {
		this.leaveDataId = leaveDataId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}


}
