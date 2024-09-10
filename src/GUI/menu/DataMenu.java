package GUI.menu;

import GUI.actions.OpenPanelAction;
import GUI.mainScreen.SystemUsersGUI;

import javax.swing.*;

import static GUI.panels.table_panels.DepartmentsPanel.DEPARTMENTS_PANEL;
import static GUI.panels.table_panels.MedicalProblemsPanel.MEDICAL_PROBLEMS_PANEL;
import static GUI.panels.table_panels.MedicationsPanel.MEDICATION_PANEL;
import static GUI.panels.table_panels.PatientsPanel.PATIENTS_PANEL;
import static GUI.panels.table_panels.StaffMembersPanel.STAFF_MEMBERS_PANEL;
import static GUI.panels.table_panels.TreatmentsPanel.TREATMENTS_PANEL;
import static GUI.panels.table_panels.VisitsPanel.VISITS_PANEL;

public class DataMenu extends JMenu {

    private static final String MENU_DATA = "Data";
    private static final String MI_DATA_DEPARTMENTS = "Departments";
    private static final String MI_DATA_MEDICAL_PROBLEMS = "Medical Problems";
    private static final String MI_DATA_STAFF_MEMBERS = "Staff Members";
    private static final String MI_DATA_MEDICATIONS = "Medications";
    private static final String MI_DATA_PATIENTS = "Patients";
    private static final String MI_DATA_TREATMENTS = "Treatments";
    private static final String MI_DATA_VISITS = "Visits";

    private static JMenuItem createItem(String itemName, String toPanel) {
        JMenuItem item = new JMenuItem(itemName);
        item.addActionListener(new OpenPanelAction(SystemUsersGUI.getMainScreen(), toPanel, SystemUsersGUI.getCardLayout()));
        return item;
    }

    public DataMenu(String role) {
        super(MENU_DATA);
        if (role.equals("admin")) {
            this.add(createItem(MI_DATA_DEPARTMENTS, DEPARTMENTS_PANEL));
            this.add(createItem(MI_DATA_MEDICAL_PROBLEMS, MEDICAL_PROBLEMS_PANEL));
            this.add(createItem(MI_DATA_STAFF_MEMBERS, STAFF_MEMBERS_PANEL));
            this.add(createItem(MI_DATA_MEDICATIONS, MEDICATION_PANEL));
            this.add(createItem(MI_DATA_PATIENTS, PATIENTS_PANEL));
            this.add(createItem(MI_DATA_TREATMENTS, TREATMENTS_PANEL));
            this.add(createItem(MI_DATA_VISITS, VISITS_PANEL));
        } else {
            this.add(createItem(MI_DATA_DEPARTMENTS, DEPARTMENTS_PANEL));
            this.add(createItem(MI_DATA_MEDICAL_PROBLEMS, MEDICAL_PROBLEMS_PANEL));
            this.add(createItem(MI_DATA_MEDICATIONS, MEDICATION_PANEL));
            this.add(createItem(MI_DATA_PATIENTS, PATIENTS_PANEL));
            this.add(createItem(MI_DATA_TREATMENTS, TREATMENTS_PANEL));
            this.add(createItem(MI_DATA_VISITS, VISITS_PANEL));
        }
    }
}
