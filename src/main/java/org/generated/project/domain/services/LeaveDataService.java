package org.generated.project.domain.services;

import java.util.HashMap;
import java.util.List;

import org.generated.project.application.CancelLeave;
import org.generated.project.domain.model.LeaveData;
import org.seedstack.business.Service;

@Service
public interface LeaveDataService {
	
		
	public String applyLeave(LeaveData leaveDataObject);
	
	public List<Object> retriveLeaveData(String employeeId);
	
	

	public String cancelLeave(CancelLeave leaveDataObject);

}
