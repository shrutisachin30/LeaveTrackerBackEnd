package org.generated.project.domain.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.generated.project.application.EmployeeParam;
import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.seedstack.business.Service;

/**
 * <h2>EmployeeService</h2>
 * <p>
 * This program implements the services of employee in login , register, get details , 
 * update/ change Password, export details , activate/deactivate Employee, make / remove admin
 * </p>
 * 
 * @author Sourav Donkar,Shruti Karde,Subasri Venkatesan
 * @since 2021-09-18
 */

@Service
public interface EmployeeService {

	public ArrayList<Object> loginService(LoginData data);

	public Optional<Employee> getservice(EmployeeId id);

	public String employeeService(Employee emp);

	public ArrayList<Employee> getEmployeeDetails();

	String updatePassword(Employee emp);

	String changePassword(EmployeeParam eparam);

	String updateEmployee(Employee emp);

	HashMap<String, String> getRandomKey(String id);

	Employee getEmpDetails(EmployeeId employeeId);

	Employee exportDe(EmployeeId employee);

	public String deactivateEmployee(EmployeeParam eparam);

	List<Object> exportData(String domain, String startDate, String endDate);

	public String isAdmin(EmployeeId id);

	String removeAdmin(EmployeeId id);

}
