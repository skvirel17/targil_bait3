package GUI.panels;

import GUI.tools.Method2;
import model.Medication;

import javax.swing.*;

import static GUI.mainScreen.SystemUsersGUI.hospital;

public class MedicationCalculatorPanel extends BasePanel {
    public MedicationCalculatorPanel(){
        super();
        Method2.placeComponents(this, hospital);
    }
}
