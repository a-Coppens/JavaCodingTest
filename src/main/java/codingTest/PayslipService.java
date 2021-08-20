package codingTest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service 
public class PayslipService {
	
	public List<Payslip> getAllPayslips(List<Employee> employees) throws JsonMappingException, JsonProcessingException {
			
		List<Payslip> payslips = new ArrayList<Payslip>();
		
		for(Employee employee : employees) {
			Payslip payslip = new Payslip(employee);
			payslips.add(payslip);
		}
		
		for(Payslip payslip : payslips) {
			payslip.generatePayslip();
		}
		
		return payslips;
	}
	
}
