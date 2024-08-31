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
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // יצירת מופע של המחלקה Hospital
        //Hospital hospital = new Hospital();

        // יצירת פאנל
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, hospital);

        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel, Hospital hospital) {
        panel.setLayout(null); // שימוש במיקום אבסולוטי

        // כפתור לחישוב כמות הרופאים לפי התמחות
        JButton checkDoctorsBySpecializationButton = new JButton("Check Doctors by Specialization");
        checkDoctorsBySpecializationButton.setBounds(10, 60, 250, 25);
        panel.add(checkDoctorsBySpecializationButton);

        // יצירת תיבת בחירה לתחום התמחות (מחרוזות במקום אובייקט התמחות)
        JLabel specializationLabel = new JLabel("Enter Specialization:");
        specializationLabel.setBounds(10, 20, 200, 25);
        panel.add(specializationLabel);

        JTextField specializationTextField = new JTextField();
        specializationTextField.setBounds(150, 20, 200, 25);
        panel.add(specializationTextField);

        // תווית לתוצאה
        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 100, 450, 250);
        resultArea.setEditable(false); // השדה לא ניתן לעריכה
        panel.add(resultArea);

        // הוספת מאזין לכפתור
        checkDoctorsBySpecializationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // קבלת ההתמחות שהוזנה מהתיבה
                String enteredSpecialization = specializationTextField.getText();

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
    }
}
