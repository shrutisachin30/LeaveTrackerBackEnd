package org.generated.project.domain.services;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.generated.project.application.ChangePasswordRequest;
import org.generated.project.application.DeactivateEmployee;
import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.generated.project.domain.model.LeaveData;
import org.seedstack.jpa.BaseJpaRepository;
import org.seedstack.seed.Bind;
import com.sun.istack.logging.Logger;

@Bind

public class EmployeeJPARepository extends BaseJpaRepository<Employee, EmployeeId> {

	private static final Logger logger = Logger.getLogger(EmployeeJPARepository.class);

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
	
	public int changePassword(ChangePasswordRequest cprequest) {

		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("changePassword");
		query.setParameter("dasId", new EmployeeId(cprequest.getId()));
		query.setParameter("newpassword", cprequest.getNewpassword());
		query.setParameter("oldpassword", cprequest.getOldpassword());

		int row = 0;

		try {
			row = query.executeUpdate();

		} catch (Exception ex) {
			return -1;
		}
		return row;
	}
	
	public List<Object> exportData(String domain, String startDate,String endDate) {

		EntityManager entityManager = getEntityManager();
		//int startD = null;
		Date endD = null;
		List<Object> obj = null;
		try {

			int startD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").TIMEZONE_FIELD;
			endD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate);
			System.out.print("startD"+startD);
		
			System.out.print("endD"+endD);
			
			Query query = entityManager.createNamedQuery("exportData");
			query.setParameter("domain",  domain);
		    query.setParameter("startDate",startD);
			query.setParameter("endDate",endD );
			
			obj = (ArrayList) query.getResultList();
		}
		 catch (Exception ex) {

			obj = null;
			System.out.print("EXCEPTION"+ex);

		}

		return obj;

	}
	
	Employee exportDe(EmployeeId employee) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("exportData");
		return null;
	}
	
	Employee getEmpDetails(EmployeeId employeeId) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("getEmployeeDetails");
		return null;
	}

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
		if(num>0) {
			result = "Employee Updated Successfuly";
		}else {
			result = "Updating Employee Failed";
		}
		return result;
	}
	
	public String  deactivateEmployee(DeactivateEmployee demp) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createNamedQuery("deactivateEmployee");
		query.setParameter("dasId", new EmployeeId(demp.getDasid()));
		query.setParameter("isActive", (demp.getIsActive()));
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
	
	public String  isAdmin(EmployeeId id) {
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
	

}
