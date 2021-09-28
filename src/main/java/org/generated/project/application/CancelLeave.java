package org.generated.project.application;

import java.util.Date;

public class CancelLeave {
	
	private String dasid;
	private Date startdate;
    private Date enddate;
    private Date updatedOn;
    private String updatedBy; 
	public CancelLeave(String dasid, Date startdate, Date enddate,Date updatedOn,String updatedBy) {
		super();
		this.dasid = dasid;
		this.startdate = startdate;
		this.enddate = enddate;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}
	public CancelLeave() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getDasid() {
		return dasid;
	}
	public void setDasid(String dasid) {
		this.dasid = dasid;
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
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Override
	public String toString() {
		return "CancelLeave [dasid=" + dasid + ", startdate=" + startdate + ", enddate=" + enddate +",updatedOn=" + updatedOn +",updatedBy=" + updatedBy + "]";
	}
	
	
	
 
}
