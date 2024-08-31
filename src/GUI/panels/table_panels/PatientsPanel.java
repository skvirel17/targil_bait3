package GUI.panels.table_panels;

import GUI.mainScreen.SystemUsersGUI;
import model.Patient;

import java.util.Map;

public class PatientsPanel extends TablePanel {

    private static final Object[] columns = {"ID", "FIRST_NAME", "LAST_NAME", "BIRTH_DATE", "ADDRESS", "PHONE_NUMBER",
            "EMAIL", "GENDER", "HELTH_FUND", "BIOLOGICAL_GENDER"};

    public PatientsPanel(Map<Integer, Patient> patients) {
        super(mapData(patients), columns, SystemUsersGUI.EDIT_DEPARTMENT_PANEL);
    }

    private static Object[][] mapData(Map<Integer, Patient> dataMap) {
        Object[][] data = new Object[dataMap.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Patient> entry : dataMap.entrySet()){
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
            data[i][8] = entry.getValue().getHealthFund().toString();
            data[i][9] = entry.getValue().getBiologicalSex().toString();
            i++;
        }

        return data;
    }
}
