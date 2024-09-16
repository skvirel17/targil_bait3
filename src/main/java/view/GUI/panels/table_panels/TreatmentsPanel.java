package view.GUI.panels.table_panels;

import view.GUI.actions.OpenPanelAction;
import view.GUI.mainScreen.SystemUsersGUI;
import view.GUI.panels.table_panels.edit_panels.EditTreatmentsPanel;
import model.Treatment;

import javax.swing.*;
import java.util.Map;

import static view.GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static view.GUI.mainScreen.SystemUsersGUI.hospital;
import static view.GUI.panels.table_panels.edit_panels.EditTreatmentsPanel.EDIT_TREATMENT_PANEL;

public class TreatmentsPanel extends TablePanel {
    public static final String TREATMENTS_PANEL = "TREATMENTS_PANEL";

    private static final Object[] columns = {"SERIAL_NUMBER", "DESCRIPTION"};

    public JPanel itemPanel;

    public TreatmentsPanel(Map<Integer, Treatment> treatments) {
        super(mapData(treatments), columns, EDIT_TREATMENT_PANEL);

        //Adding Edit button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        Treatment editTreatment= hospital.getRealTreatment(
                                (Integer) this.getContent().getModel().
                                        getValueAt(this.getContent().getSelectedRow(), 0));
                        ((EditTreatmentsPanel)itemPanel).fillFromObject(editTreatment);

                    }

                }
        );

        //Adding Delete button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        hospital.removeTreatment(
                                hospital.getRealTreatment(
                                        (Integer) this.getContent().getModel().getValueAt(this.getContent().getSelectedRow(), 0)));
                        this.reloadData(hospital.getTreatments());
                    }
                }
        );
    }

    private static Object[][] mapData(Map<Integer, Treatment> map) {
        Object[][] data = new Object[map.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Treatment> entry : map.entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue().getDescription();
            i++;
        }

        return data;
    }

    public void reloadData(Map<Integer, Treatment> treatmentMap) {
        reloadData(mapData(treatmentMap), columns);
    }

    @Override
    public String getPanelStringKey() {
        return TREATMENTS_PANEL;
    }
}
