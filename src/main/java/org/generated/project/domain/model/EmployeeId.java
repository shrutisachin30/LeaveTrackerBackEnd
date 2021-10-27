package org.generated.project.domain.model;

import javax.persistence.Embeddable;

import org.seedstack.business.domain.BaseValueObject;

/**
 * <h2>EmployeeId</h2>
 * <p>
 * This program implements to store Das Id and print the result
 * </p>
 * 
 * @author Subasri Venkatesan
 * @since 2021-09-01
 */
@Embeddable
public class EmployeeId extends BaseValueObject {

	private String dasId;

	public EmployeeId() {
		super();
	}

	public EmployeeId(String id) {
		this.dasId = id;
	}

	public String getDasId() {
		return dasId;
	}

	public void set(String dasId) {
		this.dasId = dasId;
	}

}
