package org.generated.project.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.seedstack.business.domain.BaseValueObject;

@Embeddable
public class LeaveDataId extends BaseValueObject {

	private int leaveDataId;

	public LeaveDataId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveDataId(int id) {
		// TODO Auto-generated constructor stub
		this.leaveDataId = id;
	}

	public int getLeaveDataIdId() {
		return leaveDataId;
	}

}