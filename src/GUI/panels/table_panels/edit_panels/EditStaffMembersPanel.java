package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DepartmentOptionDTO;
import GUI.dto.StaffMemberListOptionDTO;
import GUI.dto.VisitListOptionDTO;
import GUI.panels.BasePanel;
import GUI.panels.table_panels.DepartmentsPanel;
import GUI.panels.table_panels.StaffMembersPanel;
import enums.Specialization;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditStaffMembersPanel extends EditPanel {

    public static final String EDIT_STAFF_MEMBERS_PANEL = "EDIT_STAFF_MEMBERS_PANEL";

    //First name
    private JLabel firstNameLabel;
    private JTextField firstNameText;
    //Last name
    private JLabel lastNameLabel;
    private JTextField lastNameText;
    //BirthDate
    private JLabel birthDateLabel;
    private JTextField birthDateText;
    //Address
    private JLabel addressLabel;
    private JTextField addressText;
    //Phone number
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberText;
    //Email
    private JLabel emailLabel;
    private JTextField emailText;
    //Gender
    private JLabel genderLabel;
    private JTextField genderText;
    //Salary
    private JLabel salaryLabel;
    private JTextField salaryText;
    //Department
    private JLabel departmentLabel;
    private DefaultListModel<DepartmentOptionDTO> activeDepartmentListModel;
    private JList<DepartmentOptionDTO> activeDepartmentList;
    private JScrollPane activeDepartmentPane;
    private DefaultListModel<DepartmentOptionDTO> allDepartmentListModel;
    private JList<DepartmentOptionDTO> allDepartmentList;
    private JScrollPane allDepartmentPane;
    private JButton selectDepartmentButton;
    //Save button
    private JButton addButton;
    //Back button
    private JButton backButton;


    public EditStaffMembersPanel(BasePanel prev) {
        super(prev);

        buildFirstNameField();
        buildLastNameField();
        buildBirthDateField();
        buildAddressField();
        buildPhoneNumberField();
        buildEmailField();
        buildGenderField();
        buildSalaryField();
        buildDepartmentField();
        buildSaveButton(prev);
        buildBackButton(prev);

        compose();
    }

    private void buildFirstNameField() {
        firstNameLabel = new JLabel("First Name:");
        firstNameText = new JTextField();
    }

    private void buildLastNameField() {
        lastNameLabel = new JLabel("Last Name:");
        lastNameText = new JTextField();
    }

    private void buildBirthDateField() {
        birthDateLabel = new JLabel("Birth Date (YYYY-MM-DD):");
        birthDateText = new JTextField();
    }

    private void buildAddressField() {
        addressLabel = new JLabel("Address:");
        addressText = new JTextField();
    }

    private void buildPhoneNumberField() {
        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberText = new JTextField();
    }

    private void buildEmailField() {
        emailLabel = new JLabel("Email:");
        emailText = new JTextField();
    }

    private void buildGenderField() {
        genderLabel = new JLabel("Gender:");
        genderText = new JTextField();
    }

    private void buildSalaryField() {
        salaryLabel = new JLabel("Salary:");
        salaryText = new JTextField();
    }

    private void buildDepartmentField() {
        departmentLabel = new JLabel("Department:");

        activeDepartmentListModel = new DefaultListModel<>();
        activeDepartmentList = new JList<>(activeDepartmentListModel);
        activeDepartmentPane = new JScrollPane(activeDepartmentList);

        allDepartmentListModel = new DefaultListModel<>();
        allDepartmentList = new JList<>(allDepartmentListModel);
        allDepartmentPane = new JScrollPane(allDepartmentList);

        for (Department department : hospital.getDepartments().values()) {
            allDepartmentListModel.addElement(DepartmentOptionDTO.map(department));
        }

        selectDepartmentButton = new JButton("Select Sublist");
        selectDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

//    private void buildSaveButton(BasePanel prev) {
//        saveDepartmentButton = new JButton("Save");
//        saveDepartmentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int depNumber = hospital.generateNewDepartmentNumber();
//                String name = departmentNameText.getText();
//                Doctor manager = (Doctor) managerContent.getSelectedItem();
//                String location = locationText.getText();
//                Specialization spec = (Specialization) specializationContent.getSelectedItem();
//                HashSet<StaffMember> staffMembers = new HashSet<>();
//                for (int i = 0; i < activeStaffListModel.getSize(); i++) {
//                    staffMembers.add(activeStaffListModel.get(i));
//                }
//
//                Department newDepartment = new Department(depNumber, name, manager, location, spec, staffMembers);
//                if (hospital.addDepartment(newDepartment)) {
//                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
//                    ((DepartmentsPanel) prev).reloadData(hospital.getDepartments());
//                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
//                }
//            }
//        });
//    }
    private void buildSaveButton(BasePanel prev) {
        addButton = new JButton("Save");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String birthDateStr = birthDateText.getText();
                String address = addressText.getText();
                String phoneNumber = phoneNumberText.getText();
                String email = emailText.getText();
                String gender = genderText.getText();
                String salaryStr = salaryText.getText();

                //StaffMember newStaffMember = new StaffMember(id, firstName, lastName);
            }
        });
    }

    private void buildBackButton(BasePanel prev) {
        backButton = new JButton("Back");
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
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.Group departmentGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeDepartmentPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(selectDepartmentButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allDepartmentPane));
        GroupLayout.Group departmentGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeDepartmentPane)
                        .addComponent(selectDepartmentButton)
                        .addComponent(allDepartmentPane));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(firstNameLabel)
                                .addComponent(lastNameLabel)
                                .addComponent(birthDateLabel)
                                .addComponent(addressLabel)
                                .addComponent(phoneNumberLabel)
                                .addComponent(emailLabel)
                                .addComponent(genderLabel)
                                .addComponent(salaryLabel)
                                .addComponent(departmentLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(firstNameText)
                                .addComponent(lastNameText)
                                .addComponent(birthDateText)
                                .addComponent(addressText)
                                .addComponent(phoneNumberText)
                                .addComponent(emailText)
                                .addComponent(genderText)
                                .addComponent(salaryText)
                                .addGroup(departmentGroupHor)
                                .addComponent(addButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(firstNameLabel)
                                .addComponent(firstNameText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lastNameLabel)
                                .addComponent(lastNameText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(birthDateLabel)
                                .addComponent(birthDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addressLabel)
                                .addComponent(addressText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(phoneNumberLabel)
                                .addComponent(phoneNumberText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(genderLabel)
                                .addComponent(genderText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salaryLabel)
                                .addComponent(salaryText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(departmentLabel)
                                .addGroup(departmentGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(addButton))
        );
    }

    public void fillFromObject(StaffMember staffMember) {
        clearPanel();
        firstNameText.setText(staffMember.getFirstName());
        lastNameText.setText(staffMember.getLastName());
        birthDateText.setText(staffMember.getBirthDate().toString());
        addressText.setText(staffMember.getAddress());
        phoneNumberText.setText(staffMember.getPhoneNumber());
        genderText.setText(staffMember.getGender());
        emailText.setText(staffMember.getEmail());
        salaryText.setText(Double.toString(staffMember.getSalary()));
        for (Department department : staffMember.getDepartments()) {
            activeDepartmentListModel.addElement(DepartmentOptionDTO.map(department));
        }
    }

    private void clearPanel() {
        firstNameText.setText("");
        lastNameText.setText("");
        birthDateText.setText("");
        addressText.setText("");
        phoneNumberText.setText("");
        genderText.setText("");
        emailText.setText("");
        activeDepartmentListModel.removeAllElements();
    }
}
