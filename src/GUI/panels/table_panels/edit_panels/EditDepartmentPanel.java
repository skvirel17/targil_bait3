package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DoctorListOptionDTO;
import GUI.dto.StaffMemberListOptionDTO;
import GUI.panels.BasePanel;
import GUI.panels.table_panels.DepartmentsPanel;
import enums.Specialization;
import model.Department;
import model.Doctor;
import model.StaffMember;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.mainScreen.SystemUsersGUI.getCardLayout;

public class EditDepartmentPanel extends EditPanel {

    public static final String EDIT_DEPARTMENT_PANEL = "EDIT_DEPARTMENT_PANEL";
    //ID
    private Department department;
    //Name
    private JLabel departmentNameLabel;
    private JTextField departmentNameText;
    //Manager
    private JLabel managerLabel;
    private JComboBox<Doctor> managerContent;
    //Location
    private JLabel locationLabel;
    private JTextField locationText;
    //StaffMembers
    private JLabel staffMembersLabel;
    private DefaultListModel<StaffMemberListOptionDTO> activeStaffListModel;
    private JList<StaffMemberListOptionDTO> activeStaffList;
    private JScrollPane activeStaffPane;
    private DefaultListModel<StaffMemberListOptionDTO> allStaffListModel;
    private JList<StaffMemberListOptionDTO> allStaffList;
    private JScrollPane allStaffPane;
    private JButton selectStaffListButton;
    //Specialization
    private JLabel specializationLabel;
    private JComboBox<Specialization> specializationContent;
    //Save button
    private JButton saveDepartmentButton;
    //Back button
    private JButton backButton;


    public  EditDepartmentPanel(BasePanel prev){
        super(prev);

        department = null;

        buildNameField();
        buildManagerField();
        buildLocationField();
        buildStaffMembersField();
        buildSpecializationField();
        buildSaveButton(prev, this);
        buildBackButton(prev, this);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                clearPanel();
            }
        });

        compose();
    }

    private JComboBox<Doctor> createManagerContent() {
        JComboBox<Doctor> managerContent = new JComboBox<>();
        for (StaffMember staffMember : hospital.getStaffMembers().values()){
            if(staffMember instanceof Doctor){
                managerContent.addItem(DoctorListOptionDTO.map((Doctor)staffMember));
            }
        }
        return managerContent;
    }

    private void buildNameField() {
        departmentNameLabel = new JLabel("Department Name:");
        departmentNameText = new JTextField();
    }

    private void buildManagerField() {
        managerLabel = new JLabel("Manager:");
        managerContent = createManagerContent();
    }

    private void buildLocationField() {
        locationLabel = new JLabel("Location:");
        locationText = new JTextField();
    }

    private void buildStaffMembersField() {
        staffMembersLabel = new JLabel("Staff members:");

        activeStaffListModel = new DefaultListModel<>();
        activeStaffList = new JList<>(activeStaffListModel);
        activeStaffPane = new JScrollPane(activeStaffList);

        allStaffListModel = new DefaultListModel<>();
        allStaffList = new JList<>(allStaffListModel);
        allStaffPane = new JScrollPane(allStaffList);

        for (StaffMember member : hospital.getStaffMembers().values()) {
            allStaffListModel.addElement(StaffMemberListOptionDTO.map(member));
        }

        selectStaffListButton = new JButton("Select Sublist");
        selectStaffListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<StaffMemberListOptionDTO> selected = allStaffList.getSelectedValuesList();
                for (StaffMemberListOptionDTO item : selected) {
                    if (activeStaffListModel.indexOf(item) == -1) {
                        activeStaffListModel.addElement(item);
                    };
                }
            }
        });
    }

    private JComboBox<Specialization> createSpecializationContent() {
        specializationContent = new JComboBox<>();
        for (Specialization spec : Specialization.values()){
            specializationContent.addItem(spec);
        }
        return specializationContent;
    }

    private void buildSpecializationField() {
        specializationLabel = new JLabel("Specialization:");
        specializationContent = createSpecializationContent();
    }

    private void buildSaveButton(BasePanel prev, EditDepartmentPanel panel) {
        saveDepartmentButton = new JButton("Save");
        saveDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = hospital.generateNewDepartmentNumber();
                String name = departmentNameText.getText();
                Doctor manager = (Doctor) managerContent.getSelectedItem();
                String location = locationText.getText();
                Specialization spec = (Specialization) specializationContent.getSelectedItem();
                HashSet<StaffMember> staffMembers = new HashSet<>();
                for (int i = 0; i < activeStaffListModel.getSize(); i++) {
                    staffMembers.add(activeStaffListModel.get(i));
                }

                Department newDepartment = new Department(id, name, manager, location, spec, staffMembers);

                if (department != null) {
                    department.setName(name);
                    department.setmanager(manager);
                    department.setLocation(location);
                    department.setSpecialization(spec);
                    department.setStaffMembersList(staffMembers);
                }

                if (department != null || hospital.addDepartment(newDepartment)) {
                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                    ((DepartmentsPanel) prev).reloadData(hospital.getDepartments());
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void buildBackButton(BasePanel prev, EditDepartmentPanel panel) {
        backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Changes will be lost! \nDo you want to continue?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
                }
            }
        });
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.Group staffGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeStaffPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(selectStaffListButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allStaffPane));
        GroupLayout.Group staffGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeStaffPane)
                        .addComponent(selectStaffListButton)
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
                                .addComponent(specializationContent)
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
                                .addComponent(specializationContent))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveDepartmentButton))

        );
    }

    public void fillFromObject(Department department) {
        clearPanel();
        this.department = department;
        departmentNameText.setText(department.getName());
        for (int i = 0; i < managerContent.getItemCount(); i++) {
            if (managerContent.getItemAt(i).getId() == department.getmanager().getId()) {
                managerContent.setSelectedIndex(i);
            }
        }
        locationText.setText(department.getLocation());
        for (StaffMember staffMember : department.getStaffMembersList()) {
            activeStaffListModel.addElement(StaffMemberListOptionDTO.map(staffMember));
        }
        for (int i = 0; i < specializationContent.getItemCount(); i++) {
            if (specializationContent.getItemAt(i).equals(department.getSpecialization())) {
                specializationContent.setSelectedIndex(i);
            }
        }
    }

    private void clearPanel() {
        department = null;
        departmentNameText.setText("");
        locationText.setText("");
        activeStaffListModel.removeAllElements();
    }
}

