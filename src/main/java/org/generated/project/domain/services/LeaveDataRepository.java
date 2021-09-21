package org.generated.project.domain.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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

			entityManager.persist(leaveObj);
			return "Success";
		} catch (Exception ex) {

			logger.info("Exception occured in saveEmployeeLeave" + leaveObj);
			return ex.getMessage();
		}

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

//	  public String cancelLeave(int leaveDataId) {
//
//          EntityManager entityManager = getEntityManager();
//          Query query = entityManager.createNamedQuery("cancelLeave");
//          query.setParameter("id",leaveDataId);
//          query.setParameter("status", "cancelled");
//          int row = query.executeUpdate();
//
//          if(row > 0)
//              return "updated successfully";
//          else
//              return "Not updated";
//
//
//
//      }
//	  

	public String cancelLeave(LeaveData leaveObj) {
		String dasId = leaveObj.getEmployee().getId().getDasId();
		Date startDate = leaveObj.getStartDate();
		Date endDate = leaveObj.getEndDate();

		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("cancelLeave");
		query.setParameter("dasId", dasId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);

		query.setParameter("status", "cancelled");
		
		int row =0;
		
		try {
			row = query.executeUpdate();
			
		}catch(Exception ex) {
			
				return "error";
		}
		if (row > 0)
			return "success";
		else
			return "error";
	}

}
