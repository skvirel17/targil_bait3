package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

import utils.UtilsMethods;

public class Visit implements Serializable {

	private int number;//PK
	private Patient patient;
	private Date startDate;
	private Date endDate;
	private HashSet<MedicalProblem>medicalProblemsList;
	private HashSet<Treatment> treatmentsList;
	
	
	//constructors
	public Visit(int number, Patient patient, Date startDate, Date endDate, HashSet<MedicalProblem>medicalProblemsList,
			HashSet<Treatment> treatmentsList) {
		super();
		this.number = number;
		this.patient = patient;
		this.startDate = startDate;
		this.endDate = endDate;
		this.medicalProblemsList = medicalProblemsList;
		this.treatmentsList = treatmentsList;
	}
	
	public Visit(int number, Patient patient, Date startDate, Date endDate) {
		super();
		this.number = number;
		this.patient = patient;
		this.startDate = startDate;
		this.endDate = endDate;
		this.medicalProblemsList = new HashSet<>();
		this.treatmentsList = new HashSet<>();
	}

	

	//getters
	public double visitLength() {
		//returns the length of the visit, in days
		return UtilsMethods.dateDiffInDays(startDate, endDate);
	}
	
	public int getNumber() {
		return number;
	}

	public Patient getPatient() {
		return patient;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public HashSet<MedicalProblem> getMedicalProblemsList() {
		return medicalProblemsList;
	}

	public HashSet<Treatment> getTreatmentsList() {
		return treatmentsList;
	}

	//setters
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setMedicalProblemsList(HashSet<MedicalProblem> medicalProblemsList) {
		this.medicalProblemsList = medicalProblemsList;
	}

	public void setTreatmentsList(HashSet<Treatment> treatmentsList) {
		this.treatmentsList = treatmentsList;
	}

	//add
	public boolean addMedicalProblem(MedicalProblem medicalProblem) {
		if(medicalProblem==null) {
			return false;
		}
		if (medicalProblemsList.contains(medicalProblem)) {
			return false;
		}
		return medicalProblemsList.add(medicalProblem);
	}
	
	public boolean addDisease(Disease disease) {
		return addMedicalProblem(disease);
	}
	
	public boolean addFracture(Fracture fracture) {
		return addMedicalProblem(fracture);
	}
	
	public boolean addInjury(Injury injury) {
		return addMedicalProblem(injury);
	}
	
	public boolean addTreatment(Treatment treatment) {
		if(treatment==null) {
			return false;
		}
		if (treatmentsList.contains(treatment)) {
			return false;
		}
		return treatmentsList.add(treatment);
	}
	
	//remove
	public boolean removeMedicalProblem(MedicalProblem medicalProblem) {
		if(medicalProblem==null) {
			return false;
		}
		if (!medicalProblemsList.contains(medicalProblem)) {
			return false;
		}
		return medicalProblemsList.remove(medicalProblem);
	}
	
	public boolean removeDisease(Disease disease) {
		return removeMedicalProblem(disease);
	}
	public boolean removeFracture(Fracture fracture) {
		return removeMedicalProblem(fracture);
	}
	
	public boolean removeInjury(Injury injury) {
		return removeMedicalProblem(injury);
	}
	
	public boolean removeTreatment(Treatment treatment) {
		if(treatment==null) {
			return false;
		}
		if (!treatmentsList.contains(treatment)) {
			return false;
		}
		return treatmentsList.remove(treatment);
	}

	//toString
	@Override
	public String toString() {
		return "Visit [number=" + number + ", patient=" + patient + ", startDate=" + startDate + ", endDate=" + endDate
				+  "]";
	}

	//hashCode and equals according to the PK
	@Override
	public int hashCode() {
		return Objects.hash(number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visit other = (Visit) obj;
		return number == other.number;
	}
	
}
