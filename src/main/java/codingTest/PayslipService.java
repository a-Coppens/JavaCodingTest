package codingTest;

import java.util.ArrayList;

import org.springframework.stereotype.Service;


@Service
public class PayslipService {
	
	public ArrayList<Payslip> getAllPayslips() {
		ArrayList<Payslip> listOfPayslips = new ArrayList<>();
		
		
		listOfPayslips.add(new Payslip(new Employee("David", "Rudd", 60050, 0.09)));
		listOfPayslips.add(new Payslip(new Employee("Ryan", "Chen", 120000, 0.1)));
		
		for(Payslip payslip : listOfPayslips) {
			payslip.generatePayslip();
		}
		
		
		return listOfPayslips;
	}
	
}
