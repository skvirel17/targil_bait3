package GUI.panels.table_panels;

import GUI.actions.OpenPanelAction;
import GUI.mainScreen.SystemUsersGUI;
import GUI.panels.table_panels.edit_panels.EditDepartmentPanel;
import GUI.panels.table_panels.edit_panels.EditMedicalProblemPanel;
import GUI.panels.table_panels.edit_panels.EditMedicationPanel;
import model.Department;
import model.Medication;

import javax.swing.*;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static GUI.mainScreen.SystemUsersGUI.hospital;
import static GUI.panels.table_panels.edit_panels.EditMedicationPanel.EDIT_MEDICATION_PANEL;

public class MedicationsPanel extends TablePanel {

    public static final String MEDICATION_PANEL = "MEDICATION_PANEL";
    private static final Object[] columns = {"CODE", "NAME", "DOSAGE", "NUMBER_OF_DOSE"};
    public JPanel itemPanel;

    public MedicationsPanel(Map<Integer, Medication> medications) {
        super(mapData(medications), columns, EDIT_MEDICATION_PANEL);

        //Adding Edit button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        Medication editMedication = hospital.getRealMedication(
                                (Integer) this.getContent().getModel().
                                        getValueAt(this.getContent().getSelectedRow(), 0));
                        ((EditMedicationPanel)itemPanel).fillFromObject(editMedication);
                    }
                }
        );

        //Adding Delete button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        hospital.removeMedication(
                                hospital.getRealMedication(
                                        (Integer) this.getContent().getModel().getValueAt(this.getContent().getSelectedRow(), 0)));
                        this.reloadData(hospital.getMedications());
                    }
                }
        );
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

    public void reloadData(Map<Integer, Medication> medicationMap) {
        reloadData(mapData(medicationMap), columns);
    }
}
