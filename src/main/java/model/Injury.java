package model;

import java.util.ArrayList;
import java.util.HashSet;

import control.Hospital;
import enums.Specialization;
import utils.MyFileLogWriter;

public class Injury extends MedicalProblem implements IntensiveCareMedicalProblem{
	
	private double commonRecoveryTime;
	private String location;
	
	//constructors
	public Injury(String name, Department department, HashSet<Treatment> treatmentsList,
			double commonRecoveryTime, String location) {
		super("i",name, department, treatmentsList);
		this.commonRecoveryTime = commonRecoveryTime;
		this.location = location;
	}
	
	public Injury(String name, Department department, double commonRecoveryTime,
			String location) {
		super("i",name, department);
		this.commonRecoveryTime = commonRecoveryTime;
		this.location = location;
	}

	//getters
	public double getCommonRecoveryTime() {
		return commonRecoveryTime;
	}

	public String getLocation() {
		return location;
	}
	
	//setters
	public void setCommonRecoveryTime(double commonRecoveryTime) {
		this.commonRecoveryTime = commonRecoveryTime;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	//toString based on the super.toString()
	@Override
	public String toString() {
		return "Injury ["+ super.toString()+", commonRecoveryTime=" + commonRecoveryTime + ", location=" + location 
				 + "]";
	}
	
	@Override
	public void setIntensiveCare() {
		setDepartment(Hospital.getInstance().searchDepartmentBySpecialization(Specialization.IntensiveCare));
		
	}

	@Override
	public void describeSpecialProperties() {
		MyFileLogWriter.println(getCode()+", commonRecoveryTime=" + commonRecoveryTime + ", location=" + location );
		
	}
	
	
	
	
}
