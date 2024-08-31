package GUI.panels.table_panels;

import GUI.mainScreen.SystemUsersGUI;
import model.Department;
import java.util.Map;

import static GUI.panels.table_panels.edit_panels.EditDepartmentPanel.EDIT_DEPARTMENT_PANEL;

public class DepartmentsPanel extends TablePanel {
    public static final String DEPARTMENTS_PANEL = "DEPARTMENTS_PANEL";

    private static final Object[] columns = {"ID", "NAME", "MANAGER", "LOCATION", "SPECIALIZATION"};

    public DepartmentsPanel(Map<Integer, Department> departments) {
        super(mapData(departments), columns, EDIT_DEPARTMENT_PANEL);
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
