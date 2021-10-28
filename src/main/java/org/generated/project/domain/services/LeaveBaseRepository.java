package org.generated.project.domain.services;

import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.model.LeaveDataId;
import org.seedstack.business.domain.Repository;

/**
 * <h2> LeaveBaseRepository</h2>
 * <p>
 * This program implements services of employee leave details
 * </p>
 * 
 * @author Shruti Karde
 * @since 2021-09-26
 */
public interface LeaveBaseRepository extends Repository<LeaveData, LeaveDataId> {

	String saveEmployeeLeave(LeaveData emp);

	String getLeaveData(Long id);

}
