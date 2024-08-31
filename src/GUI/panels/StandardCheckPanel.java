package GUI.panels;

import GUI.tools.Method4;

import static GUI.mainScreen.SystemUsersGUI.hospital;

public class StandardCheckPanel extends BasePanel{
    public static final String STANDARD_CHECK_PANEL = "STANDARD_CHECK_PANEL";
    public StandardCheckPanel(){
        super();
        Method4.placeComponents(this, hospital);
    }
}
