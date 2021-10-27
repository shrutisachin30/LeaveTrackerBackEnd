package org.generated.project.application;

import java.util.Date;

/**
 * <h2>Employee Param</h2>
 * <p>
 * This program implements to store Das Id,Old Password,New Password,Id,is
 * Active,df,startDate,endDate,updatedBy,updatedOn and print the result
 * </p>
 * 
 * @author Subasri Venkatesan
 * @since 2021-09-01
 */

public class EmployeeParam {
	private String Id;
	private String oldpassword;
	private String newpassword;
	private String dasid;
	private String isActive;
	private String df;

	private Date startdate;
	private Date enddate;
	private String updatedBy;
	private Date updatedOn;

	public EmployeeParam(String id, String oldpassword, String newpassword, String dasid, String isActive, String df,
			Date startdate, Date enddate, String updatedBy, Date updatedOn) {
		super();
		Id = id;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
		this.dasid = dasid;
		this.isActive = isActive;
		this.df = df;
		this.startdate = startdate;
		this.enddate = enddate;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public EmployeeParam() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getDasid() {
		return dasid;
	}

	public void setDasid(String dasid) {
		this.dasid = dasid;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getDf() {
		return df;
	}

	public void setDf(String df) {
		this.df = df;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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

	@Override
	public String toString() {
		return "EmployeeParam [Id=" + Id + ",dasid=" + dasid + ", oldpassword=" + oldpassword + ", startdate="
				+ startdate + ", enddate=" + enddate + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ ",  newpassword=" + newpassword + "]";
	}

}
