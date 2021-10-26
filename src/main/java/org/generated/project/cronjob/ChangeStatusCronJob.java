package org.generated.project.cronjob;

import org.generated.project.domain.services.LeaveDataService;
import org.seedstack.scheduler.Scheduled;
import org.seedstack.scheduler.SchedulingContext;
import org.seedstack.scheduler.Task;
import com.google.inject.Inject;


/** 
 * <h2> ChangeStatusCronJob</h2> 
 * This program implements 
 * to change the status
 * <p> 
 *  
 * @author Sourav Donkar
 * @since 2021-10-08 
 */  
//Scheduler to implement cronjob of change status on every Friday
@Scheduled("0 0 12 ? * FRI *")
public class ChangeStatusCronJob implements Task {

	@Inject
	private LeaveDataService leaveDataService;

	@Override
	public void execute(SchedulingContext sc) throws Exception {
		leaveDataService.changeStatus();
	}

}
