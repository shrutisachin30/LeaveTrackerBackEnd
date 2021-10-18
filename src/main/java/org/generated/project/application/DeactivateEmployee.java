package org.generated.project.application;

public class DeactivateEmployee {
	private String dasid;
	private String isActive;

	
	

	public DeactivateEmployee(String dasid, String isActive){

		super();
		this.dasid = dasid;
		this.isActive = isActive;
		}
	
	public DeactivateEmployee() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "DeactivateEmployee [dasid=" + dasid + "]";
	}
	
}
