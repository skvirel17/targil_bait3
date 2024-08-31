package GUI.menu;

import GUI.mainScreen.SystemUsersGUI;
import GUI.actions.OpenPanelAction;

import javax.swing.*;

import static GUI.mainScreen.SystemUsersGUI.LOGIN_PANEL;

public class AccountMenu extends JMenu{

    private static final String MENU_ACCOUNT = "Account";
    private static final String MI_ACCOUNT_LOGOUT = "Logout";

    private static JMenuItem createLogoutItem() {
        JMenuItem logoutItem = new JMenuItem(MI_ACCOUNT_LOGOUT);
        logoutItem.addActionListener(new OpenPanelAction(SystemUsersGUI.getMainScreen(), LOGIN_PANEL, SystemUsersGUI.getCardLayout()));
        return logoutItem;
    }

    public AccountMenu() {
        super(MENU_ACCOUNT);
        this.add(createLogoutItem());
    }
}
