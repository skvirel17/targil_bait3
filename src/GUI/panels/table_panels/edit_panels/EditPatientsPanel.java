package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.*;
import GUI.panels.BasePanel;
import GUI.panels.table_panels.DepartmentsPanel;
import GUI.panels.table_panels.PatientsPanel;
import enums.BiologicalSex;
import enums.HealthFund;
import enums.Specialization;
import model.*;
import utils.UtilsMethods;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditPatientsPanel extends EditPanel {

    public static final String EDIT_PATIENT_PANEL = "EDIT_PATIENT_PANEL";

    //First name
    private JLabel patientFirstNameLabel;
    private JTextField patientFirstNameText;
    //Last name
    private  JLabel patientLastLabel;
    private JTextField patientLastText;
    //BirthDate
    private JLabel patientBirthDateLabel;
    private JFormattedTextField patientBirthDateText;
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
    //Health fund
    private JLabel healthFundLabel;
    private JComboBox<HealthFund> healthFundContent;
    //Biological sex
    private JLabel biologicalLabel;
    private JComboBox<BiologicalSex> biologicalText;
    //Visits
    private JLabel visitsLabel;
    private DefaultListModel<VisitListOptionDTO> activeVisitsListModel;
    private JList<VisitListOptionDTO> activeVisitsList;
    private JScrollPane activeVisitsPane;
    private DefaultListModel<VisitListOptionDTO> allVisitsListModel;
    private JList<VisitListOptionDTO> allVisitsList;
    private JScrollPane allVisitsPane;
    private JButton selectVisitButton;
    //Save button
    private JButton savePatientButton;
    //Back button
    private JButton backButton;


    public EditPatientsPanel(BasePanel prev) {
        super(prev);

        buildFirstNameField();
        buildLastNameField();
        buildBirthDateField();
        buildAddressField();
        buildPhoneNumberField();
        buildGenderField();
        buildEmailField();
        buildHealthFundField();
        buildBiologicalSexField();
        buildVisitsField();
        buildSaveButton(prev, this);
        buildBackButton(prev, this);

        compose();
    }

    private JComboBox<BiologicalSex> createBioSexContent() {
        JComboBox<BiologicalSex> res = new JComboBox<>();
        for(BiologicalSex biologicalSex : BiologicalSex.values()){
            res.addItem(biologicalSex);
        }
        return res;
    }

    private JComboBox<HealthFund> createHealthFundContent() {
        JComboBox<HealthFund> res = new JComboBox<>();
        for(HealthFund healthFund : HealthFund.values()){
            res.addItem(healthFund);
        }
        return res;
    }

    private void buildFirstNameField() {
        patientFirstNameLabel = new JLabel("Patient Name:");
        patientFirstNameText = new JTextField();
    }

    private void buildLastNameField() {
        patientLastLabel = new JLabel("Last name:");
        patientLastText = new JTextField();
    }

    private void buildBirthDateField() {
        patientBirthDateLabel = new JLabel("Birthdate:");
        try {
            patientBirthDateText = new JFormattedTextField(new MaskFormatter("##/##/####"));
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
        phoneNumberLabel = new JLabel("PhoneNumber:");
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

    private void buildHealthFundField() {
        healthFundLabel = new JLabel("HealthFund:");
        healthFundContent = createHealthFundContent();
    }

    private void buildBiologicalSexField() {
        biologicalLabel = new JLabel("BiologicalSex:");
        biologicalText = createBioSexContent();
    }

    private void buildVisitsField() {
        visitsLabel = new JLabel("Visits:");

        activeVisitsListModel = new DefaultListModel<>();
        activeVisitsList = new JList<>(activeVisitsListModel);
        activeVisitsPane = new JScrollPane(activeVisitsList);

        allVisitsListModel = new DefaultListModel<>();
        allVisitsList = new JList<>(allVisitsListModel);
        allVisitsPane = new JScrollPane(allVisitsList);

        for (Visit visit : hospital.getVisits().values()) {
            allVisitsListModel.addElement(VisitListOptionDTO.map(visit));
        }

        selectVisitButton = new JButton("Select Sublist");
        selectVisitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void buildSaveButton(BasePanel prev, EditPatientsPanel panel) {
        savePatientButton = new JButton("Save");
        savePatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = hospital.generateNewPatientNumber();
                String name = patientFirstNameText.getText();
                String lastName = patientLastText.getText();
                Date birthdate = UtilsMethods.parseDate(patientBirthDateText.getText());
                String inputDate = patientBirthDateText.getText();
                if (!inputDate.equals(UtilsMethods.format(birthdate))) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Invalid date format. Please enter a valid date.");
                    return;
                }
                String address = addressText.getText();
                String phoneNumber = phoneNumberText.getText();
                String email = emailText.getText();
                String gender = genderText.getText();
                HealthFund healthFund = (HealthFund) healthFundContent.getSelectedItem();
                BiologicalSex biologicalSex = (BiologicalSex) biologicalText.getSelectedItem();
                HashSet<Visit> visit = new HashSet<>();
                for (int i = 0; i < activeVisitsListModel.getSize(); i++) {
                    visit.add(activeVisitsListModel.get(i));
                }

                Patient newPatient = new Patient(id, name, lastName, birthdate, address, phoneNumber, email, gender, visit, healthFund, biologicalSex);
                if (hospital.addPatient(newPatient)) {
                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                    ((PatientsPanel) prev).reloadData(hospital.getPatients());
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void buildBackButton(BasePanel prev, EditPatientsPanel panel) {
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

        GroupLayout.Group visitsGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeVisitsPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(selectVisitButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allVisitsPane));
        GroupLayout.Group visitsGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeVisitsPane)
                        .addComponent(selectVisitButton)
                        .addComponent(allVisitsPane));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(patientFirstNameLabel)
                                .addComponent(patientLastLabel)
                                .addComponent(patientBirthDateLabel)
                                .addComponent(addressLabel)
                                .addComponent(phoneNumberLabel)
                                .addComponent(genderLabel)
                                .addComponent(healthFundLabel)
                                .addComponent(emailLabel)
                                .addComponent(biologicalLabel)
                                .addComponent(visitsLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(patientFirstNameText)
                                .addComponent(patientLastText)
                                .addComponent(patientBirthDateText)
                                .addComponent(addressText)
                                .addComponent(phoneNumberText)
                                .addComponent(genderText)
                                .addComponent(healthFundContent)
                                .addComponent(emailText)
                                .addComponent(biologicalText)
                                .addGroup(visitsGroupHor)
                                .addComponent(savePatientButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(patientFirstNameLabel)
                                .addComponent(patientFirstNameText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(patientLastLabel)
                                .addComponent(patientLastText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(patientBirthDateLabel)
                                .addComponent(patientBirthDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addressLabel)
                                .addComponent(addressText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(phoneNumberLabel)
                                .addComponent(phoneNumberText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(genderLabel)
                                .addComponent(genderText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(healthFundLabel)
                                .addComponent(healthFundContent))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(emailLabel)
                                .addComponent(emailText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(biologicalLabel)
                                .addComponent(biologicalText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(visitsLabel)
                                .addGroup(visitsGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(savePatientButton))

        );
    }

    public void fillFromObject(Patient patient) {
        clearPanel();
        patientFirstNameText.setText(patient.getFirstName());
        patientLastText.setText(patient.getLastName());
        patientBirthDateText.setText(UtilsMethods.format(patient.getBirthDate()));
        addressText.setText(patient.getAddress());
        phoneNumberText.setText(patient.getPhoneNumber());
        genderText.setText(patient.getGender());
        for (int i = 0; i < healthFundContent.getItemCount(); i++) {
            if (healthFundContent.getItemAt(i).equals(patient.getHealthFund())) {
                healthFundContent.setSelectedIndex(i);
            }
        }
        emailText.setText(patient.getEmail());
        for (int i = 0; i < biologicalText.getItemCount(); i++) {
            if (biologicalText.getItemAt(i).equals(patient.getBiologicalSex())) {
                biologicalText.setSelectedIndex(i);
            }
        }
        for (Visit visit : patient.getVisitsList()) {
            activeVisitsListModel.addElement(VisitListOptionDTO.map(visit));
        }
    }

    void clearPanel() {
        patientFirstNameText.setText("");
        patientLastText.setText("");
        patientBirthDateText.setText("");
        addressText.setText("");
        phoneNumberText.setText("");
        genderText.setText("");
        emailText.setText("");
        activeVisitsListModel.removeAllElements();
    }
}