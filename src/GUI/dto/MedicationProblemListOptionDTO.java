package GUI.dto;

import model.Department;
import model.MedicalProblem;
import model.Treatment;

import java.util.HashSet;

public class MedicationProblemListOptionDTO extends MedicalProblem {
    public MedicationProblemListOptionDTO(String type, String name, Department department, HashSet<Treatment> treatmentsList) {
        super(type, name, department, treatmentsList);
    }

    public static MedicationProblemListOptionDTO map(MedicalProblem medicalProblem) {
        return new MedicationProblemListOptionDTO(medicalProblem.getCode().substring(0, 1), medicalProblem.getName(),
                medicalProblem.getDepartment(), medicalProblem.getTreatmentsList());
    }

    @Override
    public String toString() {
        return "[" + getCode() + "] " + getName();
    }

    @Override
    public void describeSpecialProperties() {

    }
}
