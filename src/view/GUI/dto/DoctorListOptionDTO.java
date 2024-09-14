package view.GUI.dto;

import enums.Specialization;
import model.Department;
import model.Doctor;

import java.util.Date;
import java.util.HashSet;

public class DoctorListOptionDTO extends Doctor {

    public DoctorListOptionDTO(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber, String email, String gender, Date workStartDate, HashSet<Department> departments, double salary, int licenseNumber, boolean isFinishInternship, Specialization specialization) {
        super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, workStartDate, departments, salary, licenseNumber, isFinishInternship, specialization);
    }

    public static DoctorListOptionDTO map(Doctor doctor) {
        return new DoctorListOptionDTO(doctor.getId(), doctor.getFirstName(), doctor.getLastName(), doctor.getBirthDate(),
                doctor.getAddress(), doctor.getPhoneNumber(), doctor.getEmail(), doctor.getGender(),
                doctor.getWorkStartDate(), doctor.getDepartments(), doctor.getSalary(), doctor.getLicenseNumber(),
                doctor.isFinishInternship(), doctor.getSpecialization());
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " - " + getSpecialization().name() + " (" + getPhoneNumber() + ")";
    }
}
