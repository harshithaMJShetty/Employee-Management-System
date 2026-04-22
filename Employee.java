package com.Employee;

public class Employee {
private String id;
private String name;
private String email;
private String department;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getdepartment() {
	return department;
}
public void setdepartment(String department) {
	this.department = department;
}
public static int getI() {
	return i;
}
public static void setI(int i) {
	Employee.i= i;
}
private static int i = 1;
public Employee(String id,String name,String email,String department) {
	this.id = id;
	this.name= name;
	this.email= email;
	this.department=department;
}

}
