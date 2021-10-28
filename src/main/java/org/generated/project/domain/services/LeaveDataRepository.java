package org.generated.project.domain.services;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.generated.project.application.EmployeeParam;
import org.generated.project.domain.model.LeaveData;
import org.generated.project.domain.model.LeaveDataId;
import org.seedstack.jpa.BaseJpaRepository;
import org.seedstack.seed.Bind;
import com.sun.istack.logging.Logger;


/**
 * <h2>LeaveDataRepository</h2>
 * <p>
 * This program implements the method to save,check,get,cancel leave data of employee,
 *  method to change the status of applied leave ,  method to delete data more than 1 year old
 * </p>
 * @author Sourav Donkar,Shruti Karde,Subasri Venkatesan
 * @since 2021-09-22
 */
@Bind
public class LeaveDataRepository extends BaseJpaRepository<LeaveData, LeaveDataId> {

	@Inject
	EntityManager entityManager;

	private static final Logger logger = Logger.getLogger(LeaveDataRepository.class);

	// method to save the leave data
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

	// method to check the leave data
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

	// method to get the leave data
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

	// method to cancel the leave
	public String cancelLeave(EmployeeParam eparam) {

		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("cancelLeave");
		query.setParameter("dasId", eparam.getDasid());
		query.setParameter("startDate", eparam.getStartdate());
		query.setParameter("endDate", eparam.getEnddate());
		query.setParameter("updatedBy", eparam.getUpdatedBy());
		query.setParameter("updatedOn", eparam.getUpdatedOn());
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

	// method to change the status of applied leave
	public String changeStatus() {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("changeStatus");
		int result = 0;
		query.setParameter("status", "Availed");
		result = query.executeUpdate();

		return result == 1 ? "Success" : "Fail";
	}

	// method to delete data more than 1 year old
	public String deleteData() {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("deleteData");
		query.executeUpdate();
		return null;
	}

}
