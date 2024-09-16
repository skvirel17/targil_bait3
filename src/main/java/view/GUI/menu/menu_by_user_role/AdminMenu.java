package view.GUI.menu.menu_by_user_role;

import view.GUI.menu.*;

import javax.swing.*;

public class AdminMenu extends JMenuBar{
    private static AdminMenu instance;

    private AdminMenu() {
        this.add(new BaseMenu());
        this.add(new AccountMenu("admin"));
        //this.add(new ServiceMenu());
        this.add(new DataMenu("admin"));
        this.add(new ToolsMenu("admin"));
    }

    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }
}
