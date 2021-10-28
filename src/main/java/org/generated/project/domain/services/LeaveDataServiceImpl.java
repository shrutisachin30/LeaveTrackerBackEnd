package org.generated.project.domain.services;

import java.util.List;
import javax.inject.Inject;

//import org.generated.project.application.CancelLeave;
import org.generated.project.application.EmployeeParam;
import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.model.LeaveDataId;
import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Bind;
import org.seedstack.seed.Logging;
import org.seedstack.seed.transaction.Transactional;

import org.slf4j.Logger;

/**
 * <h2>LeaveDataServiceImpl</h2>
 * <p>
 * This program provides the implementation of services of employee leave details apply leave , retrieve leave , cancel leave,
 * change status , delete data
 * </p>
 * @author Sourav Donkar,Shruti Karde,Subasri Venkatesan
 * @since 2021-09-22
 */
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
	//applyLeave used to apply leaves
	public String applyLeave(LeaveData leaveDataObject) {

		logger.info("LeaveDataServiceImpl :: applyLeave()  param { " + leaveDataObject.getTypeOfLeave(),
				", " + leaveDataObject.getStartDate() + " ," + leaveDataObject.getEndDate() + ","
						+ leaveDataObject.getUpdatedOn() + "," + leaveDataObject.getUpdatedBy());
		String str = leaveDataRepository.saveEmployeeLeave(leaveDataObject);
		System.out.print(leaveDataObject.getStartDate());
		System.out.print(leaveDataObject.getEndDate());
		logger.info("LeaveDataServiceImpl :: applyLeave()  After saving data : return object  " + str);
		return str;
	}

	@Transactional
	@JpaUnit("myUnit")
	//retriveLeaveData used to fetch all the leave data of employees
	public List<Object> retriveLeaveData(String employeeId) {
		logger.info("LeaveDataServiceImpl :: retriveLeaveData():");
		List<Object> leaveDataObj = leaveDataRepository.getLeaveData(employeeId);

		return leaveDataObj;
	}

	@Transactional
	@JpaUnit("myUnit")
	//employees can cancel their leaves using cancelLeave
	public String cancelLeave(EmployeeParam eparam) {

		String str = leaveDataRepository.cancelLeave(eparam);

		return str;
	}

	@Transactional
	@JpaUnit("myUnit")
	//used to change the status of leave
	public String changeStatus() {
		logger.info("LeaveDataServiceImpl :: changeStatus():");
		return leaveDataRepository.changeStatus();
	}

	@Transactional
	@JpaUnit("myUnit")
	//used to delete Data which is more than a year old
	public String deleteData() {
		return leaveDataRepository.deleteData();
	}

}
