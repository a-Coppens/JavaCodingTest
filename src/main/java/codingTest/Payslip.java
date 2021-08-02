package codingTest;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;

public class Payslip implements Serializable {
	
	/// Each Payslip object contains one Employee
	private Employee employee;
	
	/// Calculated based on Employee
	private String payPeriod;
	private int grossIncome;
	private int incomeTax;
	private int netIncome;
	private int superAmount;
	
	public Payslip(Employee employee) {
		this.employee = new Employee(employee.getFirstName(), employee.getLastName(), 
									employee.getSalary(), employee.getSuperRate());
	}
	
	public void generatePayslip() {
		//payPeriod = calcPayPeriod();
		grossIncome = calcGrossIncome();
		incomeTax = calcIncomeTax();
		netIncome = calcNetIncome();
		superAmount = calcSuper();
	}
	
	/// #TODO FIX
	/// Not going to work for what we want
	/*private String calcPayPeriod()
	{
		Calendar cal = Calendar.getInstance();
		/// Set the calendar date to the payment start date
		cal.setTime(paymentStartDate);
		/// get the month of cal as a string based on the locale of UK which AUS is based on
		String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
		
		/// Assumes that we don't want to store the pay date in a database as we have a string
		/// if we were wanting to store in a database we would have two variables... the start and end dates of the payment month
		
		/// getActualMaximum returns the end date of the month based on the year so leap years are inclusive
		return "01 " + month + " - " + cal.getActualMaximum(Calendar.DAY_OF_MONTH) + " " + month;
	}*/
	
	private int calcGrossIncome() {
		return Math.round(this.employee.getSalary() / 12);
	}
	
	private int calcIncomeTax() {
		int incomeTax = 0;
		int taxableIncome;

		/// NIL Income tax threshold
		if (this.employee.getSalary() <= 18200) {
			incomeTax = 0;
		}
		/// 19c for each $1 over $18,200
		else if (this.employee.getSalary() <= 37000) {
			taxableIncome = this.employee.getSalary() - 18200;
			incomeTax = (int) Math.round((taxableIncome * 0.19) / 12);
		}
		/// $3572 + 32.5c for each $1 over $37,000
		else if (this.employee.getSalary() <= 87000) {
			taxableIncome = this.employee.getSalary() - 37000;
			incomeTax = (int) Math.round((3572 + taxableIncome * 0.325) / 12);
		}
		/// $19822 + 37c for each $1 over $87,000
		else if (this.employee.getSalary() <= 180000) {
			taxableIncome = this.employee.getSalary() - 87000;
			incomeTax = (int) Math.round((19822 + taxableIncome * 0.37) / 12);
		}
		/// $54232 + 45c for each $1 over $180,000
		else if (this.employee.getSalary() > 180000) {
			taxableIncome = this.employee.getSalary() - 180000;
			incomeTax = (int) Math.round((54232 + taxableIncome * 0.45) / 12);
		}

		return incomeTax;

	}

	public Employee getEmployee() {
		return employee;
	}

	private int calcNetIncome() {
		return grossIncome - incomeTax;
	}

	private int calcSuper() {
		/// divide by 100 assuming we get raw integer %; e.g 9% from user and not 0.09
		return (int) (grossIncome * this.employee.getSuperRate());
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public int getGrossIncome() {
		return grossIncome;
	}

	public int getIncomeTax() {
		return incomeTax;
	}

	public int getNetIncome() {
		return netIncome;
	}

	public int getSuperAmount() {
		return superAmount;
	}

	
}
