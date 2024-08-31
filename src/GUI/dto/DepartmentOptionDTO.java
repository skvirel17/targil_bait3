package GUI.dto;

import enums.Specialization;
import model.*;

import java.util.HashSet;

public class DepartmentOptionDTO extends Department {
    public DepartmentOptionDTO(int number, String name, Doctor manager, String location, Specialization specialization, HashSet<StaffMember> staffMembersList) {
        super(number, name, manager, location, specialization, staffMembersList);
    }

    public static DepartmentOptionDTO map(Department department) {
        return new DepartmentOptionDTO(department.getNumber(), department.getName(), department.getmanager(),
                department.getLocation(), department.getSpecialization(), department.getStaffMembersList());
    }

    @Override
    public String toString() {
        return getName();
    }

}
