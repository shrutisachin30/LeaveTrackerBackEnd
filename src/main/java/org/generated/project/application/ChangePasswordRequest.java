package org.generated.project.application;

public class ChangePasswordRequest {

	private String Id;
	private String oldpassword;
	private String newpassword;
	
	public ChangePasswordRequest(String id, String oldpassword, String newpassword) {
		super();
		Id = id;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
	}
	public ChangePasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "ChangePasswordRequest [Id=" + Id + ", oldpassword=" + oldpassword + ", newpassword=" + newpassword
				+ "]";
	}
	
	
	
}
