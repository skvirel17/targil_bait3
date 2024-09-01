
package GUI.panels.table_panels.edit_panels;

import GUI.actions.OpenPanelAction;
import GUI.dto.DoctorListOptionDTO;
import GUI.dto.MedicationListOptionDTO;
import GUI.dto.StaffMemberListOptionDTO;
import GUI.dto.TreatmentListOptionDTO;
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

    public EditTreatmentsPanel(BasePanel prev) {
        super(prev);

        JLabel descriptionLabel = new JLabel("Treatment Description:");
        JTextField descriptionText = new JTextField();

        //Doctors list
        JLabel doctorLabel = new JLabel("Doctors:");
        JScrollPane activeDoctorPane = new JScrollPane();
        DefaultListModel<String> activeDoctorListModel = new DefaultListModel<>();
        JList<String> activeDoctorList = new JList<>(activeDoctorListModel);
        activeDoctorPane.add(activeDoctorList);

        DefaultListModel<String> allDoctorListModel = new DefaultListModel<>();
        JList<String> allDoctorList = new JList<>(allDoctorListModel);
        JScrollPane allDoctorPane = new JScrollPane(allDoctorList);

        for (StaffMember member : hospital.getStaffMembers().values()) {
            if (member instanceof Doctor) {
                allDoctorListModel.addElement(DoctorListOptionDTO.map((Doctor) member).toString());
            }
        }
        JButton button = new JButton("Select Sublist");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        //Medication list
        JLabel medicationLabel = new JLabel("Medications:");
        JScrollPane activeMedicationPane = new JScrollPane();
        DefaultListModel<String> activeMedicationListModel = new DefaultListModel<>();
        JList<String> activeMedicationList = new JList<>(activeMedicationListModel);
        activeMedicationPane.add(activeMedicationList);

        DefaultListModel<String> allMedicationListModel = new DefaultListModel<>();
        JList<String> allMedicationList = new JList<>(allMedicationListModel);
        JScrollPane allMedicationPane = new JScrollPane(allMedicationList);

        for (Medication medication : hospital.getMedications().values()) {
            allMedicationListModel.addElement(MedicationListOptionDTO.map(medication).toString());
        }
        JButton medicationSelectButton = new JButton("Select Sublist");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        //MedicalProblem list
        JLabel medicalProblemLabel = new JLabel("Medical problems:");
        JScrollPane activeMedicalProblemPane = new JScrollPane();
        DefaultListModel<String> activeMedicalProblemListModel = new DefaultListModel<>();
        JList<String> activeMedicalProblemList = new JList<>(activeMedicalProblemListModel);
        activeMedicalProblemPane.add(activeMedicalProblemList);

        DefaultListModel<String> allMedicalProblemListModel = new DefaultListModel<>();
        JList<String> allMedicalProblemList = new JList<>(allMedicalProblemListModel);
        JScrollPane allMedicalProblemPane = new JScrollPane(allMedicalProblemList);

        for (MedicalProblem medicalProblem : hospital.getMedicalProblems().values()) {
            allMedicalProblemListModel.addElement("[" +medicalProblem.getCode() + "] " + medicalProblem.getName());
        }
        JButton medicalProblemButton = new JButton("Select Sublist");
        medicalProblemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton addButton = new JButton("Save");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        GroupLayout.Group doctorGroupHor = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(activeDoctorPane))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(button))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(allDoctorPane));
        GroupLayout.Group doctorGroupVer = layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(activeDoctorPane)
                        .addComponent(button)
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
}

