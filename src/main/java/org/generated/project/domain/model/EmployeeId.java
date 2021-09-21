package org.generated.project.domain.model;

import javax.persistence.Embeddable;

import org.seedstack.business.domain.BaseValueObject;

@Embeddable
public class EmployeeId extends BaseValueObject {
 
	private String dasId;


	public EmployeeId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeId(String id) {
		// TODO Auto-generated constructor stub
		 this.dasId=id;
	}


	public String getDasId() {
		return dasId;
	}

	public void set(String dasId) {
		this.dasId = dasId;
	}

	
	
}
	