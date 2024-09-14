package view.GUI.panels.table_panels;

import view.GUI.actions.OpenPanelAction;
import view.GUI.mainScreen.SystemUsersGUI;
import view.GUI.panels.table_panels.edit_panels.EditDepartmentPanel;
import model.Department;

import javax.swing.*;
import java.util.Map;

import static view.GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static view.GUI.mainScreen.SystemUsersGUI.hospital;
import static view.GUI.panels.table_panels.edit_panels.EditDepartmentPanel.EDIT_DEPARTMENT_PANEL;

public class DepartmentsPanel extends TablePanel {
    public static final String DEPARTMENTS_PANEL = "DEPARTMENTS_PANEL";

    private static final Object[] columns = {"ID", "NAME", "MANAGER", "LOCATION", "SPECIALIZATION"};
    public JPanel itemPanel;

    public DepartmentsPanel(Map<Integer, Department> departments) {
        super(mapData(departments), columns, EDIT_DEPARTMENT_PANEL);

        //Adding Edit button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        Department editDepartment = hospital.getRealDepartment(
                                (Integer) this.getContent().getModel().
                                        getValueAt(this.getContent().getSelectedRow(), 0));
                        ((EditDepartmentPanel)itemPanel).fillFromObject(editDepartment);

                    }

                }
        );

        //Adding Delete button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        hospital.removeDepartment(
                                hospital.getRealDepartment(
                                        (Integer) this.getContent().getModel().getValueAt(this.getContent().getSelectedRow(), 0)));
                        this.reloadData(hospital.getDepartments());
                    }
                }
        );
    }

    private static Object[][] mapData(Map<Integer, Department> departments) {
        Object[][] data = new Object[departments.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Department> departmentEntry : departments.entrySet()){
            data[i][0] = departmentEntry.getKey();
            data[i][1] = departmentEntry.getValue().getName();
            data[i][2] = departmentEntry.getValue().getmanager().getLastName() + " " + departmentEntry.getValue().getmanager().getFirstName();
            data[i][3] = departmentEntry.getValue().getLocation();
            data[i][4] = departmentEntry.getValue().getSpecialization();
            i++;
        }

        return data;
    }

    public void reloadData(Map<Integer, Department> departments) {
        reloadData(mapData(departments), columns);
    }

    @Override
    public String getPanelStringKey() {
        return DEPARTMENTS_PANEL;
    }
}
