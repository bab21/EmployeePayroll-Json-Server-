package com.capgemini.employeepayrollservice;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.employeepayrollservice.EmployeePayrollService.IOService;
import com.capgemini.employeepayrollservice.model.EmployeePayrollData;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmployeePayrollServiceTest {
	@Before
	public void setUp() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=3000;
	}
	public  EmployeePayrollData[] getEmployeeList() {
		Response response=RestAssured.get("/employees");
		System.out.println("Employee Data at jsonserver:\n"+response.asString());
		EmployeePayrollData[] arrayOfEmps=new Gson().fromJson(response.asString(),EmployeePayrollData[].class);
		return arrayOfEmps;
	}
	//UC1....
	@Test
	public void givenEmployeeDataInJsonServer_WhenRetrieved_ShouldMatchTheCount() {
		EmployeePayrollData[] arrayOfEmps=getEmployeeList();
		EmployeePayrollService employeePayrollService;
		employeePayrollService=new EmployeePayrollService(Arrays.asList(arrayOfEmps));
		long entries=employeePayrollService.countNumberOfEmployees(IOService.REST_IO);
		assertEquals(5,entries);
	}
	
//	//UC2 Database..
//	@Test
//	public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		System.out.println("data"+employeePayrollDataList);
//		assertEquals(3,employeePayrollDataList.size());
//	}
//	//UC3 Database...
//	@Test
//	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		employeePayrollService.updateEmployeeSalary("Terisa",300000.00);
//		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
//		assertTrue(result);
//		
//	}
//	
//	//UC5....
//	@Test
//	public void givenDateRange_WhenEmployeeDataRetrieved_ShouldMatch() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		LocalDate startDate=LocalDate.of(2018, 01, 01);
//		LocalDate endDate=LocalDate.now();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.getEmployeeWithDateRange(startDate,endDate);
//		assertEquals(3,employeePayrollDataList.size());
//	}
//	//UC6...
//	@Test
//	public void givenEmployeeData_WhenSumSalaryWithGender_ShouldMatch() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		Map<String,Double> salarySumByGender=employeePayrollService.getEmployeeSalarySumGroupWithGender();
//		assertTrue(salarySumByGender.get("F").equals(600000.0) && salarySumByGender.get("M").equals(100000.0));
//	}
	
	//UC7..
//	@Test
//	public void givenNewEmployee_WhenAdded_ShouldSyncWithDB() throws EmployeePayrollException{
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		int[] departments= {1,2,3};
//		employeePayrollService.addEmployeeToPayroll("Alisha",500000.00,LocalDate.now(),"M",2,departments);
//		boolean result=employeePayrollService.checkEmployeePayrollInSyncWithDB("Alisha");
//		assertTrue(result);
//	}
	//UC12...
//	@Test
//	public void givenEmployee_WhenDeleted_ShoudldSyncWithDB() throws EmployeePayrollException {
//		EmployeePayrollService employeePayrollService=new EmployeePayrollService();
//		List<EmployeePayrollData> employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		int sizeBeforeDeletion=employeePayrollDataList.size();
//		employeePayrollService.deleteEmployee("Alisha");
//		employeePayrollDataList=employeePayrollService.readEmployeePayrollData(IOService.DB_IO);
//		int sizeAfterDeletion=employeePayrollDataList.size();
//		assertTrue(sizeBeforeDeletion==sizeAfterDeletion+1);
//		
//	}

}
