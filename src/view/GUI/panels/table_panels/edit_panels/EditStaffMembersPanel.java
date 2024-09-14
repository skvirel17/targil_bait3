package view.GUI.panels.table_panels.edit_panels;

import view.GUI.actions.OpenPanelAction;
import view.GUI.dto.DepartmentOptionDTO;
import view.GUI.panels.BasePanel;
import view.GUI.panels.table_panels.StaffMembersPanel;
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
import java.util.List;

import static view.GUI.mainScreen.SystemUsersGUI.*;

public class EditStaffMembersPanel extends EditPanel {

    public static final String EDIT_STAFF_MEMBERS_PANEL = "EDIT_STAFF_MEMBERS_PANEL";

    //StaffMember
    private StaffMember staffMember;
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
    private JButton clearDepartmentButton;
    //License number
    private JLabel licenseNumberLabel;
    private JFormattedTextField licenseNumberText;
    //Finish Internship
    private JLabel isInternshipFinishedLabel;
    private JCheckBox isInternshipFinishedContent;
    //Specialization
    private JLabel specializationLabel;
    private JComboBox<Specialization> specializationContent;
    //Save button
    private JButton addButton;

    //Back button
    private JButton backButton;


    public EditStaffMembersPanel(BasePanel prev) {
        super(prev);

        staffMember = null;

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
        buildSpecializationField();
        buildSaveButton(prev, this);
        buildBackButton(prev, this);

        compose(positionContent.getItemAt(positionContent.getSelectedIndex()));
    }

    public void disablePositionField() {
        positionContent.setEnabled(false);
    }

    public void enablePositionField() {
        positionContent.setEnabled(true);
    }

    private void buildSpecializationField() {
        specializationLabel = new JLabel("Specialization: ");
        specializationContent = createSpecializationContent();
    }

    private JComboBox<Specialization> createSpecializationContent() {
        specializationContent = new JComboBox<>();
        for (Specialization spec : Specialization.values()){
            specializationContent.addItem(spec);
        }
        return specializationContent;
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

        selectDepartmentButton = new JButton("    Add    ");
        selectDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<DepartmentOptionDTO> selected = allDepartmentList.getSelectedValuesList();
                for (DepartmentOptionDTO item : selected) {
                    if (activeDepartmentListModel.indexOf(item) == -1) {
                        activeDepartmentListModel.addElement(item);
                    };
                }
            }
        });

        clearDepartmentButton = new JButton("Clear all");
        clearDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeDepartmentListModel.removeAllElements();
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
                Date birthdate = UtilsMethods.parseDate(birthDateText.getText());
                String inputDate = birthDateText.getText();
                if (!inputDate.equals(UtilsMethods.format(birthdate))) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Invalid date format (Birth date). Please enter a valid date.");
                    return;
                }
                String address = addressText.getText();
                String phoneNumber = phoneNumberText.getText();
                String email = emailText.getText();
                String gender = genderText.getText();
                String salaryStr = salaryText.getText();
                double salary = 0;
                try {
                    salary = Double.parseDouble(salaryStr);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Invalid number format (Salary). Please enter a valid double.");
                    return;
                }
                String position = positionContent.getItemAt(positionContent.getSelectedIndex());
                Date workStart = UtilsMethods.parseDate(workStartDateText.getText());
                String inputWorkStart = workStartDateText.getText();
                HashSet<Department> departments = new HashSet<>();
                for (int i = 0; i < activeDepartmentListModel.getSize(); i++) {
                    departments.add(activeDepartmentListModel.get(i));
                }
                if (!inputWorkStart.equals(UtilsMethods.format(workStart))) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Invalid date format (Work start date). Please enter a valid date.");
                    return;
                }
                int licenseNumber = Integer.parseInt(licenseNumberText.getText().trim());
                if (!licenseNumberText.getText().trim().equals(String.valueOf(licenseNumber))) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Invalid number format for license. Please enter a valid integer.");
                    return;
                }
                StaffMember newStaffMember;
                boolean isInternship = false;
                Specialization spec = null;
                if (position.equals("doctor")) {
                    isInternship = isInternshipFinishedContent.isSelected();
                    spec = (Specialization) specializationContent.getSelectedItem();
                    newStaffMember = new Doctor(hospital.generateNewStaffMember(), firstName, lastName, birthdate,
                            address, phoneNumber, email, gender, workStart, departments, salary, licenseNumber, isInternship, spec);
                } else {
                    newStaffMember = new Nurse(hospital.generateNewStaffMember(), firstName, lastName, birthdate,
                            address, phoneNumber, email, gender, workStart, departments, salary, licenseNumber);
                }

                if (staffMember != null) {
                    staffMember.setFirstName(firstName);
                    staffMember.setLastName(lastName);
                    staffMember.setBirthDate(birthdate);
                    staffMember.setAddress(address);
                    staffMember.setPhoneNumber(phoneNumber);
                    staffMember.setEmail(email);
                    staffMember.setGender(gender);
                    staffMember.setWorkStartDate(workStart);
                    staffMember.setDepartments(departments);
                    staffMember.setSalary(salary);
                    if (position.equals("doctor")) {
                        ((Doctor) staffMember).setLicenseNumber(licenseNumber);
                        ((Doctor) staffMember).setFinishInternship(isInternship);
                        ((Doctor) staffMember).setSpecialization(spec);
                    } else {
                        ((Nurse) staffMember).setLicenseNumber(licenseNumber);
                    }
                }

                if (staffMember != null || hospital.addStaffMember(newStaffMember)) {
                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                    ((StaffMembersPanel) prev).reloadData(hospital.getStaffMembers());
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
                }
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
            this.remove(isInternshipFinishedContent);
            this.remove(isInternshipFinishedLabel);
            this.remove(specializationLabel);
            this.remove(specializationContent);

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
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(selectDepartmentButton)
                        .addComponent(clearDepartmentButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allDepartmentPane));
        GroupLayout.Group departmentGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeDepartmentPane)
                        .addGroup(layout.createSequentialGroup() // создаем отдельную группу для кнопок
                                .addComponent(selectDepartmentButton)
                                .addComponent(clearDepartmentButton))
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
                                .addComponent(specializationLabel)
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
                                .addComponent(specializationContent)
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
                                .addComponent(specializationLabel)
                                .addComponent(specializationContent))
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
        this.staffMember = staffMember;
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
            for (int i = 0; i < specializationContent.getItemCount(); i++) {
                if (specializationContent.getItemAt(i).equals(((Doctor)staffMember).getSpecialization())) {
                    specializationContent.setSelectedIndex(i);
                }
            }
        } else {
            licenseNumberText.setText(String.valueOf(((Nurse) staffMember).getLicenseNumber()));
        }
    }

    void clearPanel() {
        staffMember = null;
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
        positionContent.setSelectedIndex(0);
        enablePositionField();
    }


}
