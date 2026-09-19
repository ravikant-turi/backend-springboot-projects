package com.java.main;

public class MainTest {
	public static void main(String[] args) {

		Employee emp = new Employee.EmployeeBuilder()
				.setId(101)
				.setName("Ravikant")
				.setAddress("address")
				.setDepartment("department")
				.setSalary(2342.234)
				.build();
		emp.display();
	}

}
