package GUI.menu;

import GUI.actions.OpenPanelAction;
import GUI.mainScreen.SystemUsersGUI;
import GUI.panels.BasePanel;

import javax.swing.*;

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

    public ToolsMenu() {
        super(MENU_TOOLS);
        this.add(createItem(MI_TOOLS_MEDICATION_CALCULATOR, SystemUsersGUI.MEDICATION_CALCULATOR_PANEL));
        this.add(createItem(MI_TOOLS_HOW_MANY_VISIT_BEFORE, SystemUsersGUI.HOW_MANY_VISIT_BEFORE_PANEL));
        this.add(createItem(MI_TOOLS_GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION, SystemUsersGUI.GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL));
        this.add(createItem(MI_TOOLS_STANDARD_CHECK, SystemUsersGUI.STANDARD_CHECK_PANEL));
        this.add(createItem(MI_TOOLS_APPOINT_NEW_MANAGER, SystemUsersGUI.APPOINT_NEW_MANAGER_PANEL));
    }
}
