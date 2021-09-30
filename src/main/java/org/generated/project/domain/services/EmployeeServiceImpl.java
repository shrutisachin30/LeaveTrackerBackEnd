package org.generated.project.domain.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  


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
    @Named("smtpProvider")
    private Session smtpSession;
	
	

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
	public int getRandomKey(String id) {
		// TODO Auto-generated method stub
		int key = 0;
		
		String status ="";

		try {
			String email = personRepository.getEmailId(id);
			System.out.println(email);

			Random random = new Random();
			while (key < 10000) {
				key = random.nextInt(99999);
			}

			status = sendToMail(email, key);
			
			System.out.print(status);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return key;
	}

//	@Override
//	public String updatePassword(Employee updatepswd) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	@Transactional
//	@JpaUnit("myUnit")
//	public String updatePassword(UpdatePassword updatepswd) {
//	String result="";
//
//		try {
//			Employee emp=getEmployee(new DasId(updatepswd.getDasId()));
//			emp.setPassword(updatepswd.getPassword());
//			updateEmployee(emp);
//
//			result="Updated successfully";
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//
//			result="updation failed";
//		}
//		return result;
//	}

	
	
	@Override
	public String sendOtpToMail(String addr, int otp) throws MessagingException {
		
		String status="";
		
		Transport transport=null;
		
		
		try {
			 Message message= new MimeMessage(smtpSession);
			 message.addRecipient(Message.RecipientType.TO, new InternetAddress(addr));
			 message.setFrom(new InternetAddress(addr));
			 message.setSubject("Password Reset for Leave Tracker");
			 message.setText("Hi,  Password Reset Key: "+otp);
			 
			 message.setSentDate(new Date());
			 

	            transport = smtpSession.getTransport();
	            transport.connect("smtp.gmail.com",465,"yeshvibhav@gmail.com","P@ssw0rd2809");
	            transport.sendMessage(message, message.getAllRecipients());
	            
	            
	            
	            status="OTP Mail sent to : "+addr+" . OTP : "+otp;
	           
		 }
	  
		 catch(Exception e) {
			 e.printStackTrace();
			 status="Sending mail to "+addr+" failed";
		 }
		 if(transport!=null) {
			 transport.close();
		 }
		
		return status;
	}
	
	
	
	
	  
	
	@Override
	public String sendToMail(String addr, int otp) throws MessagingException {
	  
	  String host="localhost";  
	  final String user="yeshvibhav@gmail.com";//change accordingly  
	  final String password="P@ssw0rd2809";//change accordingly  
	    
	  String to="yeshwant.prabhu@atos.net";//change accordingly  
	  
	   //Get the session object  
	   Properties props = new Properties();  
	   props.put("mail.smtp.host",host);  
	   props.put("mail.smtp.auth", "true");  
	   props.put("mail.smtp.starttls.enable", "true"); 
	   props.put("mail.smtp.debug", "true"); 
	   props.put("mail.smtp.socketFactory.port", 465); 
	   props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
	   props.put("mail.smtp.socketFactory.fallback", "false"); 
	   
	  	     
		
		  Session session = Session.getDefaultInstance(props, new
		  javax.mail.Authenticator() { protected PasswordAuthentication
		  getPasswordAuthentication() { return new
		  PasswordAuthentication(user,password); } });
		 
	  
	   //Compose the message  
	    try {  
	     MimeMessage message = new MimeMessage(session);  
	     message.setFrom(new InternetAddress(user));  
	     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	     message.setSubject("OTP");  
	     message.setText("This is simple program of sending email using JavaMail API"+otp);  
	       
	    //send the message  
	     Transport.send(message);  
	  
	     System.out.println("message sent successfully...");  
	   
	     } catch (MessagingException e) {
	    	 
	    	 e.printStackTrace();
	    	 
	     }
		return to;  
	 }  
	}  


