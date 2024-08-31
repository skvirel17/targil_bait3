package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DoctorListOptionDTO;
import GUI.dto.StaffMemberListOptionDTO;
import GUI.panels.BasePanel;
import control.Hospital;
import enums.Specialization;
import model.Doctor;
import model.StaffMember;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.bind.JAXBContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.mainScreen.SystemUsersGUI.getCardLayout;

public class EditDepartmentPanel extends EditPanel {

    private JComboBox<Doctor> createManagerContent() {
        JComboBox<Doctor> managerContent = new JComboBox<>();
        for (Map.Entry staffMember : hospital.getStaffMembers().entrySet()){
            if(staffMember.getValue() instanceof Doctor){
                managerContent.addItem(DoctorListOptionDTO.map((Doctor)staffMember.getValue()));
            }
        }
        return managerContent;
    }

    public  EditDepartmentPanel(BasePanel prev){
        super(prev);

        JLabel departmentNameLabel = new JLabel("Department Name:");
        JTextField departmentNameText = new JTextField();

        JLabel managerLabel = new JLabel("Manager:");
        JComboBox<Doctor> managerContent = createManagerContent();

        JLabel locationLabel = new JLabel("Location:");
        JTextField locationText = new JTextField();

        JLabel staffMembersLabel = new JLabel("Staff members:");
        JScrollPane activeStaffPane = new JScrollPane();
        DefaultListModel<String> activeStaffListModel = new DefaultListModel<>();
        JList<String> activeStaffList = new JList<>(activeStaffListModel);
        activeStaffPane.add(activeStaffList);

        DefaultListModel<String> allStaffListModel = new DefaultListModel<>();
        JList<String> allStaffList = new JList<>(allStaffListModel);
        JScrollPane allStaffPane = new JScrollPane(allStaffList);

        for (StaffMember member : hospital.getStaffMembers().values()) {
            allStaffListModel.addElement(StaffMemberListOptionDTO.map(member).toString());
        }

        JButton button = new JButton("Select Sublist");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JLabel specializationLabel = new JLabel("Specialization:");
        JComboBox<Specialization> specializationText = new JComboBox<>();
        for (Specialization spec : Specialization.values()){
            specializationText.addItem(spec);
        }

        JButton saveDepartmentButton = new JButton("Save");
        saveDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Doctor newDoctor = getNewInfo();
                JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Changes will be lost! \nDo you want to continue?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                }
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.Group staffGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeStaffPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(button))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allStaffPane));
        GroupLayout.Group staffGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeStaffPane)
                        .addComponent(button)
                        .addComponent(allStaffPane));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(departmentNameLabel)
                                .addComponent(managerLabel)
                                .addComponent(locationLabel)
                                .addComponent(staffMembersLabel)
                                .addComponent(specializationLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(departmentNameText)
                                .addComponent(managerContent)
                                .addComponent(locationText)
                                .addGroup(staffGroupHor)
                                .addComponent(specializationText)
                                .addComponent(saveDepartmentButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(departmentNameLabel)
                                .addComponent(departmentNameText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(managerLabel)
                                .addComponent(managerContent))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(locationLabel)
                                .addComponent(locationText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(staffMembersLabel)
                                .addGroup(staffGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(specializationLabel)
                                .addComponent(specializationText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveDepartmentButton))

        );
    }
}

