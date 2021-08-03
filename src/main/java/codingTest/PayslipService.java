package codingTest;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service 
public class PayslipService {
	
	public ArrayList<Payslip> getAllPayslips(HttpEntity<String> jsonBody) throws JsonMappingException, JsonProcessingException {
		/*
		 * ArrayList<Payslip> listOfPayslips = new ArrayList<>();
		 * 
		 * 
		 * listOfPayslips.add(new Payslip(new Employee("David", "Rudd", 60050, 0.09)));
		 * listOfPayslips.add(new Payslip(new Employee("Ryan", "Chen", 120000, 0.1)));
		 * 
		 * for(Payslip payslip : listOfPayslips) { payslip.generatePayslip(); }
		 */
		
		String json = jsonBody.getBody();
		ArrayList<Payslip> listOfPayslips = new ObjectMapper().readValue(json, new TypeReference<ArrayList<Payslip>>() {});
		
		for(Payslip payslip : listOfPayslips) {
			payslip.generatePayslip(); 
		}
		return listOfPayslips;
	}
	
}
