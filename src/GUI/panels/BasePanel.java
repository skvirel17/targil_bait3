package GUI.panels;

import GUI.mainScreen.SystemUsersGUI;

import javax.swing.*;
import java.awt.*;

public class BasePanel extends JPanel {

    JFrame baseFrame;
    JPanel base;
    CardLayout cardLayout;
    private static final String PANEL_STRING_KEY = "";

    public BasePanel() {
        this.base = SystemUsersGUI.getMainScreen();
        this.cardLayout = SystemUsersGUI.getCardLayout();
        this.baseFrame = SystemUsersGUI.getMainFrame();
    }

    public String getPanelStringKey() {
        return PANEL_STRING_KEY;
    }
}
