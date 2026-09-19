//package com.java.main;
//
//public class EmployeeBuilder {
//
//	private int id;
//	private String name;
//	private Double salary;
//	private String address;
//	private String department;
//
//	public EmployeeBuilder setId(int id) {
//		this.id = id;
//		return this;
//	}
//
//	public EmployeeBuilder setName(String name) {
//		this.name = name;
//		return this;
//	}
//
//	public EmployeeBuilder setSalary(Double salary) {
//		this.salary = salary;
//		return this;
//	}
//
//	public EmployeeBuilder setAddress(String address) {
//		this.address = address;
//		return this;
//	}
//
//	public EmployeeBuilder setDepartment(String department) {
//		this.department = department;
//		return this;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public Double getSalary() {
//		return salary;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//	
//	public Employee build() {
//		return new Employee(id,name,salary,address,department);
//	}
//
//}
