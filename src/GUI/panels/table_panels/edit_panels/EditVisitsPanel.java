package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.*;
import GUI.panels.BasePanel;
import GUI.panels.table_panels.TreatmentsPanel;
import GUI.panels.table_panels.VisitsPanel;
import model.*;
import utils.UtilsMethods;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.mainScreen.SystemUsersGUI.getCardLayout;

public class EditVisitsPanel extends EditPanel {

    public static final String EDIT_VISIT_PANEL = "EDIT_VISIT_PANEL";

    //Visit
    private Visit visit;
    //Patient
    private JLabel patientLabel;
    private JComboBox<Patient> patientContent;
    //Start date
    private JLabel startDateLabel;
    private JFormattedTextField startDateText;
    //End date
    private JLabel endDateLabel;
    private JFormattedTextField endDateText;
    //Treatment list
    private JLabel treatmentsLabel;
    private DefaultListModel<TreatmentListOptionDTO> treatmentsListModel;
    private JList<TreatmentListOptionDTO> treatmentsList;
    private JScrollPane treatmentsPane;
    private DefaultListModel<TreatmentListOptionDTO> allTreatmentsListModel;
    private DefaultListModel<MedicationListOptionDTO> activeMedicationListModel;
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
    private JButton clearMedicationButton;
    private JButton clearMedicalProblemButton;
    //Save button
    private JButton saveVisitButton;
    //Back button
    private JButton backButton;


    public  EditVisitsPanel(BasePanel prev) {
        super(prev);

        visit = null;

        buildPatientField();
        buildStartDateField();
        buildEndDateField();
        buildMedicalProblemListField();
        buildTreatmentListField();
        buildSaveButton(prev, this);
        buildBackButton(prev, this);

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
        startDateLabel = new JLabel("First visit:");
        try {
            startDateText = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            //TODO: remove runtime exception
            throw new RuntimeException(e);
        }
    }

    private void buildEndDateField() {
        endDateLabel = new JLabel("Last visit:");
        try {
            endDateText = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            //TODO: remove runtime exception
            throw new RuntimeException(e);
        }
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

        selectTreatmentButton = new JButton("    Add    ");
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

        clearMedicationButton = new JButton("Clear all");
        clearMedicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                allTreatmentsListModel.removeAllElements();
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
        medicalProblemButton = new JButton("    Add    ");
        medicalProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<MedicationProblemListOptionDTO> selected = allMedicalProblemList.getSelectedValuesList();
                for (MedicationProblemListOptionDTO item : selected) {
                    if (activeMedicalProblemListModel.indexOf(item) == -1) {
                        activeMedicalProblemListModel.addElement(item);
                    };
                }
            }
        });

        clearMedicalProblemButton = new JButton("Clear all");
        clearMedicalProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeMedicalProblemListModel.removeAllElements();
            }
        });
    }

    private void buildSaveButton(BasePanel prev, EditVisitsPanel panel) {
        saveVisitButton = new JButton("Save");
        saveVisitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = hospital.generateNewVisitNumber();
                Patient patient = patientContent.getItemAt(patientContent.getSelectedIndex());

                Date startDate = UtilsMethods.parseDate(startDateText.getText());
                String inputDate = startDateText.getText();
                if (!inputDate.equals(UtilsMethods.format(startDate))) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Invalid date format (First visit!).\nPlease enter a valid date.");
                    return;
                }
                Date endDate = UtilsMethods.parseDate(endDateText.getText());
                inputDate = endDateText.getText();
                if (!inputDate.equals(UtilsMethods.format(endDate))) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Invalid date format (Last visit!).\nPlease enter a valid date.");
                    return;
                }

                if (startDate.after(endDate)) {
                    JOptionPane.showMessageDialog(getMainFrame(), "Last visit is before first one.\nPlease enter a valid date.");
                    return;
                }

                HashSet<MedicalProblem> problems = new HashSet<>();
                HashSet<Treatment> treatments = new HashSet<>();
                for (int i = 0; i < activeMedicalProblemListModel.getSize(); i++) {
                    problems.add(activeMedicalProblemListModel.getElementAt(i));
                }
                for (int i = 0; i < treatmentsListModel.getSize(); i++) {
                    treatments.add(treatmentsListModel.getElementAt(i));
                }

                Visit newVisit = new Visit(id, patient, startDate, endDate, problems, treatments);

                if (visit != null) {
                    visit.setPatient(patient);
                    visit.setStartDate(startDate);
                    visit.setEndDate(endDate);
                    visit.setMedicalProblemsList(problems);
                    visit.setTreatmentsList(treatments);
                }

                if (visit != null || hospital.addVisit(newVisit)) {
                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                    ((VisitsPanel) prev).reloadData(hospital.getVisits());
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void buildBackButton(BasePanel prev, EditVisitsPanel panel) {
        backButton = new JButton("Back");
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

        GroupLayout.Group treatmentsGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(treatmentsPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(selectTreatmentButton)
                        .addComponent(clearMedicationButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allTreatmentsPane));
        GroupLayout.Group treatmentsGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(treatmentsPane)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(selectTreatmentButton)
                            .addComponent(clearMedicationButton))
                        .addComponent(allTreatmentsPane));

        GroupLayout.Group medicalProblemsGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeMedicalProblemPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(medicalProblemButton)
                        .addComponent(clearMedicalProblemButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allMedicalProblemPane));
        GroupLayout.Group medicalProblemsGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeMedicalProblemPane)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(medicalProblemButton)
                            .addComponent(clearMedicalProblemButton))
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
        this.visit = visit;
        for (int i = 0; i < patientContent.getItemCount(); i++) {
            if (patientContent.getItemAt(i).getId() == visit.getPatient().getId()) {
                patientContent.setSelectedIndex(i);
            }
        }
        startDateText.setText(UtilsMethods.format(visit.getStartDate()));
        endDateText.setText(UtilsMethods.format(visit.getEndDate()));
        for (Treatment treatment : visit.getTreatmentsList()) {
            treatmentsListModel.addElement(TreatmentListOptionDTO.map(treatment));
        }
        for (MedicalProblem problem : visit.getMedicalProblemsList()) {
            activeMedicalProblemListModel.addElement(MedicationProblemListOptionDTO.map(problem));
        }
    }

    void clearPanel() {
        visit = null;
        startDateText.setText("");
        endDateText.setText("");
        treatmentsListModel.removeAllElements();
        activeMedicalProblemListModel.removeAllElements();
    }
}
