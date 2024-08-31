package GUI.menu;

import javax.swing.*;

public class BaseMenu extends JMenu {

    private static final String MENU_APP = "App";
    private static final String MI_FILE_EXIT = "Exit";

    private static JMenuItem createExitItem() {
        JMenuItem exitItem = new JMenuItem(MI_FILE_EXIT);
        exitItem.addActionListener(e -> System.exit(0));
        return exitItem;
    }

    public BaseMenu() {
        super(MENU_APP);
        this.add(createExitItem());
    }
}
