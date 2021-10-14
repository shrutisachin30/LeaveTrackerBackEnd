package org.generated.project.cronjob;

import java.util.List;
import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.services.LeaveDataService;
import org.seedstack.scheduler.Scheduled;
import org.seedstack.scheduler.SchedulingContext;
import org.seedstack.scheduler.Task;
import com.google.inject.Inject;


@Scheduled("0/5 * * * * ?")
public class myTask implements Task{
	

@Inject
private LeaveDataService leaveDataService;
	@Override
	public void execute(SchedulingContext sc) throws Exception {
		
		List<Object> objects = leaveDataService.getLeave();
		objects.forEach(object -> {
			LeaveData data = (LeaveData) object;
			leaveDataService.changeStatus(data.getStatus(), data.getLeaveDataId());
		});
		
		leaveDataService.deleteData();
	}

	

}
