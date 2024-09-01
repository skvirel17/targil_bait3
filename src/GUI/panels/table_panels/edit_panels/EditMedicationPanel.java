package GUI.panels.table_panels.edit_panels;


import GUI.actions.OpenPanelAction;
import GUI.dto.MedicationListOptionDTO;
import GUI.panels.BasePanel;
import model.Medication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.*;

public class EditMedicationPanel extends EditPanel {

    public static final String EDIT_MEDICATION_PANEL = "EDIT_MEDICATION_PANEL";

    public EditMedicationPanel(BasePanel prev) {
        super(prev);

        // Метка и текстовое поле для названия лекарства
        JLabel nameLabel = new JLabel("Medication Name:");
        JTextField nameText = new JTextField();

        // Метка и текстовое поле для дозировки
        JLabel dosageLabel = new JLabel("Dosage (mg):");
        JTextField dosageText = new JTextField();

        // Метка и текстовое поле для количества доз (numberOfDose)
        JLabel doseNumberLabel = new JLabel("Number of Doses:");
        JTextField doseNumberText = new JTextField();

// Кнопка сохранения медикамента
        JButton saveMedicationButton = new JButton("Save Medication");
        saveMedicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
                String name = nameText.getText();
                String dosageStr = dosageText.getText();
                String numberOfDoseStr = doseNumberText.getText(); // Для количества доз

                if (name.isEmpty() || dosageStr.isEmpty() || numberOfDoseStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double dosage = Double.parseDouble(dosageStr);
                    int numberOfDose = Integer.parseInt(numberOfDoseStr); // Парсинг количества доз

                    // Создаем новый объект Medication
                    Medication newMedication = new Medication(generateUniqueCode(), name, dosage, numberOfDose);

                    // Добавляем его в систему (в hospital)
                    hospital.getMedications().put(newMedication.getCode(), newMedication);

                    JOptionPane.showMessageDialog(null, "Medication saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Возврат к предыдущей панели
                    new OpenPanelAction(getMainScreen(), prev.toString(), getCardLayout()).actionPerformed(e);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid dosage and number of doses.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Кнопка возврата назад
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

        // Используем GroupLayout для расположения элементов
        GroupLayout layout = new GroupLayout(this);
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

    private int generateUniqueCode() {
        return hospital.getMedications().size() + 1; // Генерация уникального кода
    }

}
