package GUI.panels;

import GUI.actions.OpenPanelAction;

import javax.swing.*;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.panels.DashBoardPanel.DASHBOARD_PANEL;

public class ViewDataPanel extends JPanel {

    public static final String VIEW_DATA_PANEL = "VIEW_DATA_PANEL";
    public ViewDataPanel(){
        super();
        // פאנל לצפייה בכל הנתונים מלבד נתוני הצוות
        JLabel viewDataLabel = new JLabel("Viewing all system data except other staff members' data.");
        this.add(viewDataLabel);

        JButton backButton = new JButton("back");
        backButton.addActionListener(new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()));

        this.add(backButton);

    }
}
