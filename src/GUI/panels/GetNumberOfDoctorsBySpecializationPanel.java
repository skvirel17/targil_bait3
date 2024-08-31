package GUI.panels;

import GUI.tools.Method3;
import static GUI.mainScreen.SystemUsersGUI.hospital;

public class GetNumberOfDoctorsBySpecializationPanel extends BasePanel{
    public GetNumberOfDoctorsBySpecializationPanel(){
        super();
        Method3.placeComponents(this, hospital);
    }
}
