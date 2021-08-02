package codingTest;

import java.util.Date;

public class Employee {

	/// User Input Variables
	private String firstName;
	private String lastName;
	private int salary;
	private Date paymentStartDate; // Maybe a better way?
	private double superRate; // Int or Double? need to know for implementation on calcSuper

	public Employee(String fName, String lName, int salary, double superRate /* Date startDate */ ) {
		this.firstName = fName;
		this.lastName = lName;
		this.salary = salary;
		// this.paymentStartDate = startDate;
		this.superRate = superRate;
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

}
