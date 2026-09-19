package com.java.main;

public class Employee {

	private int id;
	private String name;
	private Double salary;
	private String address;
	private String department;

	private Employee(EmployeeBuilder employeeBuilder) {
		this.id = employeeBuilder.id;
		this.name = employeeBuilder.name;
		this.salary = employeeBuilder.salary;
		this.address = employeeBuilder.address;
		this.department = employeeBuilder.department;
	}

	public void display() {
		System.out.println(id);
		System.out.println(name);
		System.out.println(salary);
		System.out.println(address);
		System.out.println(department);
	}

	public static class EmployeeBuilder {

		private int id;
		private String name;
		private Double salary;
		private String address;
		private String department;

		public EmployeeBuilder setId(int id) {
			this.id = id;
			return this;
		}

		public EmployeeBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public EmployeeBuilder setSalary(Double salary) {
			this.salary = salary;
			return this;
		}

		public EmployeeBuilder setAddress(String address) {
			this.address = address;
			return this;
		}

		public EmployeeBuilder setDepartment(String department) {
			this.department = department;
			return this;

		}

		public Employee build() {
			return new Employee(this);
		}
	}
}
