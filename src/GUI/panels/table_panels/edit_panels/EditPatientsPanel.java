package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.*;
import GUI.panels.BasePanel;
import GUI.panels.table_panels.PatientsPanel;
import enums.BiologicalSex;
import enums.Specialization;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditPatientsPanel extends EditPanel {

        public static final String EDIT_PATIENT_PANEL = "EDIT_PATIENT_PANEL";

        private JComboBox<BiologicalSex> createBioSexContent() {
            JComboBox<BiologicalSex> res = new JComboBox<>();
            for(BiologicalSex biologicalSex : BiologicalSex.values()){
                res.addItem(biologicalSex);
            }
            return res;
        }

        public EditPatientsPanel(BasePanel prev) {
            super(prev);

            JLabel patientFirstNameLabel = new JLabel("Patient Name:");
            JTextField patientFirstNameText = new JTextField();

            JLabel patientLastLabel = new JLabel("Last name:");
            JTextField patientLastText = new JTextField();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            JLabel patientBirthDateLabel = new JLabel("Birthdate:");
            JFormattedTextField patientBirthDateText = new JFormattedTextField(dateFormat);

            JLabel addressLabel = new JLabel("Address:");
            JTextField addressText = new JTextField();


            JLabel phoneNumberLabel = new JLabel("PhoneNumber:");
            JTextField phoneNumberText = new JTextField();

            JLabel emailLabel = new JLabel("Email:");
            JTextField emailText = new JTextField();

            JLabel genderLabel = new JLabel("gender:");
            JTextField genderText = new JTextField();

            JLabel healthFundLabel = new JLabel("HealthFund:");
            JTextField healthFundText = new JTextField();

            JLabel biologicalLabel = new JLabel("BiologicalSex:");
            JComboBox<BiologicalSex> biologicalText = createBioSexContent();

            JLabel visitsLabel = new JLabel("Visits:");
            JScrollPane activeVisitsPane = new JScrollPane();
            DefaultListModel<String> activeVisitsListModel = new DefaultListModel<>();
            JList<String> activeVisitsList = new JList<>(activeVisitsListModel);
            activeVisitsPane.add(activeVisitsList);

            DefaultListModel<String> allVisitsListModel = new DefaultListModel<>();
            JList<String> allVisitsList = new JList<>(allVisitsListModel);
            JScrollPane allVisitsPane = new JScrollPane(allVisitsList);

            for (Visit visit : hospital.getVisits().values()) {
                allVisitsListModel.addElement(VisitListOptionDTO.map(visit).toString());
            }

            JButton button = new JButton("Select Sublist");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            });


            JButton savePatientButton = new JButton("Save");
            savePatientButton.addActionListener(new ActionListener() {
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

            GroupLayout.Group visitsGroupHor = layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeVisitsPane))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(button))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allVisitsPane));
            GroupLayout.Group visitsGroupVer = layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(activeVisitsPane)
                            .addComponent(button)
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
                                    .addComponent(healthFundText)
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
                                    .addComponent(healthFundText))
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
    }