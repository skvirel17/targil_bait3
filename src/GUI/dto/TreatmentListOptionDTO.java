package GUI.dto;

import model.Doctor;
import model.MedicalProblem;
import model.Medication;
import model.Treatment;

import java.util.HashSet;

public class TreatmentListOptionDTO extends Treatment {
    public TreatmentListOptionDTO(int serialNumber, String description, HashSet<Medication> medicationsList,
                                  HashSet<Doctor> doctorsList, HashSet<MedicalProblem> medicalProblemsList) {
        super(serialNumber, description, medicationsList, doctorsList, medicalProblemsList);
    }

    public static TreatmentListOptionDTO map(Treatment treatment){
        return new TreatmentListOptionDTO(treatment.getSerialNumber(), treatment.getDescription(), treatment.getMedicationsList(),
                treatment.getDoctorsList(), treatment.getMedicalProblemsList());
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
