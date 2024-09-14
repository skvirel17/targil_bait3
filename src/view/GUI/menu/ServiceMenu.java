package view.GUI.menu;

import view.GUI.mainScreen.SystemUsersGUI;
import view.GUI.actions.OpenPanelAction;

import javax.swing.*;

import static view.GUI.panels.AddIssueAndTreatmentToVisitPanel.ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL;
import static view.GUI.panels.AddMedicationPanel.ADD_MEDICATION_PANEL;
import static view.GUI.panels.AddTreatmentPanel.ADD_TREATMENT_PANEL;
import static view.GUI.panels.AllPanel.ADD_ALL_PANEL;
import static view.GUI.panels.EditProfilePanel.EDIT_PROFILE_PANEL;
import static view.GUI.panels.ViewDataPanel.VIEW_DATA_PANEL;

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
        this.add(createItem(MI_SERVICE_EDIT_PROFILE, EDIT_PROFILE_PANEL));
        this.add(createItem(MI_SERVICE_ADD_MEDICATION, ADD_MEDICATION_PANEL));
        this.add(createItem(MI_SERVICE_ADD_TREATMENT, ADD_TREATMENT_PANEL));
        this.add(createItem(MI_SERVICE_ADD_VISIT_INFO, ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL));
        this.add(createItem(MI_SERVICE_ADD_SYSTEM_CURE, ADD_ALL_PANEL));
        this.add(createItem(MI_SERVICE_VIEW_DATA, VIEW_DATA_PANEL));
    }
}
