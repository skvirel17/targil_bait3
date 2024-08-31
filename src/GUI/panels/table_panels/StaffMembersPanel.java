package GUI.panels.table_panels;

import model.StaffMember;

import java.util.Map;

public class StaffMembersPanel extends TablePanel {

    private static final Object[] columns = {"ID", "FIRST_NAME", "LAST_NAME", "BIRTH_DATE", "ADDRESS", "PHONE_NUMBER",
        "EMAIL", "GENDER", "WORK_START_DATE", "SALARY"};

    public StaffMembersPanel(Map<Integer, StaffMember> staffMembers) {
        super(mapData(staffMembers), columns, "");
    }

    private static Object[][] mapData(Map<Integer, StaffMember> dataMap) {
        Object[][] data = new Object[dataMap.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, StaffMember> entry : dataMap.entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue().getFirstName();
            data[i][2] = entry.getValue().getLastName();
            data[i][3] = entry.getValue().getBirthDate();
            data[i][4] = entry.getValue().getAddress();
            data[i][5] = entry.getValue().getPhoneNumber();
            data[i][6] = entry.getValue().getEmail();
            data[i][7] = entry.getValue().getGender().equals("M") ? "MALE" :
                    entry.getValue().getGender().equals("F") ? "FEMALE" :
                    entry.getValue().getGender().equals("O") ? "OTHER" : entry.getValue().getGender();
            data[i][8] = entry.getValue().getWorkStartDate();
            data[i][9] = entry.getValue().getSalary();
            i++;
        }

        return data;
    }
}
