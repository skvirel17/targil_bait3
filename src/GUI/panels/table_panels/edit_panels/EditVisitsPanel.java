package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.*;
import GUI.panels.BasePanel;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.mainScreen.SystemUsersGUI.getCardLayout;

public class EditVisitsPanel extends EditPanel {

    public static final String EDIT_VISIT_PANEL = "EDIT_VISIT_PANEL";

    //Patient
    private JLabel patientLabel;
    private JComboBox<Patient> patientContent;
    //Start date
    private JLabel startDateLabel;
    private JTextField startDateText;
    //End date
    private JLabel endDateLabel;
    private JTextField endDateText;
    //Treatment list
    private JLabel treatmentsLabel;
    private DefaultListModel<TreatmentListOptionDTO> treatmentsListModel;
    private JList<TreatmentListOptionDTO> treatmentsList;
    private JScrollPane treatmentsPane;
    private DefaultListModel<TreatmentListOptionDTO> allTreatmentsListModel;
    private JList<TreatmentListOptionDTO> allTreatmentsList;
    private JScrollPane allTreatmentsPane;
    private JButton selectTreatmentButton;
    //Medical problem list
    private JLabel medicalProblemLabel;
    private JList<MedicationProblemListOptionDTO> activeMedicalProblemList;
    private DefaultListModel<MedicationProblemListOptionDTO> activeMedicalProblemListModel;
    private JScrollPane activeMedicalProblemPane;
    private DefaultListModel<MedicationProblemListOptionDTO> allMedicalProblemListModel;
    private JList<MedicationProblemListOptionDTO> allMedicalProblemList;
    private JScrollPane allMedicalProblemPane;
    private JButton medicalProblemButton;
    //Save button
    private JButton saveVisitButton;
    //Back button
    private JButton backButton;


    public  EditVisitsPanel(BasePanel prev) {
        super(prev);

        buildPatientField();
        buildStartDateField();
        buildEndDateField();
        buildMedicalProblemListField();
        buildTreatmentListField();
        buildSaveButton(prev);
        buildBackButton(prev);

        compose();
    }

    private JComboBox<Patient> createPatientContent() {
        JComboBox<Patient> patientContent = new JComboBox<>();
        for (Patient patient : hospital.getPatients().values()) {
            patientContent.addItem(PatientListOptionDTO.map(patient));
        }
        return patientContent;
    }

    private void buildPatientField() {
        patientLabel = new JLabel("Patient:");
        patientContent = createPatientContent();
    }

    private void buildStartDateField() {
        startDateLabel = new JLabel("Start Date:");
        startDateText = new JTextField();
    }

    private void buildEndDateField() {
        endDateLabel = new JLabel("End Date:");
        endDateText = new JTextField();
    }

    private void buildTreatmentListField() {
        treatmentsLabel = new JLabel("Treatments:");
        treatmentsListModel = new DefaultListModel<>();
        treatmentsList = new JList<>(treatmentsListModel);
        treatmentsPane = new JScrollPane(treatmentsList);

        allTreatmentsListModel = new DefaultListModel<>();
        allTreatmentsList = new JList<>(allTreatmentsListModel);
        allTreatmentsPane = new JScrollPane(allTreatmentsList);

        for (Treatment treatment : hospital.getTreatments().values()) {
            allTreatmentsListModel.addElement(TreatmentListOptionDTO.map(treatment));
        }

        selectTreatmentButton = new JButton("Add Treatment");
        selectTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add selected treatment to the list
                TreatmentListOptionDTO selectedTreatment = allTreatmentsList.getSelectedValue();
                if (selectedTreatment != null) {
                    treatmentsListModel.addElement(selectedTreatment);
                }
            }
        });
    }

    private void buildMedicalProblemListField() {
        medicalProblemLabel = new JLabel("Medical problems:");
        activeMedicalProblemListModel = new DefaultListModel<>();
        activeMedicalProblemList = new JList<>(activeMedicalProblemListModel);
        activeMedicalProblemPane = new JScrollPane(activeMedicalProblemList);

        allMedicalProblemListModel = new DefaultListModel<>();
        allMedicalProblemList = new JList<>(allMedicalProblemListModel);
        allMedicalProblemPane = new JScrollPane(allMedicalProblemList);

        for (MedicalProblem medicalProblem : hospital.getMedicalProblems().values()) {
            allMedicalProblemListModel.addElement(MedicationProblemListOptionDTO.map(medicalProblem));
        }
        medicalProblemButton = new JButton("Select Sublist");
        medicalProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void buildSaveButton(BasePanel prev) {
        saveVisitButton = new JButton("Save");
        saveVisitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save visit logic
                JOptionPane.showMessageDialog(null, "Visit saved successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
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

        GroupLayout.Group treatmentsGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(treatmentsPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(selectTreatmentButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allTreatmentsPane));
        GroupLayout.Group treatmentsGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(treatmentsPane)
                        .addComponent(selectTreatmentButton)
                        .addComponent(allTreatmentsPane));

        GroupLayout.Group medicalProblemsGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeMedicalProblemPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(medicalProblemButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allMedicalProblemPane));
        GroupLayout.Group medicalProblemsGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeMedicalProblemPane)
                        .addComponent(medicalProblemButton)
                        .addComponent(allMedicalProblemPane));

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(patientLabel)
                                .addComponent(startDateLabel)
                                .addComponent(endDateLabel)
                                .addComponent(treatmentsLabel)
                                .addComponent(medicalProblemLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(patientContent)
                                .addComponent(startDateText)
                                .addComponent(endDateText)
                                .addGroup(treatmentsGroupHor)
                                .addGroup(medicalProblemsGroupHor)
                                .addComponent(saveVisitButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
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
                                .addComponent(medicalProblemLabel)
                                .addGroup(medicalProblemsGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveVisitButton))
        );
    }

    public void fillFromObject(Visit visit) {
        clearPanel();
        for (int i = 0; i < patientContent.getItemCount(); i++) {
            if (patientContent.getItemAt(i).getId() == visit.getPatient().getId()) {
                patientContent.setSelectedIndex(i);
            }
        }
        startDateText.setText(visit.getStartDate().toString());
        endDateText.setText(visit.getEndDate().toString());
        for (Treatment treatment : visit.getTreatmentsList()) {
            treatmentsListModel.addElement(TreatmentListOptionDTO.map(treatment));
        }
        for (MedicalProblem problem : visit.getMedicalProblemsList()) {
            activeMedicalProblemListModel.addElement(MedicationProblemListOptionDTO.map(problem));
        }
    }

    private void clearPanel() {
        startDateText.setText("");
        endDateText.setText("");
        treatmentsListModel.removeAllElements();
        activeMedicalProblemListModel.removeAllElements();
    }
}
