package org.generated.project.domain.model;

import javax.persistence.Embeddable;
import org.seedstack.business.domain.BaseValueObject;

/**
 * <h2>Leave Data Id</h2>
 * <p>
 * This program implements to store leavedataid and print the result
 * </p>
 * 
 * @author Shruti Karde
 * @since 2021-09-03
 */
@Embeddable
public class LeaveDataId extends BaseValueObject {

	private int leaveDataId;

	public LeaveDataId() {
		super();

	}

	public LeaveDataId(int id) {
		this.leaveDataId = id;
	}

	public int getLeaveDataIdId() {
		return leaveDataId;
	}

}