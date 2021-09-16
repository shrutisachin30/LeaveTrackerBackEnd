package org.generated.project.domain.services;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.seedstack.jpa.BaseJpaRepository;
import org.seedstack.seed.Bind;
import com.sun.istack.logging.Logger;

@Bind

public class EmployeeJPARepository extends BaseJpaRepository<Employee, EmployeeId>  {

	private static final Logger logger =  Logger.getLogger(EmployeeJPARepository.class);
  
	  public ArrayList<Employee> getEmployee(LoginData empObj) {
		  logger.info("Inside getEmployee"+empObj);
	         EntityManager entityManager = getEntityManager();
	      	 			 			 		
	         
		     ArrayList<Employee> obj = null;
				try {
					Query query = entityManager.createNamedQuery("getEmployee");
					query.setParameter("dasid", empObj.getDasid());
					query.setParameter("password", empObj.getPassword());
					obj= (ArrayList)query.getResultList();
					//obj= (ArrayList)query.getSingleResult();
					
 
				} catch (Exception ex) {
					logger.info("Exception occured in getEmployee"+empObj);
					obj = null;

				}
				
					return obj;
				
			
						
	     }
	  
	  public ArrayList<Employee> getEmployeeDetails() {
		  
	         EntityManager entityManager = getEntityManager();
	      	 			 			 		
	         
		     ArrayList<Employee> obj = null;
				try {
					Query query = entityManager.createNamedQuery("getEmployeeDetails");
					obj= (ArrayList)query.getResultList();
					//obj= (ArrayList)query.getSingleResult();
					
 
				} catch (Exception ex) {
					
					obj = null;

				}
				
					return obj;
				
			
						
	     }
	  
	 
	
   
	     
}
