package GUI.panels.table_panels.edit_panels;


import GUI.actions.OpenPanelAction;
import GUI.dto.TreatmentListOptionDTO;
import GUI.panels.BasePanel;
import GUI.panels.table_panels.DepartmentsPanel;
import GUI.panels.table_panels.MedicationsPanel;
import enums.Specialization;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditMedicationPanel extends EditPanel {

    public static final String EDIT_MEDICATION_PANEL = "EDIT_MEDICATION_PANEL";
    private JLabel nameLabel;
    private JTextField nameText;
    private JLabel dosageLabel;
    private JTextField dosageText;
    private JLabel doseNumberLabel;
    private JTextField doseNumberText;
    private JButton saveMedicationButton;
    private JButton backButton;
    private GroupLayout layout;

    public EditMedicationPanel(BasePanel prev) {
        super(prev);
        buildName();
        buildDosageField();
        buildNumberOfDosageField();
        buildSaveButton(prev, this);
        buildBackButton(prev, this);

        compose();
    }

    private void buildName() {
        nameLabel = new JLabel("Medication Name:");
        nameText = new JTextField();
    }

    private void buildDosageField() {
        // Метка и текстовое поле для дозировки
        dosageLabel = new JLabel("Dosage (mg):");
        dosageText = new JTextField();
    }

    private void buildNumberOfDosageField() {
        // Mark and text field (numberOfDose)
        doseNumberLabel = new JLabel("Number of Doses:");
        doseNumberText = new JTextField();
    }

    private void buildSaveButton(BasePanel prev, EditMedicationPanel panel) {
        saveMedicationButton = new JButton("Save");
        saveMedicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codeMedication = hospital.generateNewMedicationCode();
                String name = nameText.getText();
                Double dosage = Double.valueOf(dosageText.getText());
                int numberOfDosage = Integer.parseInt(doseNumberText.getText());

                Medication newMedication = new Medication(codeMedication, name, dosage, numberOfDosage);
                if (hospital.addMedication(newMedication)) {
                    JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                    ((MedicationsPanel) prev).reloadData(hospital.getMedications());
                    new OpenPanelAction(getMainScreen(), prev.getPanelStringKey(), getCardLayout()).actionPerformed(e);
                    panel.clearPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong. Please contact administrator!", " ", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void buildBackButton(BasePanel prev, EditMedicationPanel panel) {
        // Кнопка возврата назад
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
        // Используем GroupLayout для расположения элементов
        layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel)
                                .addComponent(dosageLabel)
                                .addComponent(doseNumberLabel)
                                .addComponent(backButton))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nameText)
                                .addComponent(dosageText)
                                .addComponent(doseNumberText)
                                .addComponent(saveMedicationButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel)
                                .addComponent(nameText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dosageLabel)
                                .addComponent(dosageText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(doseNumberLabel)
                                .addComponent(doseNumberText))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(backButton)
                                .addComponent(saveMedicationButton))
        );
    }

    public void fillFromObject(Medication medication) {
        clearPanel();
        nameText.setText(medication.getName());
        dosageText.setText(Double.toString(medication.getDosage()));
        doseNumberText.setText(Integer.toString(medication.getNumberOfDose()));
    }

    void clearPanel() {
        nameText.setText("");
        dosageText.setText("");
        doseNumberText.setText("");
    }

}
