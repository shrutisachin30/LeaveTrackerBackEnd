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
					query.setParameter("dasId", empObj.getDasId());
					query.setParameter("password", empObj.getPassword());
					obj= (ArrayList)query.getResultList();
					//obj= (ArrayList)query.getSingleResult();
					
 
				} catch (Exception ex) {
					logger.info("Exception occured in getEmployee"+empObj);
					obj = null;

				}
				
					return obj;
				
			
						
	     }
	  
	  public List<Object> getEmployeeDetails() {
		  
	         EntityManager entityManager = getEntityManager();
	      	 			 			 		
	         
		     List<Object> obj = null;
				try {
					Query query = entityManager.createNamedQuery("getEmployeeDetails");
					
					obj= (ArrayList)query.getResultList();
					//obj= (ArrayList)query.getSingleResult();
					
 
				} catch (Exception ex) {
					
					obj = null;

				}
				
					return obj;
				
			
						
	     }
	  
	 
	  public ArrayList<Employee> checkIfEmployeeExist(Employee empObj) {
		  logger.info("Inside checkIfEmployeeExist"+empObj);
	         EntityManager entityManager = getEntityManager();
	      	 			 			 		
	         
		     ArrayList<Employee> obj = null;
				try {
					Query query = entityManager.createNamedQuery("checkIfEmployeeExist");
					query.setParameter("dasId",empObj.getId() );
					query.setParameter("employeeId", empObj.getEmployeeId());
					obj= (ArrayList)query.getResultList();
					//obj= (ArrayList)query.getSingleResult();
					
 
				} catch (Exception ex) {
					logger.info("Exception occured in getEmployee"+empObj);
					obj = null;

				}
				
					return obj;
				
			
						
	     }
	  
	  public String getEmailId(String dasId) {
		  EntityManager entityManager = getEntityManager();
		  String email = " ";
		  
		  try {
			  System.out.println(new EmployeeId(dasId));
				Query query = entityManager.createNamedQuery("getEmailId");
				query.setParameter("dasId",new EmployeeId(dasId));
				
				email= (String) query.getSingleResult();
				//obj= (ArrayList)query.getSingleResult();
				
				 System.out.println(email);
				

			} catch (Exception ex) {
				logger.info("Exception occured in getEmail");
				ex.printStackTrace();
			
				
			}
		  return email;

		
		  
	  }
   
	     
}
