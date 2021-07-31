package codingTest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/// Controller For the Employee Class
	@GetMapping("/")
	public String index() {
		return "Hello from the index page, this endpoint has no function for now.. try /generate to generate payslips!";
	}
	
	@GetMapping("/generate") 
	public ArrayList<Employee> generatePayslips() {
		return employeeService.getAllEmployees();
	}
}
