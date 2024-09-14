package view.GUI.panels;

import view.GUI.tools.Method2;

import static view.GUI.mainScreen.SystemUsersGUI.hospital;

public class MedicationCalculatorPanel extends BasePanel {
    public static final String MEDICATION_CALCULATOR_PANEL = "MEDICATION_CALCULATOR_PANEL";
    public MedicationCalculatorPanel(){
        super();
        Method2.placeComponents(this, hospital);
    }
}
