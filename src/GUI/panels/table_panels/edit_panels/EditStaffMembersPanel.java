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
import utils.UtilsMethods;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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
    private JFormattedTextField birthDateText;
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
    //Work start date
    private JLabel workStartDateLabel;
    private JFormattedTextField workStartDateText;
    //Salary
    private JLabel salaryLabel;
    private JTextField salaryText;
    //Department
    private JLabel departmentLabel;
    private JLabel positionLabel;
    private JComboBox<String> positionContent;
    private DefaultListModel<DepartmentOptionDTO> activeDepartmentListModel;
    private JList<DepartmentOptionDTO> activeDepartmentList;
    private JScrollPane activeDepartmentPane;
    private DefaultListModel<DepartmentOptionDTO> allDepartmentListModel;
    private JList<DepartmentOptionDTO> allDepartmentList;
    private JScrollPane allDepartmentPane;
    private JButton selectDepartmentButton;
    //License number
    private JLabel licenseNumberLabel;
    private JFormattedTextField licenseNumberText;
    //Finish Internship
    private JLabel isInternshipFinishedLabel;
    private JCheckBox isInternshipFinishedContent;
    //Save button
    private JButton addButton;
    //Back button
    private JButton backButton;


    public EditStaffMembersPanel(BasePanel prev) {
        super(prev);

        buildPositionField();
        buildFirstNameField();
        buildLastNameField();
        buildBirthDateField();
        buildAddressField();
        buildPhoneNumberField();
        buildEmailField();
        buildGenderField();
        buildWorkStartDateField();
        buildSalaryField();
        buildDepartmentField();
        buildLicenseNumberField();
        buildInternShipField();
        buildSaveButton(prev, this);
        buildBackButton(prev, this);
        clearPanel();

        compose(positionContent.getItemAt(positionContent.getSelectedIndex()));
    }

    public void disablePositionField() {
        positionContent.setEnabled(false);
    }

    public void enablePositionField() {
        positionContent.setEnabled(true);
    }

    private void buildInternShipField() {
        isInternshipFinishedLabel = new JLabel("Internship finished: ");
        isInternshipFinishedContent = new JCheckBox();
    }

    private void buildFirstNameField() {
        firstNameLabel = new JLabel("First Name:");
        firstNameText = new JTextField();
    }

    private void buildLicenseNumberField() {
        licenseNumberLabel = new JLabel("License number: ");
        try {
            licenseNumberText = new JFormattedTextField(new MaskFormatter("##########"));
        } catch (ParseException e) {
            //TODO: remove runtime exception
            throw new RuntimeException(e);
        }
    }
    private void buildLastNameField() {
        lastNameLabel = new JLabel("Last Name:");
        lastNameText = new JTextField();
    }

    private void buildBirthDateField() {
        birthDateLabel = new JLabel("Birth Date (dd/mm/yyyy):");
        try {
            birthDateText = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            //TODO: remove runtime exception
            throw new RuntimeException(e);
        }
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

    private void buildWorkStartDateField() {
        workStartDateLabel = new JLabel("Work start date(dd/mm/yyyy):");
        try {
            workStartDateText = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            //TODO: remove runtime exception
            throw new RuntimeException(e);
        }
    }

    private void buildSalaryField() {
        salaryLabel = new JLabel("Salary:");
        salaryText = new JTextField();
    }

    private void buildPositionField(){
        positionLabel = new JLabel("Type:");
        positionContent = createPositionContent();
        positionContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compose(positionContent.getItemAt(positionContent.getSelectedIndex()));
            }
        });
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

    private void buildSaveButton(BasePanel prev, EditStaffMembersPanel panel) {
        addButton = new JButton("Save");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = hospital.generateNewStaffMember();
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String birthDateStr = birthDateText.getText();
                String address = addressText.getText();
                String phoneNumber = phoneNumberText.getText();
                String email = emailText.getText();
                String gender = genderText.getText();
                String salaryStr = salaryText.getText();
                String position = positionContent.getItemAt(positionContent.getSelectedIndex());
//                if(position.equals("doctor")){
//                    StaffMember staffMember = new Doctor(id, firstName, lastName, birthDateStr, address, phoneNumber, email, gender, salaryStr)
//                }
//
//                StaffMember staffMember = new StaffMember(id, firstName, lastName, birthDateStr, address, phoneNumber, email, gender, salaryStr);
//                if (hospital.addDepartment(newDepartment)) {
//                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
//                    ((DepartmentsPanel) prev).reloadData(hospital.getDepartments());
//                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
//                }
                //StaffMember newStaffMember = new StaffMember(id, firstName, lastName);
            }
        });
    }

    private void buildBackButton(BasePanel prev, EditStaffMembersPanel panel) {
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Changes will be lost! \nDo you want to continue?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
                    panel.enablePositionField();
                }
            }
        });
    }

    private void compose(String position) {
        if (position.equals("doctor")) {
            composeDoctor();
        } else {
            composeNurse();
        }
    }

    private void composeDoctor() {
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
                                .addComponent(positionLabel)
                                .addComponent(firstNameLabel)
                                .addComponent(lastNameLabel)
                                .addComponent(birthDateLabel)
                                .addComponent(addressLabel)
                                .addComponent(phoneNumberLabel)
                                .addComponent(emailLabel)
                                .addComponent(genderLabel)
                                .addComponent(workStartDateLabel)
                                .addComponent(salaryLabel)
                                .addComponent(departmentLabel)
                                .addComponent(licenseNumberLabel)
                                .addComponent(isInternshipFinishedLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(positionContent)
                                .addComponent(firstNameText)
                                .addComponent(lastNameText)
                                .addComponent(birthDateText)
                                .addComponent(addressText)
                                .addComponent(phoneNumberText)
                                .addComponent(emailText)
                                .addComponent(genderText)
                                .addComponent(workStartDateText)
                                .addComponent(salaryText)
                                .addGroup(departmentGroupHor)
                                .addComponent(licenseNumberText)
                                .addComponent(isInternshipFinishedContent)
                                .addComponent(addButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(positionLabel)
                                .addComponent(positionContent))
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
                                .addComponent(workStartDateLabel)
                                .addComponent(workStartDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salaryLabel)
                                .addComponent(salaryText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(departmentLabel)
                                .addGroup(departmentGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(licenseNumberLabel)
                                .addComponent(licenseNumberText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(isInternshipFinishedLabel)
                                .addComponent(isInternshipFinishedContent))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(addButton))
        );
    }

    private void composeNurse() {
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
                                .addComponent(positionLabel)
                                .addComponent(firstNameLabel)
                                .addComponent(lastNameLabel)
                                .addComponent(birthDateLabel)
                                .addComponent(addressLabel)
                                .addComponent(phoneNumberLabel)
                                .addComponent(emailLabel)
                                .addComponent(genderLabel)
                                .addComponent(workStartDateLabel)
                                .addComponent(salaryLabel)
                                .addComponent(departmentLabel)
                                .addComponent(licenseNumberLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(positionContent)
                                .addComponent(firstNameText)
                                .addComponent(lastNameText)
                                .addComponent(birthDateText)
                                .addComponent(addressText)
                                .addComponent(phoneNumberText)
                                .addComponent(emailText)
                                .addComponent(genderText)
                                .addComponent(workStartDateText)
                                .addComponent(salaryText)
                                .addGroup(departmentGroupHor)
                                .addComponent(licenseNumberText)
                                .addComponent(addButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(positionLabel)
                                .addComponent(positionContent))
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
                                .addComponent(workStartDateLabel)
                                .addComponent(workStartDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salaryLabel)
                                .addComponent(salaryText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(departmentLabel)
                                .addGroup(departmentGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(licenseNumberLabel)
                                .addComponent(licenseNumberText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(addButton))
        );
    }

    private JComboBox<String> createPositionContent() {
        return new JComboBox<>(new String[]{"doctor", "nurse"});
    }

    public void fillFromObject(StaffMember staffMember) {
        clearPanel();
        firstNameText.setText(staffMember.getFirstName());
        lastNameText.setText(staffMember.getLastName());
        birthDateText.setText(UtilsMethods.format(staffMember.getBirthDate()));
        addressText.setText(staffMember.getAddress());
        phoneNumberText.setText(staffMember.getPhoneNumber());
        genderText.setText(staffMember.getGender());
        emailText.setText(staffMember.getEmail());
        salaryText.setText(Double.toString(staffMember.getSalary()));
        workStartDateText.setText(UtilsMethods.format(staffMember.getWorkStartDate()));
        for (Department department : staffMember.getDepartments()) {
            activeDepartmentListModel.addElement(DepartmentOptionDTO.map(department));
        }
        if (staffMember instanceof Doctor) {
            licenseNumberText.setText(String.valueOf(((Doctor) staffMember).getLicenseNumber()));
            isInternshipFinishedContent.setSelected(((Doctor) staffMember).isFinishInternship());
        } else {
            licenseNumberText.setText(String.valueOf(((Nurse) staffMember).getLicenseNumber()));
        }
    }

    private void clearPanel() {
        firstNameText.setText("");
        lastNameText.setText("");
        birthDateText.setText("01/01/1900");
        addressText.setText("");
        phoneNumberText.setText("");
        genderText.setText("");
        emailText.setText("");
        salaryText.setText("");
        workStartDateText.setText("01/01/1900");
        activeDepartmentListModel.removeAllElements();
        licenseNumberText.setText("");
        isInternshipFinishedContent.setSelected(false);
    }


}
