package GUI.dto;

import model.MedicalProblem;
import model.Patient;
import model.Treatment;
import model.Visit;

import java.util.Date;
import java.util.HashSet;

public class VisitListOptionDTO extends Visit {

    public VisitListOptionDTO(int number, Patient patient, Date startDate, Date endDate,
                              HashSet<MedicalProblem> medicalProblemsList,
                              HashSet<Treatment> treatmentsList) {
        super(number, patient, startDate, endDate, medicalProblemsList, treatmentsList);
    }

    public static VisitListOptionDTO map(Visit visit) {
        return new VisitListOptionDTO(
                visit.getNumber(),
                visit.getPatient(),
                visit.getStartDate(),
                visit.getEndDate(),
                visit.getMedicalProblemsList(),
                visit.getTreatmentsList()
        );
    }

    @Override
    public String toString() {
        return "Visit Number: " + getNumber() + ", Patient: " +
                ((getPatient() != null) ? getPatient().getFirstName() + " " + getPatient().getLastName() : "") +
                " (" + getStartDate().toString() + " - " + ((getEndDate() != null) ? getEndDate() : "...") + ")";
    }
}
