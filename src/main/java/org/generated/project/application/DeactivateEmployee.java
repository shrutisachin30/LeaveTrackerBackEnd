package org.generated.project.application;

public class DeactivateEmployee {
	private String dasid;
	public DeactivateEmployee(String dasid){
		super();
		this.dasid = dasid;
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
	@Override
	public String toString() {
		return "DeactivateEmployee [dasid=" + dasid + "]";
	}
	
}
