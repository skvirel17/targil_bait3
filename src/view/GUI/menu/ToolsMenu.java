package view.GUI.menu;

import view.GUI.actions.OpenPanelAction;
import view.GUI.mainScreen.SystemUsersGUI;

import javax.swing.*;

import static view.GUI.panels.AppointNewManagerPanel.APPOINT_NEW_MANAGER_PANEL;
import static view.GUI.panels.GetNumberOfDoctorsBySpecializationPanel.GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL;
import static view.GUI.panels.HowManyVisitBeforePanel.HOW_MANY_VISIT_BEFORE_PANEL;
import static view.GUI.panels.MedicationCalculatorPanel.MEDICATION_CALCULATOR_PANEL;
import static view.GUI.panels.StandardCheckPanel.STANDARD_CHECK_PANEL;

public class ToolsMenu extends JMenu {
    private static final String MENU_TOOLS = "Tools";
    private static final String MI_TOOLS_MEDICATION_CALCULATOR = "Medication Calculator";
    private static final String MI_TOOLS_HOW_MANY_VISIT_BEFORE = "How many visit before";
    private static final String MI_TOOLS_GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION = "Get number of doctor by specialization";
    private static final String MI_TOOLS_STANDARD_CHECK = "Standard check";
    private static final String MI_TOOLS_APPOINT_NEW_MANAGER = "Appoint new manager";

    private static JMenuItem createItem(String itemName, String toPanel) {
        JMenuItem item = new JMenuItem(itemName);
        item.addActionListener(new OpenPanelAction(SystemUsersGUI.getMainScreen(), toPanel, SystemUsersGUI.getCardLayout()));
        return item;
    }

    public ToolsMenu(String role) {
        super(MENU_TOOLS);
        if (role.equals("admin")) {
            this.add(createItem(MI_TOOLS_MEDICATION_CALCULATOR, MEDICATION_CALCULATOR_PANEL));
            this.add(createItem(MI_TOOLS_HOW_MANY_VISIT_BEFORE, HOW_MANY_VISIT_BEFORE_PANEL));
            this.add(createItem(MI_TOOLS_GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION, GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL));
            this.add(createItem(MI_TOOLS_STANDARD_CHECK, STANDARD_CHECK_PANEL));
            this.add(createItem(MI_TOOLS_APPOINT_NEW_MANAGER, APPOINT_NEW_MANAGER_PANEL));
        } else {
            this.add(createItem(MI_TOOLS_MEDICATION_CALCULATOR, MEDICATION_CALCULATOR_PANEL));
            this.add(createItem(MI_TOOLS_HOW_MANY_VISIT_BEFORE, HOW_MANY_VISIT_BEFORE_PANEL));
            this.add(createItem(MI_TOOLS_STANDARD_CHECK, STANDARD_CHECK_PANEL));
        }
    }
}
