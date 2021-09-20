package org.generated.project.application;

import javax.validation.constraints.NotEmpty;

import org.seedstack.seed.validation.NotBlank;

public class LoginData {
	
	
	private String dasId;
	private String password;

	

	public String getDasId() {
		return dasId;
	}

	public void setDasId(String dasId) {
		this.dasId = dasId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
