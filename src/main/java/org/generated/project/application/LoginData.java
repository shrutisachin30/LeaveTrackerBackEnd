package org.generated.project.application;

import javax.validation.constraints.NotEmpty;

import org.seedstack.seed.validation.NotBlank;

public class LoginData {
	
	
	private String dasid;
	private String password;

	public String getDasid() {
		return dasid;
	}

	
	public void setDasid(String dasid) {
		this.dasid = dasid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
