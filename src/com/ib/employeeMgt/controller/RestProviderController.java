package com.ib.employeeMgt.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ib.employeeMgt.domain.Employee;
import com.ib.employeeMgt.domain.EmployeeList;
import com.ib.employeeMgt.domain.Result;
import com.ib.employeeMgt.service.EmployeeService;

/**
 * REST service provider
 * 
 * Only GET and POST will return values
 * PUT and DELETE will not.
 */
@Controller
public class RestProviderController {

	private static final String GETPAGE = "getpage";
	
	private static final String VALUE_AS_TRUE = "true";
	private static final String VALUE_AS_FALSE = "false";

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	
	/*@RequestMapping(value = "/employee/{id}", 
								method = RequestMethod.GET,
								headers="Accept=image/jpeg, image/jpg, image/png, image/gif")
	public @ResponseBody byte[] getPhoto(@PathVariable("id") Long id) {
		
		// Call service here
		try {
			// Retrieve image from the classpath
			InputStream is = this.getClass().getResourceAsStream("/bella.jpg"); 
			
			// Prepare buffered image
			BufferedImage img = ImageIO.read(is);
			
			// Create a byte array output stream
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			
			// Write to output stream
			ImageIO.write(img, "jpg", bao);
			
			logger.debug("Retrieving photo as byte array image");
			return bao.toByteArray();
			
		} catch (IOException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}*/
    
    @RequestMapping(value = "/employee/{id}", 
								method = RequestMethod.GET, 
								headers="Accept=application/html, application/xhtml+xml")
	public String getEmployeeHTML(@PathVariable("id") Long id, Model model) {
    	logger.debug("Provider has received request to get employee with id: " + id);
		
		// Call service to here
		model.addAttribute("employee",employeeService.get(id));
		
		// This will resolve to /WEB-INF/jsp/getpage.jsp
		return GETPAGE;
	}
    
	@RequestMapping(value = "/employees", 
								method = RequestMethod.GET, 
								headers="Accept=application/xml, application/json")
	public @ResponseBody EmployeeList getEmployee() {
		logger.debug("Provider has received request to get all employees");
		
		// Call service here
		EmployeeList result = new EmployeeList();
		result.setData(employeeService.getAll());
		
		return result;
	}
	
    @RequestMapping(value = "/employee/{id}", 
    							method = RequestMethod.GET, 
    							headers="Accept=application/xml, application/json")
	public @ResponseBody Employee getEmployee(@PathVariable("id") Long id) {
    	logger.debug("Provider has received request to get employee with id: " + id);
    	
    	// Call service here
		return employeeService.get(id);
    }
    
    @RequestMapping(value = "/employee", 
    							method = RequestMethod.POST)
	public @ResponseBody Result addEmployee(@RequestBody Employee employee) {
    	logger.debug("Provider has received request to add new employee");
    	
    	Result result = new Result();
    	
    	// Call service to here
    	boolean added = employeeService.add(employee);
    	
    	if(added)
    		result.setSuccess(VALUE_AS_TRUE);
    	else
    		result.setSuccess(VALUE_AS_FALSE);
    	
    	return result;
    }
    
    
    @RequestMapping(value = "/employee/{id}", 
    							method = RequestMethod.PUT, 
    							headers="Accept=application/xml, application/json")
	public @ResponseBody Result updateEmployee(@PathVariable("id") Long id, 
								@RequestBody Employee employee) {
    	logger.debug("Provider has received request to edit employee with id: " + id);
    	Result result = new Result();
    	
    	// Call service here
    	employee.setId(id);
    	boolean updated = employeeService.edit(employee);
    	
    	if(updated)
    		result.setSuccess(VALUE_AS_TRUE);
    	else
    		result.setSuccess(VALUE_AS_FALSE);
    	
    	return result;
    		
    }
    
    
    @RequestMapping(value = "/employee/{id}", 
    							method = RequestMethod.DELETE,
    							headers="Accept=application/xml, application/json")
	public @ResponseBody Result deleteEmployee(@PathVariable("id") Long id) {
    	logger.debug("Provider has received request to delete employee with id: " + id);
 
    	// Call service here
    	boolean deleteed = employeeService.delete(id);
    	Result result = new Result();
    	
    	if(deleteed)
    		result.setSuccess(VALUE_AS_TRUE);
    	else
    		result.setSuccess(VALUE_AS_FALSE);
    	
    	return result;
    }
}
