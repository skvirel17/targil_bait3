package GUI.panels.table_panels;

import GUI.actions.OpenPanelAction;
import GUI.mainScreen.SystemUsersGUI;
import GUI.panels.table_panels.edit_panels.EditDepartmentPanel;
import GUI.panels.table_panels.edit_panels.EditPatientsPanel;
import model.Department;
import model.Patient;

import javax.swing.*;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static GUI.mainScreen.SystemUsersGUI.hospital;
import static GUI.panels.table_panels.edit_panels.EditDepartmentPanel.EDIT_DEPARTMENT_PANEL;
import static GUI.panels.table_panels.edit_panels.EditPatientsPanel.EDIT_PATIENT_PANEL;

public class PatientsPanel extends TablePanel {
    public static final String PATIENTS_PANEL = "PATIENTS_PANEL";

    private static final Object[] columns = {"ID", "FIRST_NAME", "LAST_NAME", "BIRTH_DATE", "ADDRESS", "PHONE_NUMBER",
            "EMAIL", "GENDER", "HELTH_FUND", "BIOLOGICAL_GENDER"};

    public JPanel itemPanel;

    public PatientsPanel(Map<Integer, Patient> patients) {
        super(mapData(patients), columns, EDIT_PATIENT_PANEL);

        //Adding Edit button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        Patient editPatient = hospital.getRealPatient(
                                (Integer) this.getContent().getModel().
                                        getValueAt(this.getContent().getSelectedRow(), 0));
                        ((EditPatientsPanel)itemPanel).fillFromObject(editPatient);

                    }

                }
        );

        //Adding Delete button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        hospital.removePatient(
                                hospital.getRealPatient(
                                        (Integer) this.getContent().getModel().getValueAt(this.getContent().getSelectedRow(), 0)));
                        this.reloadData(hospital.getPatients());
                    }
                }
        );
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

    @Override
    public String getPanelStringKey() {
        return PATIENTS_PANEL;
    }

    public void reloadData(Map<Integer, Patient> patients) {
        reloadData(mapData(patients), columns);
    }
}
