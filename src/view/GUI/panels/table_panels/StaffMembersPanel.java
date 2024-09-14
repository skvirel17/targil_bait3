package view.GUI.panels.table_panels;

import view.GUI.actions.OpenPanelAction;
import view.GUI.mainScreen.SystemUsersGUI;
import view.GUI.panels.table_panels.edit_panels.EditStaffMembersPanel;
import model.StaffMember;
import utils.UtilsMethods;

import javax.swing.*;
import java.util.Map;

import static view.GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static view.GUI.mainScreen.SystemUsersGUI.hospital;
import static view.GUI.panels.table_panels.edit_panels.EditStaffMembersPanel.EDIT_STAFF_MEMBERS_PANEL;

public class StaffMembersPanel extends TablePanel {
    public static final String STAFF_MEMBERS_PANEL = "STAFF_MEMBERS_PANEL";

    private static final Object[] columns = {"ID", "FIRST_NAME", "LAST_NAME", "BIRTH_DATE", "ADDRESS", "PHONE_NUMBER",
        "EMAIL", "GENDER", "WORK_START_DATE", "SALARY"};

    public static JPanel itemPanel;

    public StaffMembersPanel(Map<Integer, StaffMember> staffMembers) {
        super(mapData(staffMembers), columns, EDIT_STAFF_MEMBERS_PANEL);

        //Adding Edit button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        StaffMember editStaffMember = hospital.getStaffMember(
                                (Integer) this.getContent().getModel().
                                        getValueAt(this.getContent().getSelectedRow(), 0));
                        ((EditStaffMembersPanel)itemPanel).fillFromObject(editStaffMember);
                        ((EditStaffMembersPanel)itemPanel).disablePositionField();
                    }
                }
        );

        //Adding Delete button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        hospital.removeStaffMember(
                                hospital.getStaffMember(
                                        (Integer) this.getContent().getModel().getValueAt(this.getContent().getSelectedRow(), 0)));
                        this.reloadData(hospital.getStaffMembers());
                    }
                }
        );
    }

    private static Object[][] mapData(Map<Integer, StaffMember> dataMap) {
        Object[][] data = new Object[dataMap.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, StaffMember> entry : dataMap.entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = entry.getValue().getFirstName();
            data[i][2] = entry.getValue().getLastName();
            data[i][3] = UtilsMethods.format(entry.getValue().getBirthDate());
            data[i][4] = entry.getValue().getAddress();
            data[i][5] = entry.getValue().getPhoneNumber();
            data[i][6] = entry.getValue().getEmail();
            data[i][7] = entry.getValue().getGender().equals("M") ? "MALE" :
                    entry.getValue().getGender().equals("F") ? "FEMALE" :
                    entry.getValue().getGender().equals("O") ? "OTHER" : entry.getValue().getGender();
            data[i][8] = entry.getValue().getWorkStartDate();
            data[i][9] = entry.getValue().getSalary();
            i++;
        }

        return data;
    }

    public void reloadData(Map<Integer, StaffMember> staffMemberMap) {
        reloadData(mapData(staffMemberMap), columns);
    }

    @Override
    public String getPanelStringKey() {
        return STAFF_MEMBERS_PANEL;
    }
}
