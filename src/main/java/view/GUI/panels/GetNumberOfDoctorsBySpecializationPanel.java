package view.GUI.panels;

import view.GUI.tools.Method3;
import static view.GUI.mainScreen.SystemUsersGUI.hospital;

public class GetNumberOfDoctorsBySpecializationPanel extends BasePanel{
    public static final String GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL = "GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL";
    public GetNumberOfDoctorsBySpecializationPanel(){
        super();
        Method3.placeComponents(this, hospital);
    }
}
