package org.generated.project.domain.services;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.model.LeaveDataId;
import org.seedstack.business.domain.BaseRepository;
import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.BaseJpaRepository;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Bind;
import org.seedstack.seed.Logging;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;


@Bind
public class LeaveDataServiceImpl implements LeaveDataService {

	@Inject
	Repository<LeaveData, LeaveDataId> leaveDataRepo;
	
	@Inject	
	LeaveDataRepository leaveDataRepository;
	
	@Logging
	private Logger logger;
	

	@Transactional
	@JpaUnit("myUnit")
	public String applyLeave(LeaveData leaveDataObject) {
		
		logger.info("LeaveDataServiceImpl :: applyLeave()  param { "+leaveDataObject.getTypeOfLeave(),", "+leaveDataObject.getStartDate()+" ,"+leaveDataObject.getEndDate());	
		String str = leaveDataRepository.saveEmployeeLeave(leaveDataObject);

		logger.info("LeaveDataServiceImpl :: applyLeave()  After saving data : return object  "+str);		 
        return str;
	}

	@Transactional
	@JpaUnit("myUnit")
	public List<Object> retriveLeaveData(Long employeeId) {
		logger.info("LeaveDataServiceImpl :: retriveLeaveData():");
		List<Object> leaveDataObj = leaveDataRepository.getLeaveData(employeeId);

		return leaveDataObj;

	}
	
}
