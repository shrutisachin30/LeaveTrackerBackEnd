package org.generated.project.domain.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.seedstack.business.domain.BaseAggregateRoot;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity

@NamedQueries(value = { 
@NamedQuery(name="getEmployee", query ="select id, password From Employee where id=:dasId and password=:password ") ,
@NamedQuery(name="getEmployeeDetails", query ="select id,employeeId,name,mobile,email,gcmLevel,projectName,domain,jobRole,reportingManager From Employee" 
+ " ORDER BY name ASC ") ,
@NamedQuery(name="checkIfEmployeeExist", query =" From Employee where id=:dasId or employeeId=:employeeId "),
@NamedQuery(name = "updatePassword", query = "update Employee set password=:password where id=:dasId "),
@NamedQuery(name="getEmailId", query = "Select email from Employee where id=:dasId")})

public class Employee extends BaseAggregateRoot<EmployeeId> {
	
	
	@EmbeddedId
	private EmployeeId id;
	@Column(unique=true)
	private String employeeId;
	private String name;
	private String gcmLevel;
	private String mobile;
	private String email;
	private String reportingManager;
	private String password;	
	private String projectName;
	private String jobRole;
	private String domain;
	
	
	@OneToMany(mappedBy="employee")
	private Collection<LeaveData> leaveData = new ArrayList<>();
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(EmployeeId id,String employeeId, String name,String gcmLevel,
			String mobile ,String email, String reportingManager,String password, String projectName,String jobRole,String domain) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.name = name;
		this.gcmLevel = gcmLevel;
		this.mobile = mobile;
		this.email = email;	
		this.reportingManager = reportingManager;
		this.password = password;
		this.projectName = projectName;
		this.jobRole = jobRole;
		this.domain = domain;
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGcmLevel() {
		return gcmLevel;
	}
	public void setGcmLevel(String gcmLevel) {
		this.gcmLevel = gcmLevel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getReportingManager() {
		return reportingManager;
	}
	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
	public Collection<LeaveData> getLeaveData() {
		return leaveData;
	}
	public void setLeaveData(Collection<LeaveData> leaveData) {
		this.leaveData = leaveData;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public EmployeeId getId() {
		return id;
	}
	public void setId(EmployeeId id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
}