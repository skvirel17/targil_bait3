package view.GUI.panels;

import view.GUI.tools.Method5;

import static view.GUI.mainScreen.SystemUsersGUI.hospital;

public class AppointNewManagerPanel extends BasePanel{
    public static final String APPOINT_NEW_MANAGER_PANEL = "APPOINT_NEW_MANAGER_PANEL";
    public AppointNewManagerPanel(){
        super();
        Method5.placeComponents(this, hospital);
    }
}
