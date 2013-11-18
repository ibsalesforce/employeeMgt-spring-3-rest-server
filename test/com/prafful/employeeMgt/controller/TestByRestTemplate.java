package com.prafful.employeeMgt.controller;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.prafful.employeeMgt.domain.Employee;
import com.prafful.employeeMgt.domain.EmployeeList;

public class TestByRestTemplate extends TestCase {

protected static Logger logger = Logger.getLogger("controller");
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private static final String URL = "http://localhost:9090/employeeMgt/restService";
	
	
	@Test
	public void testRestService() {
		// Prepare acceptable media type
		// Prepare acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_XML);

		// Prepare header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);

		// Prepare HttpEntity
		HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);

		// Send the request as GET
		try {
			ResponseEntity<EmployeeList> result = restTemplate.exchange(URL	+ "/employees", HttpMethod.GET, entity, EmployeeList.class);
			System.out.println("ggggg " + result.getBody().getData());

			EmployeeList result1 =restTemplate.getForObject(URL	+ "/employees", EmployeeList.class);
			System.out.println("kkkkkkk " + result1.getData());
			assertNotNull(result1.getData());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
}
