package GUI.dto;

import model.Patient;
import model.Visit;
import enums.BiologicalSex;
import enums.HealthFund;

import java.util.Date;
import java.util.HashSet;

public class PatientListOptionDTO extends Patient {

    public PatientListOptionDTO(int id, String firstName, String lastName, Date birthDate, String address,
                                String phoneNumber, String email, String gender, HashSet<Visit> visitsList,
                                HealthFund healthFund, BiologicalSex biologicalSex) {
        super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, visitsList, healthFund, biologicalSex);
    }

    public static PatientListOptionDTO map(Patient patient) {
        return new PatientListOptionDTO(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                patient.getAddress(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getGender(),
                patient.getVisitsList(),
                patient.getHealthFund(),
                patient.getBiologicalSex()
        );
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (" + getPhoneNumber() + ")";
    }
}
