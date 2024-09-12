package GUI.tools;

// method 40
import control.Hospital; // ייבוא המחלקה Hospital
import enums.Specialization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import static GUI.mainScreen.SystemUsersGUI.hospital;

public class Method3 {

    private static JComboBox<Specialization> specializationTextField;
    private static JLabel specializationLabel;
    private static JButton checkDoctorsBySpecializationButton;
    private static JLabel resultArea;

    public static void placeComponents(JPanel panel, Hospital hospital) {
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout); // שימוש במיקום אבסולוטי

        // כפתור לחישוב כמות הרופאים לפי התמחות
        checkDoctorsBySpecializationButton = new JButton("Check Doctors by Specialization");
        panel.add(checkDoctorsBySpecializationButton);

        // יצירת תיבת בחירה לתחום התמחות (מחרוזות במקום אובייקט התמחות)
        specializationLabel = new JLabel("Choose Specialization:");
        panel.add(specializationLabel);

        specializationTextField = new JComboBox();
        for (Specialization item : Specialization.values()) {
            specializationTextField.addItem(item);
        }
        panel.add(specializationTextField);

        resultArea = new JLabel();
        panel.add(resultArea);

        // הוספת מאזין לכפתור
        checkDoctorsBySpecializationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // קבלת ההתמחות שהוזנה מהתיבה
                Specialization enteredSpecialization = (Specialization) specializationTextField.getSelectedItem();

                // קריאה למתודה ב-Hospital לקבלת מספר הרופאים לפי התמחות
                HashMap<Specialization, Integer> result = hospital.getNumberOfDoctorsBySpecialization();

                // בדיקה האם תחום ההתמחות קיים במפה
                if (result.containsKey(enteredSpecialization)) {
                    int numDoctors = result.get(enteredSpecialization);

                    // הצגת התוצאה על המסך
                    resultArea.setText("Number of Doctors for specialization " + enteredSpecialization + ": " + numDoctors);
                } else {
                    resultArea.setText("Specialization not found.");
                }
            }
        });
        compose(layout);
    }

    private static void compose(GroupLayout layout) {

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(specializationLabel)
                                .addComponent(specializationTextField)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(checkDoctorsBySpecializationButton)
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(resultArea)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(specializationLabel)
                                .addComponent(specializationTextField)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(checkDoctorsBySpecializationButton)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(resultArea)
                        )
        );
    }
}

