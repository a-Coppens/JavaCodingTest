package codingTest;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class Employee implements Serializable {

	/// User Input Variables
	private String firstName;
	private String lastName;
	private int salary;
	private Date paymentStartDate; // Maybe a better way?
	private double superRate; // Int or Double? need to know for implementation on calcSuper
	
	/// Calculated Variables
	private String payPeriod;
	private int grossIncome;
	private int incomeTax;
	private int netIncome;
	private int superAmount;
	
	public Employee(String fName, String lName, int salary, double superRate /*Date startDate*/ )
	{
		this.firstName = fName;
		this.lastName = lName;
		this.salary = salary;
		//this.paymentStartDate = startDate;
		this.superRate = superRate;
	}
	
	
	public void generatePayslip()
	{
		//payPeriod = calcPayPeriod();
		grossIncome = calcGrossIncome();
		incomeTax = calcIncomeTax();
		netIncome = calcNetIncome();
		superAmount = calcSuper();
	}
	
	private String calcPayPeriod()
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
	}
	
	private int calcGrossIncome()
	{
		return Math.round(salary / 12);
	}
	
	private int calcIncomeTax()
	{
		int incomeTax = 0;
		int taxableIncome;
		
		/// NIL Income tax threshold
		if(salary <= 18200)
		{
			incomeTax = 0;
		}
		/// 19c for each $1 over $18,200
		else if(salary <= 37000)
		{
			taxableIncome = salary - 18200;
			incomeTax = (int) Math.round((taxableIncome * 0.19) / 12);
		}
		/// $3572 + 32.5c for each $1 over $37,000
		else if(salary <= 87000)
		{
			taxableIncome = salary - 37000;
			incomeTax = (int) Math.round((3572 + taxableIncome * 0.325) / 12);
		}
		/// $19822 + 37c for each $1 over $87,000
		else if(salary <= 180000)
		{
			taxableIncome = salary - 87000;
			incomeTax = (int) Math.round((19822 + taxableIncome * 0.37) / 12);
		}
		/// $54232 + 45c for each $1 over $180,000
		else if(salary > 180000)
		{
			taxableIncome = salary - 180000;
			incomeTax = (int) Math.round((54232 + taxableIncome * 0.45) / 12);
		}
		
		return incomeTax;
		
	}
	
	private int calcNetIncome()
	{
		return grossIncome - incomeTax;
	}
	
	private int calcSuper()
	{
		/// divide by 100 assuming we get raw integer %; e.g 9% from user and not 0.09
		return (int) (grossIncome * superRate);
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public int getSalary() {
		return salary;
	}


	public Date getPaymentStartDate() {
		return paymentStartDate;
	}


	public double getSuperRate() {
		return superRate;
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
