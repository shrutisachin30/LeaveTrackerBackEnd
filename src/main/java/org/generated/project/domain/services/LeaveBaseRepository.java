package org.generated.project.domain.services;

import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.model.LeaveDataId;
import org.seedstack.business.domain.Repository;

public interface LeaveBaseRepository extends Repository<LeaveData, LeaveDataId> {

	String saveEmployeeLeave(LeaveData emp);

	String getLeaveData(Long id);

}
