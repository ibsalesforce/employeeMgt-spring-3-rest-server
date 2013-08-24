package com.prafful.employeeMgt.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.prafful.employeeMgt.domain.Employee;

import org.springframework.stereotype.Service;

@Service("employeeService")
public class EmployeeService {

	protected static Logger logger = Logger.getLogger("service");
	
	// In-memory list
	private List<Employee> employees = new ArrayList<Employee>();
	
	public EmployeeService() {
		logger.debug("Init database");
		
		// Create in-memory list
		Employee employee1 = new Employee();
		employee1.setId(0L);
		employee1.setFirstName("Ajay");
		employee1.setLastName("Jain");
		employee1.setMoney(1000.0);
		
		Employee employee2 = new Employee();
		employee2.setId(1L);
		employee2.setFirstName("Surabhi");
		employee2.setLastName("Parihar");
		employee2.setMoney(2000.0);
		
		Employee employee3 = new Employee();
		employee3.setId(2L);
		employee3.setFirstName("Neeraj");
		employee3.setLastName("Sharma");
		employee3.setMoney(3000.0);
		
		employees.add(employee1);
		employees.add(employee2);
		employees.add(employee3);
	}
	
	/**
	 * Retrieves all employees
	 */
	public List<Employee> getAll() {
		logger.debug("Retrieving all employees");
		
		return employees;
	}
	
	/**
	 * Retrieves a single employee
	 */
	public Employee get( Long id ) {
		logger.debug("Retrieving employee with id: " + id);
		
		for (Employee employee:employees) {
			if (employee.getId().longValue() == id.longValue()) {
				logger.debug("Found record");
				return employee;
			}
		}
		
		logger.debug("No records found");
		return null;
	}
	
	/**
	 * Adds a new employee
	 */
	public Boolean add(Employee employee) {
		logger.debug("Adding new employee");
		
		try {
			// Find suitable id
			Long newId = 0L;
			Boolean hasFoundSuitableId = false;
			while(hasFoundSuitableId == false) {
				if(employees.size() > 0){
					for (int i=0; i <employees.size(); i++) {
						if (employees.get(i).getId().longValue() == newId.longValue()) {
							newId++;
							break;
						}
						
						// Exit while loop
						if(i==employees.size()-1) {
							logger.debug("Assigning id: " + newId);
							hasFoundSuitableId = true;
						}
					}
				} else {
					logger.debug("Assigning id: " + newId);
					hasFoundSuitableId = true;
				}
			}
			
			// Assign new id
			employee.setId(newId);
			// Add to list
			employees.add(employee);
			
			logger.debug("Added new employee");
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	/**
	 * Deletes an existing employee
	 */
	public Boolean delete(Long id) {
		logger.debug("Deleting employee with id: " + id);
		
		try {
			for (Employee employee:employees) {
				if (employee.getId().longValue() == id.longValue()) {
					logger.debug("Found record");
					employees.remove(employee);
					return true;
				}
			}
			
			logger.debug("No records found");
			return false;
			
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	/**
	 * Edits an existing employee
	 */
	public Boolean edit(Employee employee) {
		logger.debug("Editing employee with id: " + employee.getId());
		
		try {
			for (Employee p:employees) {
				if (p.getId().longValue() == employee.getId().longValue()) {
					logger.debug("Found record");
					employees.remove(p);
					employees.add(employee);
					return true;
				}
			}
			
			logger.debug("No records found");
			return false;
			
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
}
