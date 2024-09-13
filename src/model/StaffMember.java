package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import control.Hospital;
import enums.Specialization;
import exceptions.FutureDateException;
import utils.UtilsMethods;

public abstract class StaffMember extends Person implements Serializable {
	
	private Date workStartDate;
	private HashSet<Department> departments;
	private double salary;

	private  String passWord = "";

	private static final long serialVersionUID = 7665079918262989153L;

	//Constructors
	public StaffMember(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,
			HashSet<Department> departments, double salary) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender);
		setWorkStartDate(workStartDate);
		this.departments = departments;
		this.salary = salary;
		this.passWord = "";
	}
	
	public StaffMember(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber,
			String email, String gender, Date workStartDate,
			 double salary) {
		super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender);
		setWorkStartDate(workStartDate);
		this.departments = new HashSet<Department>();
		this.salary = salary;
	}

	//getters
	public Date getWorkStartDate() {
		return workStartDate;
	}

	public HashSet<Department> getDepartments() {
		return departments;
	}

	public double getSalary() {
		return salary;
	}
	
	public double getWorkTime() {
		//returns the WorkTime of the StaffMember, in days
		return UtilsMethods.dateDiffInDays(Hospital.TODAY, workStartDate);
	}

	//setters
	public void setWorkStartDate(Date workStartDate) {
		//if the workStartDate is after "today", it set to "today"
		if(workStartDate.after(Hospital.TODAY)) {
			throw new FutureDateException(workStartDate);
		}
		this.workStartDate = workStartDate;
	}

	public void setDepartments(HashSet<Department> departments) {
		this.departments = departments;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setPassWord(String passWord){
		this.passWord = passWord;
	}

	public String getPassWord(){
		return  passWord;
	}

	//add
	public boolean addDepartment(Department department) {
		if(department!=null&&!departments.contains(department)) {
			return departments.add(department);
		}
		return false;
	}
	
	//remove
	public boolean removeDepartment(Department department) {
		if(department!=null&&departments.contains(department)) {
			return departments.remove(department);
		}
		return false;
	}

	//toString based on the super.toString()
	@Override
	public String toString() {
		return super.toString()+", workStartDate=" + workStartDate  +
				", salary=" + salary
				;
	}
	
	public Department getDepartmentBySpecialization(Specialization specialization) {
		for(Department department:departments) {
			if (department.getSpecialization().equals(specialization)) {
				return department;
			}
		}
		return null;
	}
	
	
	
}
