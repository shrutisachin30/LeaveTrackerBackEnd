package org.generated.project.cronjob;

import java.util.List;
import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.services.LeaveDataService;
import org.seedstack.scheduler.Scheduled;
import org.seedstack.scheduler.SchedulingContext;
import org.seedstack.scheduler.Task;
import com.google.inject.Inject;

@Scheduled("0/2 * * * * ?")
public class ChangeStatusCronJob implements Task{

	@Inject
	private LeaveDataService leaveDataService;

	@Override
	public void execute(SchedulingContext sc) throws Exception {
			leaveDataService.changeStatus();
	}

}
