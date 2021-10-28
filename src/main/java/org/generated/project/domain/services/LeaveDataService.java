package org.generated.project.domain.services;

import java.util.List;

//import org.generated.project.application.CancelLeave;
import org.generated.project.application.EmployeeParam;
import org.generated.project.domain.model.LeaveData;
import org.seedstack.business.Service;

/**
 * <h2>LeaveDataService</h2>
 * <p>
 * This program implements the services of employee leave details as apply leave , retrieve leave , cancel leave,
 * change status , delete data
 * </p>
 * @author Sourav Donkar,Shruti Karde,Subasri Venkatesan
 * @since 2021-09-22
 */

@Service
public interface LeaveDataService {

	public String applyLeave(LeaveData leaveDataObject);

	public List<Object> retriveLeaveData(String employeeId);

	public String cancelLeave(EmployeeParam eparam);

	public String changeStatus();

	public String deleteData();

}
