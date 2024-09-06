package GUI.menu.menu_by_user_role;

import GUI.menu.*;

import javax.swing.*;

public class DoctorMenu extends JMenuBar {
    private static DoctorMenu instance;

    private DoctorMenu() {
        this.add(new BaseMenu());
        this.add(new AccountMenu());
        this.add(new ServiceMenu());
        this.add(new DataMenu());
        this.add(new ToolsMenu());
    }

    public static DoctorMenu getInstance() {
        if (instance == null) {
            instance = new DoctorMenu();
        }
        return instance;
    }
}
