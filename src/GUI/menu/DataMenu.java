package GUI.menu;

import GUI.actions.OpenPanelAction;
import GUI.mainScreen.SystemUsersGUI;

import javax.swing.*;

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

    public DataMenu() {
        super(MENU_DATA);
        this.add(createItem(MI_DATA_DEPARTMENTS, SystemUsersGUI.DEPARTMENTS_PANEL));
        this.add(createItem(MI_DATA_MEDICAL_PROBLEMS, SystemUsersGUI.MEDICAL_PROBLEMS_PANEL));
        this.add(createItem(MI_DATA_STAFF_MEMBERS, SystemUsersGUI.STAFF_MEMBERS_PANEL));
        this.add(createItem(MI_DATA_MEDICATIONS, SystemUsersGUI.MEDICATION_PANEL));
        this.add(createItem(MI_DATA_PATIENTS, SystemUsersGUI.PATIENTS_PANEL));
        this.add(createItem(MI_DATA_TREATMENTS, SystemUsersGUI.TREATMENTS_PANEL));
        this.add(createItem(MI_DATA_VISITS, SystemUsersGUI.VISITS_PANEL));
    }
}
