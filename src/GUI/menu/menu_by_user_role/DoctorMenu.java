package GUI.menu.menu_by_user_role;

import GUI.menu.*;

import javax.swing.*;

public class DoctorMenu extends JMenuBar {
    private static DoctorMenu instance;

    private DoctorMenu() {
        this.add(new BaseMenu());
        //редактирование личных данных
        this.add(new AccountMenu("doctor"));
        this.add(new DataMenu("doctor"));
        this.add(new ToolsMenu("doctor"));
    }

    public static DoctorMenu getInstance() {
        if (instance == null) {
            instance = new DoctorMenu();
        }
        return instance;
    }
}
