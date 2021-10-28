package org.generated.project.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.inject.Inject;
import org.generated.project.application.EmployeeParam;
import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.generated.project.infrastructure.AESUtils;
import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Bind;
import org.seedstack.seed.Logging;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;

/**
 * <h2>EmployeeService</h2>
 * <p>
 * This program provides the implementation of services of employee in login , register, get details , 
 * update/ change Password, export details , activate/deactivate Employee, make / remove admin
 * </p>
 * 
 * @author Sourav Donkar,Shruti Karde,Subasri Venkatesan
 * @since 2021-09-18
 */

@Bind
public class EmployeeServiceImpl implements EmployeeService {

	@Inject
	private EmployeeJPARepository personRepository;

	@Inject
	@Jpa
	private Repository<Employee, EmployeeId> emprepo;

	@Logging
	private Logger logger;

	@Inject
	private EmailService emailservice;

	@Transactional
	@JpaUnit("myUnit")
	//employeeService is used for registration api as well as it checks if the employee already exists
	public String employeeService(Employee emp) {

		logger.info("EmployeeServiceImpl  ::  employeeService() : param : { " + emp.getName(),
				"," + emp.getEmail() + "," + emp.getEmployeeId() + "," + emp.getGcmLevel() + "," + emp.getMobile() + ","
						+ emp.getReportingManager() + "," + emp.getJobRole() + "," + emp.getId() + ","
						+ emp.getPassword() + "," + emp.getProjectName() + emp.getDomain() + "}");

		String status = "";
		try {
			
			ArrayList<Employee> list = checkIfEmployeeExist(emp);
			if (list != null && list.size() == 0) {

				try {
					emprepo.add(emp);
					status = "success";
				} catch (Exception e) {
					return "fail";
				}
			} else {
				status = "alreadyExists";
			}

		} catch (Exception e) {
			logger.info("Exception occured in getEmployee" + emp);
			e.getMessage();
			status = "fail";

		}
		return status;
	}

	@Inject
	@Jpa
	private Repository<Employee, EmployeeId> loginRepository;
	//loginService used for verifying the employee credentials and logging in
	public ArrayList<Object> loginService(LoginData data) {

		logger.info("EmployeeServiceImpl  ::  loginService() :  param: {" + data.getDasId() + "," + data.getPassword()

				+ "}");

		ArrayList<Object> list = verifyEmployeeDetails(data);

		return list;

	}

	@Transactional
	@JpaUnit("myUnit")
	@Override
	//getService used for getting the Employeeid
	public Optional<Employee> getservice(EmployeeId id) {
		logger.info("EmployeeServiceImpl :: getservice():");
		Optional<Employee> obj = loginRepository.get(id);
		return obj;
	}

	@Transactional
	@JpaUnit("myUnit")
	//verifyEmployeeDetails used for verifying the employee details
	public ArrayList<Object> verifyEmployeeDetails(LoginData empObj) {
		logger.info("EmployeeServiceImpl :: verifyEmployeeDetails():");
		ArrayList<Object> login = personRepository.getEmployee(empObj);

		return login;
	}

	@Transactional
	@JpaUnit("myUnit")
	//checkIfEmployeeExist used for checking whether the employee exists or not in the database
	public ArrayList<Employee> checkIfEmployeeExist(Employee empObj) {
		logger.info("EmployeeServiceImpl :: checkIfEmployeeExist():");
		ArrayList<Employee> login = personRepository.checkIfEmployeeExist(empObj);

		return login;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//getEmployeeDetails used for getting the details of Employee
	public ArrayList<Employee> getEmployeeDetails() {
		logger.info("EmployeeServiceImpl :: getEmployeeDetails():");
		List<Object> employeeDetails = personRepository.getEmployeeDetails();

		ArrayList response = new ArrayList();

		HashMap<String, String> employeeDetail = new HashMap<String, String>();

		for (int i = 0; i < employeeDetails.size(); i++) {

			Object[] objArray = (Object[]) employeeDetails.get(i);
			employeeDetail = new HashMap<String, String>();
			EmployeeId id = (EmployeeId) objArray[0];
			employeeDetail.put("dasId", id.getDasId());
			employeeDetail.put("employeeId", objArray[1].toString());
			employeeDetail.put("name", objArray[2].toString());
			employeeDetail.put("mobile", objArray[3].toString());
			employeeDetail.put("emailId", objArray[4].toString());
			employeeDetail.put("gcmLevel", objArray[5].toString());
			employeeDetail.put("projectName", objArray[6].toString());
			employeeDetail.put("jobRole", objArray[7].toString());
			employeeDetail.put("reportingManager", objArray[8].toString());
			employeeDetail.put("domain", objArray[9].toString());
			employeeDetail.put("isActive", objArray[10].toString());
			response.add(employeeDetail);

		}

		return response;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//exportData used for exporting the data into excel sheet
	public List<Object> exportData(String domain, String startDate, String endDate) {
		logger.info("EmployeeServiceImpl :: exportData():");
		List<Object> exportData = personRepository.exportData(domain, startDate, endDate);
		System.out.print(" export data " + exportData);
		ArrayList response = new ArrayList();

		HashMap<String, String> exportData1 = new HashMap<String, String>();
		if (exportData != null)
			for (int i = 0; i < exportData.size(); i++) {

				Object[] objArray = (Object[]) exportData.get(i);
				String Date = objArray[6].toString();
				String[] arr = Date.split("\\s+");
				String Date2 = objArray[7].toString();
				String[] arr1 = Date2.split("\\s+");

				exportData1 = new HashMap<String, String>();
				EmployeeId id = (EmployeeId) objArray[0];
				exportData1.put("dasId", id.getDasId());
				exportData1.put("name", objArray[1].toString());
				exportData1.put("mobile", objArray[2].toString());
				exportData1.put("reportingManager", objArray[3].toString());
				exportData1.put("gcmLevel", objArray[4].toString());
				exportData1.put("domain", objArray[5].toString());
				exportData1.put("startDate", arr[0]);
				exportData1.put("endDate", arr1[0]);
				exportData1.put("status", objArray[8].toString());
				exportData1.put("typeOfLeave", objArray[9].toString());
				exportData1.put("isActive", objArray[10].toString());
				exportData1.put("noOfDays", objArray[11].toString());
				response.add(exportData1);

			}

		return response;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//getRandomKey used for generating 4 digit OTP and send it to registered email
	public HashMap<String, String> getRandomKey(String id) {

		int key = 0;
		String email = "";

		HashMap<String, String> response = new HashMap<>();
		try {
			email = personRepository.getEmailId(id);

			Random random = new Random();
			while (key < 10000) {
				key = random.nextInt(99999);
			}

			String subject = " OTP to Reset Password for Leave Tracker Application ";
			String emailContent = "<font color=darkblue><i>Greetings!</i><br><br>";
			emailContent += " \n\n ";
			emailContent += "<i>Here is your OTP : </i><b>" + key + "</b><br><br>";
			emailContent += " \n\n ";
			emailContent += " <i>Wish you a nice day!</i> </font>";

			String to = email;
			String from = "leavetracker.atos@gmail.com";
			this.emailservice.sendmail(subject, emailContent, to, from);

		} catch (Exception e) {
			e.printStackTrace();
		}
		response.put("id", id);
		response.put("otp", Integer.toString(key));

		return response;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//used for updation of password and encrypt it to store in database
	public String updatePassword(Employee emp) {

		final String secretKey = "JH4KL6XA@ByC!$";

		try {

			String encryptedString = AESUtils.encrypt(emp.getPassword().toString(), secretKey);

			emp.setPassword(encryptedString);

			int result = personRepository.updatePassword(emp);

			if (result > 0)
				return "success";
			else
				return "error";

		}

		catch (Exception e) {
			e.printStackTrace();

			return "error";
		}

	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//changePassword used for changing the password
	public String changePassword(EmployeeParam eparam) {
		final String secretKey = "JH4KL6XA@ByC!$";

		try {

			String encryptedoldpassword = AESUtils.encrypt(eparam.getOldpassword().toString(), secretKey);
			String encryptednewpassword = AESUtils.encrypt(eparam.getNewpassword().toString(), secretKey);
			eparam.setOldpassword(encryptedoldpassword);
			eparam.setNewpassword(encryptednewpassword);

			int result = personRepository.changePassword(eparam);

			if (result > 0)
				return "success";
			else
				return "error";

		}

		catch (Exception e) {
			e.printStackTrace();

			return "error";
		}
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//updateEmployee used to update the details of the employee
	public String updateEmployee(Employee emp) {
		String result = "";
		try {
			personRepository.updateEmployeeDetails(emp);
			result = "Employee Updated Successfuly";
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "Updating Employee Failed";
		}
		return result;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//getEmpDetails used for getting the details of Employee
	public Employee getEmpDetails(EmployeeId employeeId) {
		System.out.print(employeeId);
		Optional<Employee> object = personRepository.get(employeeId);
		System.out.print(object.get());
		return object.get();
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	//deactivateEmployee used for deactivating the registered employee(Only can be done by admin)
	public String deactivateEmployee(EmployeeParam eparam) {

		String str = personRepository.deactivateEmployee(eparam);

		return str;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	public Employee exportDe(EmployeeId employee) {
		System.out.print(employee);
		Optional<Employee> object = personRepository.get(employee);
		System.out.print(object.get());
		return object.get();
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	public String isAdmin(EmployeeId id) {
		String str = personRepository.isAdmin(id);
		return str;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	public String removeAdmin(EmployeeId id) {
		String str = personRepository.removeAdmin(id);
		return str;
	}

}