package GUI.panels.table_panels;

import GUI.panels.table_panels.edit_panels.EditMedicationPanel;
import model.Medication;

import java.util.Map;

import static GUI.panels.table_panels.edit_panels.EditMedicationPanel.EDIT_MEDICATION_PANEL;

public class MedicationsPanel extends TablePanel {

    public static final String MEDICATION_PANEL = "MEDICATION_PANEL";
    private static final Object[] columns = {"CODE", "NAME", "DOSAGE", "NUMBER_OF_DOSE"};

    public MedicationsPanel(Map<Integer, Medication> medications) {
        super(mapData(medications), columns, EDIT_MEDICATION_PANEL);
    }

    private static Object[][] mapData(Map<Integer, Medication> map) {
        Object[][] data = new Object[map.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Medication> entry : map.entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue().getName();
            data[i][2] = entry.getValue().getDosage();
            data[i][3] = entry.getValue().getNumberOfDose();
            i++;
        }

        return data;
    }

    @Override
    public String getPanelStringKey() {
        return MEDICATION_PANEL;
    }
}
