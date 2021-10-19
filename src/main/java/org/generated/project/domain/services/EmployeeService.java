package org.generated.project.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.generated.project.application.ChangePasswordRequest;
import org.generated.project.application.DeactivateEmployee;
import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.seedstack.business.Service;

@Service
public interface EmployeeService {

	public ArrayList<Object> loginService(LoginData data);

	public Optional<Employee> getservice(EmployeeId id);

	public String employeeService(Employee emp);

	public ArrayList<Employee> getEmployeeDetails();

	String updatePassword(Employee emp);
	
	String changePassword(ChangePasswordRequest cprequest);
	
	String updateEmployee(Employee emp);
	
	HashMap<String, String> getRandomKey(String id);

	Employee getEmpDetails(EmployeeId employeeId);
	
	Employee exportDe(EmployeeId employee);
	
	public String deactivateEmployee(DeactivateEmployee demp);

	List<Object> exportData(String domain, String startDate,String endDate);

	public String isAdmin(EmployeeId id);

	String removeAdmin(EmployeeId id);
	
	
	

	
	

}
