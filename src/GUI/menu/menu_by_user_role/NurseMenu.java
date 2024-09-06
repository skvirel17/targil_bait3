package GUI.menu.menu_by_user_role;

import GUI.menu.*;

import javax.swing.*;

public class NurseMenu extends JMenuBar {
    private static NurseMenu instance;

    private NurseMenu() {
        this.add(new BaseMenu());
        this.add(new AccountMenu());
        this.add(new ServiceMenu());
        this.add(new DataMenu());
        this.add(new ToolsMenu());
    }

    public static NurseMenu getInstance() {
        if (instance == null) {
            instance = new NurseMenu();
        }
        return instance;
    }
}
