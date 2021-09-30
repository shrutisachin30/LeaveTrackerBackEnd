package org.generated.project.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.seedstack.business.Service;
import javax.mail.Message;
import javax.mail.MessagingException;
@Service
public interface EmployeeService {
	
	public boolean loginService(LoginData data);
	
	public Optional<Employee> getservice(EmployeeId id);
	
	public String employeeService(Employee emp);

	public ArrayList<Employee> getEmployeeDetails();
	
//	String updatePassword(UpdatePassword updatepswd);
	
	
	int getRandomKey(String id);
	

	//public Optional<Employee> getservice(EmployeeId id, String dasid);
	

	String sendOtpToMail(String addr, int otp) throws MessagingException;

	String sendToMail(String addr, int otp) throws MessagingException;
	
	

}
