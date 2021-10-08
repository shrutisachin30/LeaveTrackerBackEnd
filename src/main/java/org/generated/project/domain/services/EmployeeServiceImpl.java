package org.generated.project.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.generated.project.application.ChangePasswordRequest;
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

	public boolean loginService(LoginData data) {

		logger.info("EmployeeServiceImpl  ::  loginService() :  param: {" + data.getDasId() + "," + data.getPassword()
				+ "}");

		boolean resolve = false;

		ArrayList<Employee> list = verifyEmployeeDetails(data);
		if (list != null && list.size() > 0) {
			resolve = true;
		}

		return resolve;

	}

	@Transactional
	@JpaUnit("myUnit")
	@Override
	public Optional<Employee> getservice(EmployeeId id) {
		logger.info("EmployeeServiceImpl :: getservice():");
		Optional<Employee> obj = loginRepository.get(id);
		return obj;
	}

	@Transactional
	@JpaUnit("myUnit")
	public ArrayList<Employee> verifyEmployeeDetails(LoginData empObj) {
		logger.info("EmployeeServiceImpl :: verifyEmployeeDetails():");
		ArrayList<Employee> login = personRepository.getEmployee(empObj);

		return login;
	}

	@Transactional
	@JpaUnit("myUnit")
	public ArrayList<Employee> checkIfEmployeeExist(Employee empObj) {
		logger.info("EmployeeServiceImpl :: checkIfEmployeeExist():");
		ArrayList<Employee> login = personRepository.checkIfEmployeeExist(empObj);

		return login;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
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
			response.add(employeeDetail);

		}

		return response;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	public HashMap<String, String> getRandomKey(String id) {
		// TODO Auto-generated method stub
		int key = 0;
		String email = "";

		HashMap<String, String> response = new HashMap<>();
		try {
			email = personRepository.getEmailId(id);

			Random random = new Random();
			while (key < 10000) {
				key = random.nextInt(99999);
			}
			
			String subject=" OTP to Reset Password for Leave Tracker Application ";
			String emailContent = "<font color=darkblue><i>Greetings!</i><br><br>";
			emailContent += " \n\n ";
			emailContent += "<i>Here is your OTP : </i><b>" + key + "</b><br><br>";
			emailContent += " \n\n ";
			emailContent += " <i>Wish you a nice day!</i> </font>";
			
	
			String to = email;
			String from = "leavetracker.atos@gmail.com";
			this.emailservice.sendmail(subject, emailContent, to,from);
			
			
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
	public String changePassword(ChangePasswordRequest cprequest) {
		final String secretKey = "JH4KL6XA@ByC!$";

		try {

			String encryptedoldpassword = AESUtils.encrypt(cprequest.getOldpassword().toString(), secretKey);
			String encryptednewpassword = AESUtils.encrypt(cprequest.getNewpassword().toString(), secretKey);
			cprequest.setOldpassword(encryptedoldpassword);
			cprequest.setNewpassword(encryptednewpassword);

			int result = personRepository.changePassword(cprequest);

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
	public String updateEmployee(Employee emp) {
		  String result = "";
		  try{
			  personRepository.updateEmployeeDetails(emp);
			  result = "Employee Updated Successfuly";
		  }catch(Exception ex) {
			  ex.printStackTrace();
			  result = "Updating Employee Failed";
		  }
		  return result;
	}

	@Override
	@Transactional
	@JpaUnit("myUnit")
	public Employee getEmpDetails(EmployeeId employeeId) {
		System.out.print(employeeId);
		Optional<Employee> object = personRepository.get(employeeId);
		System.out.print(object.get());
		return object.get();
	}

}