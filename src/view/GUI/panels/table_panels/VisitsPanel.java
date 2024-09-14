package view.GUI.panels.table_panels;

import view.GUI.actions.OpenPanelAction;
import view.GUI.mainScreen.SystemUsersGUI;
import view.GUI.panels.table_panels.edit_panels.EditVisitsPanel;
import model.Visit;
import utils.UtilsMethods;

import javax.swing.*;
import java.util.Map;

import static view.GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static view.GUI.mainScreen.SystemUsersGUI.hospital;
import static view.GUI.panels.table_panels.edit_panels.EditVisitsPanel.EDIT_VISIT_PANEL;

public class VisitsPanel extends TablePanel {
    public static final String VISITS_PANEL = "VISITS_PANEL";

    private static final Object[] columns = {"NUMBER", "PATIENT", "START_DATE", "END_DATE"};

    public JPanel itemPanel;

    public VisitsPanel(Map<Integer, Visit> visits) {
        super(mapData(visits), columns, EDIT_VISIT_PANEL);

        //Adding Edit button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        Visit editVisit = hospital.getRealVisit(
                                (Integer) this.getContent().getModel().
                                        getValueAt(this.getContent().getSelectedRow(), 0));
                        ((EditVisitsPanel)itemPanel).fillFromObject(editVisit);
                    }
                }
        );

        //Adding Delete button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        hospital.removeVisit(
                                hospital.getRealVisit(
                                        (Integer) this.getContent().getModel().getValueAt(this.getContent().getSelectedRow(), 0)));
                        this.reloadData(hospital.getVisits());
                    }
                }
        );
    }

    private static Object[][] mapData(Map<Integer, Visit> map) {
        Object[][] data = new Object[map.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Visit> entry : map.entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = (entry.getValue().getPatient() == null) ? "" :
                    entry.getValue().getPatient().getLastName() + " " + entry.getValue().getPatient().getFirstName();
            data[i][2] = UtilsMethods.format(entry.getValue().getStartDate());
            data[i][3] = UtilsMethods.format(entry.getValue().getEndDate());
            i++;
        }

        return data;
    }

    public void reloadData(Map<Integer, Visit> visits) {
        reloadData(mapData(visits), columns);
    }

    @Override
    public String getPanelStringKey() {
        return VISITS_PANEL;
    }
}
