package codingTest;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Payslip implements Serializable {
	
	/// Doesn't really matter here but gets rid of the warning
	private static final long serialVersionUID = 1L;

	/// Each Payslip object contains one Employee
	private Employee employee;
	
	/// Calculated based on Employee
	private String fromDate;
	private String toDate;
	private int grossIncome;
	private int incomeTax;
	private int superAmount;
	private int netIncome;
	
	
	public Payslip() { 
		this.employee = new Employee();
	}
	
	/// Constructor for manual tests
	public Payslip(Employee employee) {
		this.employee = new Employee(employee.getFirstName(), employee.getLastName(), 
						employee.getSalary(), employee.getSuperRate(), employee.getPaymentStartMonth());
	}
	
	public void generatePayslip() {
		setPayPeriod();
		grossIncome = calcGrossIncome();
		incomeTax = calcIncomeTax();
		netIncome = calcNetIncome();
		superAmount = calcSuper();
	}
	
	private void setPayPeriod()
	{
		int maxDayOfMonth;
		int payMonth = this.employee.getPaymentStartMonth();
		
		/// Converts an month int to month string where 0 = January, 11 = December
		String monthString = new DateFormatSymbols().getMonths()[payMonth];
		
		/// Creates a calendar based on the month given
		/// Assumes that the payslip is for the current year
		Calendar calendar = new GregorianCalendar(Year.now().getValue(), payMonth, 1);
		
		/// Finds the maximum number of days in a month based on our calendar
		/// Works for Leap years with the assumption made above
		maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		this.fromDate = "01 " + monthString;
		this.toDate = maxDayOfMonth + " " + monthString;
	}
	
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
			incomeTax = (int) Math.round((float)(taxableIncome * 0.19) / 12);
		}
		/// $3572 + 32.5c for each $1 over $37,000
		else if (this.employee.getSalary() <= 87000) {
			taxableIncome = this.employee.getSalary() - 37000;
			incomeTax = (int) Math.round((float)(3572 + taxableIncome * 0.325) / 12);
		}
		/// $19822 + 37c for each $1 over $87,000
		else if (this.employee.getSalary() <= 180000) {
			taxableIncome = this.employee.getSalary() - 87000;
			incomeTax = (int) Math.round((float)(19822 + taxableIncome * 0.37) / 12);
		}
		/// $54232 + 45c for each $1 over $180,000
		else if (this.employee.getSalary() > 180000) {
			taxableIncome = this.employee.getSalary() - 180000;
			incomeTax = (int) Math.round((float)(54232 + taxableIncome * 0.45) / 12);
		}

		return incomeTax;
	}
	
	private int calcNetIncome() {
		return grossIncome - incomeTax;
	}

	private int calcSuper() {
		return (int) (grossIncome * this.employee.getSuperRate());
	}

	public Employee getEmployee() {
		return employee;
	}

	public String getFromDate() {
		return fromDate;
	}
	
	public String getToDate() {
		return toDate;
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
	
	/// Set Employee values, seems a bit verbose? probably fine?
	public void setFirstName(String firstName) {
		employee.setFirstName(firstName);
	}

	public void setLastName(String lastName) {
		employee.setLastName(lastName);
	}

	public void setSalary(int salary) {
		employee.setSalary(salary);
	}

	public void setPaymentStartDate(int paymentStartDate) {
		employee.setPaymentStartDate(paymentStartDate);
	}

	public void setSuperRate(double superRate) {
		employee.setSuperRate(superRate);
	}
	
}
