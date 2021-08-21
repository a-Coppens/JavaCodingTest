package codingTest;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class PayslipController {
	
	@Autowired
	PayslipService payslipService;
	
	/// Controller For the Employee Class
	@GetMapping("/")
	public String index() {
		return "Hello from the index page, this endpoint has no function for now... try /generate to generate payslips!";

	}
	
	@PostMapping("/generate") 
	@ResponseBody
	public List<Payslip> generatePayslips(@RequestBody List<Employee> employees) throws JsonMappingException, JsonProcessingException {
		return payslipService.getAllPayslips(employees);
	}
}
