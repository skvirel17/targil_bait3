package GUI.menu;

import GUI.mainScreen.SystemUsersGUI;
import GUI.actions.OpenPanelAction;
import GUI.menu.menu_by_user_role.LogoutMenu;
import GUI.panels.LoginPanel;
import GUI.panels.table_panels.StaffMembersPanel;
import GUI.panels.table_panels.edit_panels.EditDepartmentPanel;
import GUI.panels.table_panels.edit_panels.EditStaffMembersPanel;
import model.StaffMember;
import sun.rmi.runtime.Log;

import javax.swing.*;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.panels.table_panels.StaffMembersPanel.itemPanel;

public class AccountMenu extends JMenu{

    private static final String MENU_ACCOUNT = "Account";
    private static final String MI_ACCOUNT_LOGOUT = "Logout";
    private static final String MI_PERSONAL_INFO = "Personal info";

    private static JMenuItem createLogoutItem() {
        JMenuItem logoutItem = new JMenuItem(MI_ACCOUNT_LOGOUT);
        logoutItem.addActionListener(e -> {
            SystemUsersGUI.getMainFrame().setJMenuBar(LogoutMenu.getInstance());
            SystemUsersGUI.clearSession();
            new OpenPanelAction(SystemUsersGUI.getMainScreen(), LoginPanel.LOGIN_PANEL, SystemUsersGUI.getCardLayout()).actionPerformed(e);
        });
        return logoutItem;
    }

    private static JMenuItem createItem() {
        JMenuItem personalItem = new JMenuItem(MI_PERSONAL_INFO);
        personalItem.addActionListener(e -> {
            (new OpenPanelAction(SystemUsersGUI.getMainScreen(), EditStaffMembersPanel.EDIT_STAFF_MEMBERS_PANEL, getCardLayout())).actionPerformed(e);
            StaffMember editStaffMember = hospital.getStaffMember(getSession().getId());
            ((EditStaffMembersPanel)itemPanel).fillFromObject(editStaffMember);
            ((EditStaffMembersPanel)itemPanel).disablePositionField();
        });
        return personalItem;
    }

    public AccountMenu(String role) {
        super(MENU_ACCOUNT);
        if (role.equals("doctor") || role.equals("nurse")) {
            this.add(createItem());
        }
        this.add(createLogoutItem());

    }
}
