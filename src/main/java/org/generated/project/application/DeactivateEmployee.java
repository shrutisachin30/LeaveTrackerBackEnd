package org.generated.project.application;

public class DeactivateEmployee {
	private String dasid;
	private String isActive;
    private String df;
	
	

	public DeactivateEmployee(String dasid, String isActive,String df){

		super();
		this.dasid = dasid;
		this.isActive = isActive;
		this.df = df;
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
	public String getDf() {
		return df;
	}

	public void setDf(String df) {
		this.df = df;
	}

	@Override
	public String toString() {
		return "DeactivateEmployee [dasid=" + dasid + "]";
	}
	
}
