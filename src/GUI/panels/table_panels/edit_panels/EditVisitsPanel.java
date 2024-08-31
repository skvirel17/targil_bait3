package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DoctorListOptionDTO;
import GUI.dto.PatientListOptionDTO;
import GUI.dto.TreatmentListOptionDTO;
import GUI.panels.BasePanel;
import model.Doctor;
import model.Patient;
import model.Treatment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.mainScreen.SystemUsersGUI.getCardLayout;

public class EditVisitsPanel extends EditPanel {


    private JComboBox<Patient> createPatientContent() {
        JComboBox<Patient> patientContent = new JComboBox<>();
        for (Patient patient : hospital.getPatients().values()) {
            patientContent.addItem(PatientListOptionDTO.map(patient));
        }
        return patientContent;
    }



    public  EditVisitsPanel(BasePanel prev) {
        super(prev);

        JLabel numberLabel = new JLabel("Visit Number:");
        JTextField numberText = new JTextField();

        JLabel patientLabel = new JLabel("Patient:");
        JComboBox<Patient> patientContent = createPatientContent();

        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateText = new JTextField();

        JLabel endDateLabel = new JLabel("End Date:");
        JTextField endDateText = new JTextField();

        JLabel treatmentsLabel = new JLabel("Treatments:");
        JScrollPane treatmentsPane = new JScrollPane();
        DefaultListModel<String> treatmentsListModel = new DefaultListModel<>();
        JList<String> treatmentsList = new JList<>(treatmentsListModel);
        treatmentsPane.setViewportView(treatmentsList);

        DefaultListModel<String> allTreatmentsListModel = new DefaultListModel<>();
        JList<String> allTreatmentsList = new JList<>(allTreatmentsListModel);
        JScrollPane allTreatmentsPane = new JScrollPane(allTreatmentsList);

        for (Treatment treatment : hospital.getTreatments().values()) {
            allTreatmentsListModel.addElement(TreatmentListOptionDTO.map(treatment).toString());
        }

        JButton addButton = new JButton("Add Treatment");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add selected treatment to the list
                String selectedTreatment = allTreatmentsList.getSelectedValue();
                if (selectedTreatment != null) {
                    treatmentsListModel.addElement(selectedTreatment);
                }
            }
        });

        JButton saveVisitButton = new JButton("Save");
        saveVisitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save visit logic
                JOptionPane.showMessageDialog(null, "Visit saved successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
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

        GroupLayout.Group treatmentsGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(treatmentsPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(addButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allTreatmentsPane));
        GroupLayout.Group treatmentsGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(treatmentsPane)
                        .addComponent(addButton)
                        .addComponent(allTreatmentsPane));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(numberLabel)
                                .addComponent(patientLabel)
                                .addComponent(startDateLabel)
                                .addComponent(endDateLabel)
                                .addComponent(treatmentsLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(numberText)
                                .addComponent(patientContent)
                                .addComponent(startDateText)
                                .addComponent(endDateText)
                                .addGroup(treatmentsGroupHor)
                                .addComponent(saveVisitButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(numberLabel)
                                .addComponent(numberText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(patientLabel)
                                .addComponent(patientContent))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(startDateLabel)
                                .addComponent(startDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(endDateLabel)
                                .addComponent(endDateText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(treatmentsLabel)
                                .addGroup(treatmentsGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveVisitButton))
        );
    }
}
