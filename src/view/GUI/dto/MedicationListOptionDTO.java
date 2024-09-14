package view.GUI.dto;

import model.Medication;

public class MedicationListOptionDTO extends Medication {

    public MedicationListOptionDTO(int code, String name, double dosage, int numberOfDose) {
        super(code, name, dosage, numberOfDose);
    }

    // Метод для создания DTO на основе объекта Medication
    public static MedicationListOptionDTO map(Medication medication) {
        return new MedicationListOptionDTO(medication.getCode(), medication.getName(),
                medication.getDosage(), medication.getNumberOfDose());
    }

    @Override
    public String toString() {
        return getName() + " - " + getDosage() + "mg, " + getNumberOfDose() + " doses";
    }
}
