package org.generated.project.domain.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.generated.project.application.CancelLeave;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.model.LeaveDataId;
import org.seedstack.jpa.BaseJpaRepository;
import org.seedstack.seed.Bind;

import com.sun.istack.logging.Logger;

@Bind
public class LeaveDataRepository extends BaseJpaRepository<LeaveData, LeaveDataId> {

	@Inject
	EntityManager entityManager;

	private static final Logger logger = Logger.getLogger(LeaveDataRepository.class);

	public String saveEmployeeLeave(LeaveData leaveObj) {

		logger.info("Inside saveEmployeeLeave" + leaveObj);
		EntityManager entityManager = getEntityManager();

		try {
			List<Object> obj = checkLeaveData(leaveObj);

			if (obj != null && obj.size() == 0) {
				entityManager.persist(leaveObj);
				return "Success";

			} else {
				return "Already Exist";
			}
		} catch (Exception ex) {

			logger.info("Exception occured in saveEmployeeLeave" + leaveObj);
			return ex.getMessage();
		}

	}

	public List<Object> checkLeaveData(LeaveData leaveObj) {
		EntityManager entityManager = getEntityManager();

		List<Object> obj = null;
		try {
			Query query = entityManager.createNamedQuery("checkLeaveData");
			query.setParameter("dasId", leaveObj.getEmployee().getId());
			query.setParameter("startDate", leaveObj.getStartDate());
			query.setParameter("endDate", leaveObj.getEndDate());

			obj = query.getResultList();
		} catch (Exception ex) {

		}

		return obj;

	}

	public List<Object> getLeaveData(String id) {
		EntityManager entityManager = getEntityManager();

		List<Object> obj = null;
		try {
			Query query = entityManager.createNamedQuery("getEmployeeLeave");
			query.setParameter("dasId", id);
			obj = query.getResultList();
		} catch (Exception ex) {

		}

		return obj;

	}

	public String cancelLeave(CancelLeave leaveObj) {

		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("cancelLeave");
		query.setParameter("dasId", leaveObj.getDasid());
		query.setParameter("startDate", leaveObj.getStartdate());
		query.setParameter("endDate", leaveObj.getEnddate());
		query.setParameter("updatedBy", leaveObj.getUpdatedBy());
		query.setParameter("updatedOn", leaveObj.getUpdatedOn());
		query.setParameter("status", "cancelled");

		int row = 0;

		try {
			row = query.executeUpdate();

		} catch (Exception ex) {

			return "error";
		}
		if (row > 0)
			return "success";
		else
			return "error";
	}

}
