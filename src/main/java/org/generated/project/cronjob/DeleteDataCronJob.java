package org.generated.project.cronjob;

import org.generated.project.domain.services.LeaveDataService;
import org.seedstack.scheduler.Scheduled;
import org.seedstack.scheduler.SchedulingContext;
import org.seedstack.scheduler.Task;
import com.google.inject.Inject;

/** 
 * <h2> ChangeStatusCronJob</h2> 
 * This program implements 
 * to delete data which is more than a year old everyday
 * <p> 
 *  
 * @author Sourav Donkar
 * @since 2021-10-08 
 */  

//Scheduler to implement cronjob of delete data which is more than a year old everyday
@Scheduled("0 0 12 1/1 * ? *")
public class DeleteDataCronJob implements Task {

	@Inject
	private LeaveDataService leaveDataService;

	@Override
	public void execute(SchedulingContext sc) throws Exception {
		leaveDataService.deleteData();

	}

}
