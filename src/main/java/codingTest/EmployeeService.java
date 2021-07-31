package codingTest;

import java.util.ArrayList;

import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
		
		listOfEmployees.add(new Employee("David", "Rudd", 60050, 0.09));
		listOfEmployees.add(new Employee("Ryan", "Chen", 120000, 0.1));
		
		for(Employee employee : listOfEmployees) {
			employee.generatePayslip();
		}
		
		
		return listOfEmployees;
	}
	
}
