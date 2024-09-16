package view.GUI.menu.menu_by_user_role;

import view.GUI.menu.BaseMenu;

import javax.swing.*;

public class LogoutMenu extends JMenuBar {
    private static LogoutMenu instance;

    private LogoutMenu() {
        this.add(new BaseMenu());
    }

    public static LogoutMenu getInstance() {
        if (instance == null) {
            instance = new LogoutMenu();
        }
        return instance;
    }
}
