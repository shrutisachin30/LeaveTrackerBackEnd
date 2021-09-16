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
@NamedQuery(name="getEmployee", query ="select dasid, password From Employee where dasid=:dasid and password=:password ") ,
@NamedQuery(name="getEmployeeDetails", query ="select id,dasid,name,mobile,email,gcmLevel,projectName,jobRole,rm From Employee") })

public class Employee extends BaseAggregateRoot<EmployeeId> {
	
	
	@EmbeddedId
	private EmployeeId id;
	@Column(unique=true)
	private String dasid;
	private String name;
	private String gcmLevel;
	private String mobile;
	private String email;
	private String rm;
	private String password;	
	private String projectName;
	private String jobRole;
	
	
	@OneToMany(mappedBy="employee")
	private Collection<LeaveData> leaveData = new ArrayList<>();
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(EmployeeId id,String dasid, String name,String gcmLevel,
			String mobile ,String email, String rm,String password, String projectName,String jobRole) {
		super();
		this.id = id;
		this.dasid = dasid;
		this.name = name;
		this.gcmLevel = gcmLevel;
		this.mobile = mobile;
		this.email = email;	
		this.rm = rm;
		this.password = password;
		this.projectName = projectName;
		this.jobRole = jobRole;
		
	}
	
	public String getDasid() {
		return dasid;
	}
	public void setDasid(String dasid) {
		this.dasid = dasid;
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
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public EmployeeId getId() {
		return id;
	}
	public void setId(EmployeeId id) {
		this.id = id;
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
	
	
}