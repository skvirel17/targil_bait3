package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.StaffMemberListOptionDTO;
import GUI.panels.BasePanel;
import model.StaffMember;
import model.Department;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

 public class EditStaffMembersPanel extends EditPanel {

     private JComboBox<StaffMember> createStaffMembersContent() {
         JComboBox<StaffMember> staffMemberContent = new JComboBox<>();

         // Получаем все сотрудники из hospital
         for (Map.Entry<Integer, StaffMember> entry : hospital.getStaffMembers().entrySet()) {
             // Добавляем каждого сотрудника в JComboBox с помощью DTO
             staffMemberContent.addItem(StaffMemberListOptionDTO.map(entry.getValue()));
         }

         return staffMemberContent;
     }


    public EditStaffMembersPanel(BasePanel prev) {
        super(prev);

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameText = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameText = new JTextField();

        JLabel birthDateLabel = new JLabel("Birth Date (YYYY-MM-DD):");
        JTextField birthDateText = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressText = new JTextField();

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberText = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailText = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Male", "Female", "Other"});

        JLabel salaryLabel = new JLabel("Salary:");
        JTextField salaryText = new JTextField();

        JLabel departmentLabel = new JLabel("Department:");
        JComboBox<Department> departmentCombo = new JComboBox<>();
        // Populate departmentCombo with available departments if needed

        JButton addButton = new JButton("Save");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameText.getText();
                String lastName = lastNameText.getText();
                String birthDateStr = birthDateText.getText();
                String address = addressText.getText();
                String phoneNumber = phoneNumberText.getText();
                String email = emailText.getText();
                String gender = (String) genderCombo.getSelectedItem();
                String salaryStr = salaryText.getText();
            }
        });

        JButton backButton = new JButton("Back");
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
                                .addComponent(genderCombo)
                                .addComponent(salaryText)
                                .addComponent(departmentCombo)
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
                                .addComponent(genderCombo))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(salaryLabel)
                                .addComponent(salaryText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(departmentLabel)
                                .addComponent(departmentCombo))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(addButton))
        );
    }

    private int generateUniqueId() {
        return hospital.getStaffMembers().size() + 1; // Simple unique ID generator
    }
}
