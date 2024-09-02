
package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.*;
import GUI.panels.BasePanel;
import control.Hospital;
import enums.Specialization;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditTreatmentsPanel extends EditPanel {

    public static final String EDIT_TREATMENT_PANEL = "EDIT_TREATMENT_PANEL";

    //Description
    private JLabel descriptionLabel;
    private JTextField descriptionText;
    //Doctor list
    private JLabel doctorLabel;
    private JScrollPane activeDoctorPane;
    private DefaultListModel<DoctorListOptionDTO> activeDoctorListModel;
    private JList<DoctorListOptionDTO> activeDoctorList;
    private DefaultListModel<DoctorListOptionDTO> allDoctorListModel;
    private JList<DoctorListOptionDTO> allDoctorList;
    private JScrollPane allDoctorPane;
    private JButton selectDoctorButton;
    //Medication list
    private JLabel medicationLabel;
    private JScrollPane activeMedicationPane;
    private DefaultListModel<MedicationListOptionDTO> activeMedicationListModel;
    private JList<MedicationListOptionDTO> activeMedicationList;
    private DefaultListModel<MedicationListOptionDTO> allMedicationListModel;
    private JList<MedicationListOptionDTO> allMedicationList;
    private JScrollPane allMedicationPane;
    private JButton medicationSelectButton;
    //Medical problem list
    private JLabel medicalProblemLabel;
    private JScrollPane activeMedicalProblemPane;
    private DefaultListModel<MedicationProblemListOptionDTO> activeMedicalProblemListModel;
    private JList<MedicationProblemListOptionDTO> activeMedicalProblemList;
    private DefaultListModel<MedicationProblemListOptionDTO> allMedicalProblemListModel;
    private JList<MedicationProblemListOptionDTO> allMedicalProblemList;
    private JScrollPane allMedicalProblemPane;
    private JButton medicalProblemButton;
    //Save button
    private JButton addButton;
    //Back button
    private JButton backButton;


    public EditTreatmentsPanel(BasePanel prev) {
        super(prev);

        buildDescriptionField();
        buildDoctorsListField();
        buildMedicationListField();
        buildMedicalProblemListField();
        buildSaveButton(prev);
        buildBackButton(prev);

        compose();
    }

    private void buildDescriptionField() {
        descriptionLabel = new JLabel("Treatment Description:");
        descriptionText = new JTextField();
    }

    private void buildDoctorsListField() {
        doctorLabel = new JLabel("Doctors:");
        activeDoctorListModel = new DefaultListModel<>();
        activeDoctorList = new JList<>(activeDoctorListModel);
        activeDoctorPane = new JScrollPane(activeDoctorList);

        allDoctorListModel = new DefaultListModel<>();
        allDoctorList = new JList<>(allDoctorListModel);
        allDoctorPane = new JScrollPane(allDoctorList);

        for (StaffMember member : hospital.getStaffMembers().values()) {
            if (member instanceof Doctor) {
                allDoctorListModel.addElement(DoctorListOptionDTO.map((Doctor) member));
            }
        }
        selectDoctorButton = new JButton("Select Sublist");
        selectDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private void buildMedicationListField() {
        medicationLabel = new JLabel("Medications:");
        activeMedicationListModel = new DefaultListModel<>();
        activeMedicationList = new JList<>(activeMedicationListModel);
        activeMedicationPane = new JScrollPane(activeMedicationList);

        allMedicationListModel = new DefaultListModel<>();
        allMedicationList = new JList<>(allMedicationListModel);
        allMedicationPane = new JScrollPane(allMedicationList);

        for (Medication medication : hospital.getMedications().values()) {
            allMedicationListModel.addElement(MedicationListOptionDTO.map(medication));
        }
        medicationSelectButton = new JButton("Select Sublist");
        medicationSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        addButton = new JButton("Save");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        GroupLayout.Group doctorGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeDoctorPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(selectDoctorButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allDoctorPane));
        GroupLayout.Group doctorGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeDoctorPane)
                        .addComponent(selectDoctorButton)
                        .addComponent(allDoctorPane));

        GroupLayout.Group medicationGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeMedicationPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(medicationSelectButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allMedicationPane));
        GroupLayout.Group medicationGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeMedicationPane)
                        .addComponent(medicationSelectButton)
                        .addComponent(allMedicationPane));

        GroupLayout.Group medicalProblemGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeMedicalProblemPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(medicalProblemButton))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allMedicalProblemPane));
        GroupLayout.Group medicalProblemGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeMedicalProblemPane)
                        .addComponent(medicalProblemButton)
                        .addComponent(allMedicalProblemPane));


        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(descriptionLabel)
                                .addComponent(doctorLabel)
                                .addComponent(medicationLabel)
                                .addComponent(medicalProblemLabel)
                                .addComponent(addButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(descriptionText)
                                .addGroup(doctorGroupHor)
                                .addGroup(medicationGroupHor)
                                .addGroup(medicalProblemGroupHor)
                                .addComponent(backButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(descriptionLabel)
                                .addComponent(descriptionText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(doctorLabel)
                                .addGroup(doctorGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(medicationLabel)
                                .addGroup(medicationGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(medicalProblemLabel)
                                .addGroup(medicalProblemGroupVer))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(backButton))
        );
    }

    public void fillFromObject(Treatment treatment) {
        clearPanel();
        descriptionText.setText(treatment.getDescription());
        for (Doctor doctor : treatment.getDoctorsList()) {
            activeDoctorListModel.addElement(DoctorListOptionDTO.map(doctor));
        }
        for (Medication medication : treatment.getMedicationsList()) {
            activeMedicationListModel.addElement(MedicationListOptionDTO.map(medication));
        }
        for (MedicalProblem problem : treatment.getMedicalProblemsList()) {
            activeMedicalProblemListModel.addElement(MedicationProblemListOptionDTO.map(problem));
        }
    }

    private void clearPanel() {
        descriptionText.setText("");
        activeDoctorListModel.removeAllElements();
        activeMedicationListModel.removeAllElements();
        activeMedicalProblemListModel.removeAllElements();
    }
}

