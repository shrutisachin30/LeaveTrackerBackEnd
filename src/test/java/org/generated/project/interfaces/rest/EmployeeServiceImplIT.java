package org.generated.project.interfaces.rest;

 

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

 

import java.util.Date;

 

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import org.json.*;  

 

import org.generated.project.application.LoginData;
import org.generated.project.domain.model.Employee;
import org.generated.project.domain.model.EmployeeId;
import org.generated.project.domain.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.jpa.Jpa;
import org.seedstack.jpa.JpaUnit;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.SeedITRunner;
import org.seedstack.seed.transaction.Transactional;
import org.seedstack.seed.undertow.LaunchWithUndertow;

 

import io.restassured.response.Response;

 

@RunWith(SeedITRunner.class)
@LaunchWithUndertow
public class EmployeeServiceImplIT {

 

    
    @Configuration("runtime.web.baseUrl")
    private String baseUrl;

 

    Employee employee = null;
    
    @Inject
    private EmployeeService employeeservice;
    
    @Before 
    public void initializeRepository() {
        
EmployeeId id = new EmployeeId("A815665");
        
        employee = new Employee();
        employee.setName("shruti");
        employee.setId(id);
        employee.setEmployeeId("a9089");
        employee.setGcmLevel("1");
        employee.setEmail("shruti@gmail.com");
        employee.setJobRole("Associate Consultant");
        employee.setPassword("string");
        employee.setMobile("8878654534");
        employee.setProjectName("PSA");
        employee.setReportingManager("RM");
        
        employeeservice.employeeService(employee);
        
        
        
        
    }
    
    
    @Test
    public void testLogin() throws Exception {
        
        LoginData login = new LoginData();
        login.setDasId("200");
        login.setPassword("string");
        
        Response response = given().
                contentType(MediaType.APPLICATION_JSON)
                 .body(login)
                .auth().basic("demo", "demo")
                .expect().statusCode(200)
                .when().post(baseUrl + "/psa/loginService");
        

 

        JSONObject json = new JSONObject(response.body().asString());  
        
        String statusMsg= (String) json.get("statusCode");
        
        assertThat(statusMsg).isEqualTo("201");
    }
    
    @Test
    public void testLoginFail() throws Exception {
        
        LoginData login1 = new LoginData();
        login1.setDasId("demo1");
        login1.setPassword("pass1");
        
        Response response = given().
                contentType(MediaType.APPLICATION_JSON)
                 .body(login1)
                .auth().basic("demo", "demo")
                .expect().statusCode(200)
                .when().post(baseUrl + "/psa/loginService");
        

 

 

 

        JSONObject json = new JSONObject(response.body().asString());  
        
        String statusMsg= (String) json.get("statusCode");
        
        assertThat(statusMsg).isEqualTo("500");
    }
    
    
    @Test
    public void testRegister() throws Exception {
        EmployeeId id = new EmployeeId("A814558");
        
        Employee  register = new Employee();
        register.setName("shru");
        register.setId(id);
        register.setEmployeeId("a1239089");
        register.setEmail("shru@gmail.com");
        register.setJobRole("Associate Consultant");
        register.setPassword("shru#3");
        register.setGcmLevel("2");
        register.setMobile("7878898945");
        register.setReportingManager("AG");
        register.setProjectName("PSA");
       
        
        Response response = given().
                contentType(MediaType.APPLICATION_JSON)
                .body(register)
                .auth().basic("demo", "demo")
                .expect().statusCode(200)
                .when().post(baseUrl + "/psa/registerEmployee");

 

        String actualResult = response.body().asString();
                
                assertThat(response.body().asString()).isEqualTo("{\"statusMsg\":\"Registration is Successful\",\"statusCode\":\"201\"}");
        
    
    }
    
    
    
    @Test
    public void testRegister_useralreadyexist() throws Exception {
        
        
        
        
        Response response = given().
                contentType(MediaType.APPLICATION_JSON)
                .body(employee)
                .auth().basic("demo", "demo")
                .expect().statusCode(200)
                .when().post(baseUrl + "/psa/registerEmployee");

 

        String actualResult = response.body().asString();
                
                assertThat(response.body().asString()).isEqualTo("{\"statusMsg\":\"User Already Exists\",\"statusCode\":\"500\"}");
        
    
    }
} 