package GUI.menu;

import GUI.mainScreen.SystemUsersGUI;
import GUI.actions.OpenPanelAction;

import javax.swing.*;

public class ServiceMenu extends JMenu{

    private static final String MENU_SERVICE = "Service";
    private static final String MI_SERVICE_EDIT_PROFILE = "Edit Personal Details";
    private static final String MI_SERVICE_ADD_MEDICATION = "Add Medication";
    private static final String MI_SERVICE_ADD_TREATMENT = "Add Treatment";
    private static final String MI_SERVICE_ADD_VISIT_INFO = "Add Issue and Treatment to Visit";
    private static final String MI_SERVICE_ADD_SYSTEM_CURE = "Add Medication, Issue, and Treatment to System";
    private static final String MI_SERVICE_VIEW_DATA = "View System Data";

    private static JMenuItem createItem(String itemName, String toPanel) {
        JMenuItem item = new JMenuItem(itemName);
        item.addActionListener(new OpenPanelAction(SystemUsersGUI.getMainScreen(), toPanel, SystemUsersGUI.getCardLayout()));
        return item;
    }

    public ServiceMenu() {
        super(MENU_SERVICE);
        this.add(createItem(MI_SERVICE_EDIT_PROFILE, SystemUsersGUI.EDIT_PROFILE_PANEL));
        this.add(createItem(MI_SERVICE_ADD_MEDICATION, SystemUsersGUI.ADD_MEDICATION_PANEL));
        this.add(createItem(MI_SERVICE_ADD_TREATMENT, SystemUsersGUI.ADD_TREATMENT_PANEL));
        this.add(createItem(MI_SERVICE_ADD_VISIT_INFO, SystemUsersGUI.ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL));
        this.add(createItem(MI_SERVICE_ADD_SYSTEM_CURE, SystemUsersGUI.ADD_ALL_PANEL));
        this.add(createItem(MI_SERVICE_VIEW_DATA, SystemUsersGUI.VIEW_DATA_PANEL));
    }
}
