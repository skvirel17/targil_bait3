package view.GUI.dto;

import model.Department;
import model.StaffMember;

import java.util.Date;
import java.util.HashSet;

public class StaffMemberListOptionDTO extends StaffMember {


    public StaffMemberListOptionDTO(int id, String firstName, String lastName, Date birthDate, String address, String phoneNumber, String email, String gender, Date workStartDate, HashSet<Department> departments, double salary) {
        super(id, firstName, lastName, birthDate, address, phoneNumber, email, gender, workStartDate, departments, salary);
    }

    public static StaffMemberListOptionDTO map(StaffMember member) {
        return new StaffMemberListOptionDTO(member.getId(), member.getFirstName(), member.getLastName(),
                member.getBirthDate(), member.getAddress(), member.getPhoneNumber(), member.getEmail(),
                member.getGender(), member.getWorkStartDate(), member.getDepartments(), member.getSalary());
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName()  + " (" + getPhoneNumber() + ")";
    }

}
