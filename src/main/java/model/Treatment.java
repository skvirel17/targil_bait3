package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import javax.lang.model.element.NestingKind;

public class Treatment implements Serializable {
	
	private int serialNumber;
	private String description;
	private HashSet<Medication>medicationsList;
	private HashSet<Doctor>doctorsList;
	private HashSet<MedicalProblem>medicalProblemsList;
	
	
	//constructors
	public Treatment(int serialNumber,String description, HashSet<Medication> medicationsList, HashSet<Doctor> doctorsList,
			HashSet<MedicalProblem> medicalProblemsList) {
		super();
		this.serialNumber = serialNumber;
		this.description=description;
		this.medicationsList = medicationsList;
		this.doctorsList = doctorsList;
		this.medicalProblemsList = medicalProblemsList;
	}


	public Treatment(int serialNumber,String description) {
		super();
		this.serialNumber = serialNumber;
		this.description=description;
		this.medicationsList = new HashSet<>();
		this.doctorsList = new HashSet<>();
		this.medicalProblemsList = new HashSet<>();
	}
	

	//getters
	public int getSerialNumber() {
		return serialNumber;
	}


	public String getDescription() {
		return description;
	}


	public HashSet<Medication> getMedicationsList() {
		return medicationsList;
	}


	public HashSet<Doctor> getDoctorsList() {
		return doctorsList;
	}


	public HashSet<MedicalProblem> getMedicalProblemsList() {
		return medicalProblemsList;
	}

	//setters
	public void setDescription(String description) {
		this.description = description;
	}


	public void setMedicationsList(HashSet<Medication> medicationsList) {
		this.medicationsList = medicationsList;
	}


	public void setDoctorsList(HashSet<Doctor> doctorsList) {
		this.doctorsList = doctorsList;
	}


	public void setMedicalProblemsList(HashSet<MedicalProblem> medicalProblemsList) {
		this.medicalProblemsList = medicalProblemsList;
	}


	//hashCode and equals according to the PK
	@Override
	public int hashCode() {
		return Objects.hash(serialNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Treatment other = (Treatment) obj;
		return serialNumber == other.serialNumber;
	}

	//add
	public boolean addMedication(Medication medication) {
		if(medication==null) {
			return false;
		}
		if(medicationsList.contains(medication)) {
			return false;
		}
		return medicationsList.add(medication);
		
	}
	
	public boolean addMedicalProblem(MedicalProblem medicalProblem) {
		if(medicalProblem==null) {
			return false;
		}
		if(medicalProblemsList.contains(medicalProblem)) {
			return false;
		}
		return medicalProblemsList.add(medicalProblem);
		
	}
	
	public boolean addDoctor(Doctor doctor) {
		if(doctor==null) {
			return false;
		}
		if(doctorsList.contains(doctor)) {
			return false;
		}
		return doctorsList.add(doctor);
		
	}
	
	public boolean addFracture(Fracture Fracture) {
		return addMedicalProblem(Fracture);
	}
	
	public boolean addInjury(Injury injury) {
		return addMedicalProblem(injury);
	}
	
	public boolean addDisease(Disease disease) {
		return addMedicalProblem(disease);
	}
	
	//remove
	public boolean removeDisease(Disease disease) {
		return removeMedicalProblem(disease);
	}
	
	public boolean removeDoctor(Doctor doctor) {
		if(doctor==null) {
			return false;
		}
		if (!doctorsList.contains(doctor)) {
			return false;
		}
		return doctorsList.remove(doctor);
	}
	
	public boolean removeFracture(Fracture fracture) {
		return removeMedicalProblem(fracture);
	}
	
	public boolean removeInjury(Injury injury) {
		return removeMedicalProblem(injury);
	}
	
	public boolean removeMedication(Medication medication) {
		if(medication==null) {
			return false;
		}
		if (!medicationsList.contains(medication)) {
			return false;
		}
		return medicationsList.remove(medication);
	}
	
	public boolean removeMedicalProblem(MedicalProblem medicalProblem) {
		if(medicalProblem==null) {
			return false;
		}
		if (!medicalProblemsList.contains(medicalProblem)) {
			return false;
		}
		return medicalProblemsList.remove(medicalProblem);
	}


	//toString
	@Override
	public String toString() {
		return "Treatment [serialNumber=" + serialNumber + ", description=" + description + "]";
	}


}
