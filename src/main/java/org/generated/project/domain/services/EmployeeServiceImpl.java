package org.generated.project.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Bind;
import org.seedstack.seed.Logging;
import org.seedstack.seed.transaction.Transactional;
import org.slf4j.Logger;
//import com.sun.istack.logging.Logger;


@Bind
public class EmployeeServiceImpl implements EmployeeService {

	@Inject
	private EmployeeJPARepository personRepository;
	
	@Inject
	@Jpa
	private Repository<Employee,EmployeeId> emprepo;

	@Logging
    private Logger logger;
	
	@Transactional
	@JpaUnit("myUnit")
	public String employeeService(Employee emp) {
	   
	        logger.info("EmployeeServiceImpl  ::  employeeService() : param : { "+emp.getName(),","+emp.getEmail()+","+emp.getDasid()+","+emp.getGcmLevel()+","+emp.getMobile()+","+emp.getRm()+","+emp.getJobRole()+","+emp.getId()+","+emp.getPassword()+","+emp.getProjectName()+"}");
	        
	    
	    
		String status="";
		try {
			
			Optional<Employee> login = getservice(emp.getId());
			if(!login.isPresent()) {
				try {
				emprepo.add(emp);
				status= "success";
				}
				catch(Exception e) {
					return "fail";
				}
			}
			else {
				status="alreadyExists";
			}
			
			
		}
		catch(Exception e){
			logger.info("Exception occured in getEmployee"+emp);
			e.getMessage();
			status="fail";
			
		}
		return status;
	}
	
	
    @Inject
    @Jpa
    private Repository<Employee, EmployeeId> loginRepository;
    
//    @Inject
//    private LoginRepository repo;
//    @Transactional
//    @JpaUnit("myUnit")
//    public String loginService(LoginId loginId, String password) {
//    	String resolve = "";
//    	try
//    	{
//        Optional<Login> login = loginRepository.get(getSpecificationBuilder().of(Login.class)
//                .property("username").matching(loginId).ignoringCase()
//                .or()
//                .property("password").matching(password).ignoringCase()
//                .build());
//        if(login.isPresent())
//        resolve = "Sucess";
//        else
//        	resolve = "Fail";
//    	}
//        
//    	catch(Exception e)
//    	{
//    		e.printStackTrace();
//    		resolve = "Fail";
//    	}
//    	return resolve;
//    }
    public boolean loginService(LoginData data) {
    	
    	logger.info("EmployeeServiceImpl  ::  loginService() :  param: {" +data.getDasid()+","+data.getPassword()+"}");
        
    	boolean resolve =false;
       	
        //Optional<Employee> login = getservice(new EmployeeId());
    	ArrayList<Employee> list = verifyEmployeeDetails(data);
    	if(list !=null && list.size() >0)
    	{
    		resolve =true;
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

    @Override
    @Transactional
	@JpaUnit("myUnit")
	public ArrayList<Employee> getEmployeeDetails() {
    	logger.info("EmployeeServiceImpl :: getEmployeeDetails():");
    	ArrayList<Employee> EmployeeDetails = personRepository.getEmployeeDetails();
		return EmployeeDetails;
	}
    
    
    
    
    
}


