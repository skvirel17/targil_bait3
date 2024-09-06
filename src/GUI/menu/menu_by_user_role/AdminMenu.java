package GUI.menu.menu_by_user_role;

import GUI.menu.*;

import javax.swing.*;

public class AdminMenu extends JMenuBar{
    private static AdminMenu instance;

    private AdminMenu() {
        this.add(new BaseMenu());
        this.add(new AccountMenu());
        this.add(new ServiceMenu());
        this.add(new DataMenu());
        this.add(new ToolsMenu());
    }

    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }
}
