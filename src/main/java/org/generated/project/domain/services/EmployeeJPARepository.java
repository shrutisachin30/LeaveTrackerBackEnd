package org.generated.project.domain.services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

	public ArrayList<Employee> getEmployee(LoginData empObj) {
		logger.info("Inside getEmployee" + empObj);
		EntityManager entityManager = getEntityManager();

		ArrayList<Employee> obj = null;
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
}
