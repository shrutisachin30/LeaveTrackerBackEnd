package org.generated.project.domain.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.generated.project.application.EmployeeParam;
import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.seedstack.jpa.BaseJpaRepository;
import org.seedstack.seed.Bind;
import com.sun.istack.logging.Logger;

@Bind

public class EmployeeJPARepository extends BaseJpaRepository<Employee, EmployeeId> {

	private static final Logger logger = Logger.getLogger(EmployeeJPARepository.class);

	// method to get the DasId & Password while login
	public ArrayList<Object> getEmployee(LoginData empObj) {
		logger.info("Inside getEmployee" + empObj);
		EntityManager entityManager = getEntityManager();

		ArrayList<Object> obj = null;
		try {
			Query query = entityManager.createNamedQuery("getEmployee");
			query.setParameter("dasId", empObj.getDasId());
			query.setParameter("password", empObj.getPassword());
			obj = (ArrayList) query.getResultList();

		} catch (Exception ex) {
			logger.info("Exception occured in getEmployee" + empObj);
			obj = null;

		}

		return obj;

	}

	// method to get the EmployeeDetails after login
	public List<Object> getEmployeeDetails() {

		EntityManager entityManager = getEntityManager();

		List<Object> obj = null;
		try {
			Query query = entityManager.createNamedQuery("getEmployeeDetails");

			obj = (ArrayList) query.getResultList();

		} catch (Exception ex) {

			obj = null;

		}

		return obj;

	}

	// method to check whether the Employee exists in database
	public ArrayList<Employee> checkIfEmployeeExist(Employee empObj) {
		logger.info("Inside checkIfEmployeeExist" + empObj);
		EntityManager entityManager = getEntityManager();

		ArrayList<Employee> obj = null;
		try {
			Query query = entityManager.createNamedQuery("checkIfEmployeeExist");
			query.setParameter("dasId", empObj.getId());
			query.setParameter("employeeId", empObj.getEmployeeId());
			obj = (ArrayList) query.getResultList();

		} catch (Exception ex) {
			logger.info("Exception occured in getEmployee" + empObj);
			obj = null;

		}

		return obj;

	}

	// method to get the E-mailId of the employee
	public String getEmailId(String dasId) {
		EntityManager entityManager = getEntityManager();
		String email = " ";

		System.out.println(new EmployeeId(dasId));
		Query query = entityManager.createNamedQuery("getEmailId");
		query.setParameter("dasId", new EmployeeId(dasId));

		email = (String) query.getSingleResult();

		System.out.println(email);

		return email;

	}

	// method to update the password
	public int updatePassword(Employee emp) {

		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("updatePassword");
		query.setParameter("dasId", emp.getId().getDasId());
		query.setParameter("password", emp.getPassword());

		int row = 0;

		try {
			row = query.executeUpdate();

		} catch (Exception ex) {
			return -1;
		}
		return row;
	}

	// method to change the password
	public int changePassword(EmployeeParam eparam) {

		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("changePassword");
		query.setParameter("dasId", new EmployeeId(eparam.getId()));
		query.setParameter("newpassword", eparam.getNewpassword());
		query.setParameter("oldpassword", eparam.getOldpassword());

		int row = 0;

		try {
			row = query.executeUpdate();

		} catch (Exception ex) {
			return -1;
		}
		return row;
	}

	// method to export the leaveDetails
	public List<Object> exportData(String domain, String startDate, String endDate) {

		EntityManager entityManager = getEntityManager();
		// int startD = null;
		Date startD = null;
		Date endD = null;
		List<Object> obj = null;
		try {

			startD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startDate);
			endD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);

			Query query = null;

			if (!domain.equalsIgnoreCase("All")) {
				query = entityManager.createNamedQuery("exportData");
				query.setParameter("domain", domain);
			} else {
				query = entityManager.createNamedQuery("exportDataAllDomain");
			}

			query.setParameter("startDate", startD);
			query.setParameter("endDate", endD);

			obj = (ArrayList) query.getResultList();

		} catch (Exception ex) {

			obj = null;
			System.out.print("EXCEPTION" + ex);

		}

		return obj;

	}

	Employee exportDe(EmployeeId employee) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("exportData");
		return null;
	}

	// method to get the EmployeeDetails
	Employee getEmpDetails(EmployeeId employeeId) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("getEmployeeDetails");
		return null;
	}

	// method to update the EmployeeDetails
	public String updateEmployeeDetails(Employee emp) {
		String result = "";
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("updateEmployee");
		query.setParameter("employeeId", emp.getEmployeeId());
		query.setParameter("name", emp.getName());
		query.setParameter("mobile", emp.getMobile());
		query.setParameter("email", emp.getEmail());
		query.setParameter("gcmLevel", emp.getGcmLevel());
		query.setParameter("projectName", emp.getProjectName());
		query.setParameter("domain", emp.getDomain());
		query.setParameter("jobRole", emp.getJobRole());
		query.setParameter("reportingManager", emp.getReportingManager());
		query.setParameter("dasId", emp.getId());
		int num = query.executeUpdate();
		if (num > 0) {
			result = "Employee Updated Successfuly";
		} else {
			result = "Updating Employee Failed";
		}
		return result;
	}

	// method to activate/deactivate the Employee using DasId
	public String deactivateEmployee(EmployeeParam eparam) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("deactivateEmployee");

		query.setParameter("dasId", new EmployeeId(eparam.getDasid()));
		query.setParameter("isActive", (eparam.getIsActive()));
		query.setParameter("default", eparam.getDf());

		int row = 0;

		try {

			String status = getStatus(eparam.getDasid());
			if (!status.equals(eparam.getDf())) {
				return "Operation already performed";
			} else {
				row = query.executeUpdate();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
		if (row > 0)
			return "success";
		else
			return "error";

	}

	// method to make employee as Admin using DasId
	public String isAdmin(EmployeeId id) {

		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("makeAdmin");
		query.setParameter("dasId", new EmployeeId(id.getDasId()));
		query.setParameter("isAdmin", "Yes");

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

	// method to remove Admin using DasId
	public String removeAdmin(EmployeeId id) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("makeAdmin");
		query.setParameter("dasId", new EmployeeId(id.getDasId()));
		query.setParameter("isAdmin", "No");
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

	// method to check the status of the leave
	public String getStatus(String dasId) {
		EntityManager entityManager = getEntityManager();
		String status = " ";

		System.out.println(new EmployeeId(dasId));
		Query query = entityManager.createNamedQuery("getStatus");
		query.setParameter("dasId", new EmployeeId(dasId));

		status = (String) query.getSingleResult();

		System.out.println(status);

		return status;

	}

}
