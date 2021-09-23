package org.generated.project.application;

import java.util.Date;

public class CancelLeave {
	
	private String dasid;
	private Date startdate;
    private Date enddate;
	public CancelLeave(String dasid, Date startdate, Date enddate) {
		super();
		this.dasid = dasid;
		this.startdate = startdate;
		this.enddate = enddate;
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
	@Override
	public String toString() {
		return "CancelLeave [dasid=" + dasid + ", startdate=" + startdate + ", enddate=" + enddate + "]";
	}
	
 
}
