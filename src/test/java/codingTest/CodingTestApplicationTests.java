package codingTest;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CodingTestApplicationTests { 
		
	@Test
	@DisplayName ("Name Check")
	void SingleEmployee() {
		Payslip payslipTest = new Payslip(new Employee("Foo", "Bar", 75000, 0.09, 1));
		
		Assert.hasText("Foo", payslipTest.getEmployee().getFirstName());
		Assert.doesNotContain("James", payslipTest.getEmployee().getFirstName(), "Does Not Contain James");
	}
	
	@Test
	@DisplayName ("Date Check")
	void paymentDate() {
		Payslip payslipTest = new Payslip(new Employee("Foo", "Bar", 75000, 0.09, 1));
		payslipTest.generatePayslip();
				
		Assert.hasText("Febuary", payslipTest.getFromDate());
	}
		
	@Test
	@DisplayName ("No Income Tax")
	void isNoTax() {
		Payslip payslipTest = new Payslip(new Employee("Foo", "Bar", 18200, 0.09, 1));
		payslipTest.generatePayslip();
		
		Assert.isTrue(payslipTest.getIncomeTax() == 0, "Contains no income tax");
	}
	
	@Test
	@DisplayName ("First Tier Income Tax")
	void isFirstTierTax() {
		Payslip payslipTest = new Payslip(new Employee("Foo", "Bar", 37000, 0.09, 1));
		payslipTest.generatePayslip();
		
		/// 19c for each $1 over $18,200
		System.out.print("\nfirst is: " + Math.round((float)3572 / 12));
		Assert.isTrue(payslipTest.getIncomeTax() == Math.round((float)3572 / 12), "Contains First Tier Income Tax");
	}
	
	@Test
	@DisplayName ("Second Tier Income Tax")
	void isSecondTierTax() {
		Payslip payslipTest = new Payslip(new Employee("Foo", "Bar", 87000, 0.09, 1));
		payslipTest.generatePayslip();
		
		/// $3572 + 32.5c for each $1 over $37,000
		System.out.print("\nsecond is: " + Math.round((float)19822 / 12));
		Assert.isTrue(payslipTest.getIncomeTax() == Math.round((float)19822 / 12), "Contains Second Tier Income Tax");
	}
	
	@Test
	@DisplayName ("Third Tier Income Tax")
	void isThirdTierTax() {
		Payslip payslipTest = new Payslip(new Employee("Foo", "Bar", 180000, 0.09, 1));
		payslipTest.generatePayslip();
		
		/// $19822 + 37c for each $1 over $87,000
		System.out.print("\nthird is: " + Math.round((float)54232 / 12));
		Assert.isTrue(payslipTest.getIncomeTax() == Math.round((float)54232 / 12), "Contains Third Tier Income Tax");
	}
	
	@Test
	@DisplayName ("Fourth Tier Income Tax")
	void isFourthTierTax() {
		Payslip payslipTest = new Payslip(new Employee("Foo", "Bar", 300000, 0.09, 1));
		payslipTest.generatePayslip();
		
		/// $54232 + 45c for each $1 over $180,000
		System.out.print("\nfourth is: " + Math.round((float)108232 / 12));
		Assert.isTrue(payslipTest.getIncomeTax() == Math.round((float)108232 / 12), "Contains Fourth Tier Income Tax");
	}
}
