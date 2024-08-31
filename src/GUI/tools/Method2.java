package GUI.tools;
// method 33
import javax.swing.*;

import control.Hospital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.hospital;

public class Method2  {
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

        // תווית ותיבת קלט עבור מינון מינימלי
        JLabel minDosageLabel = new JLabel("Min Dosage:");
        minDosageLabel.setBounds(10, 20, 100, 25);
        panel.add(minDosageLabel);

        JTextField minDosageInput = new JTextField(20);
        minDosageInput.setBounds(120, 20, 165, 25);
        panel.add(minDosageInput);

        // תווית ותיבת קלט עבור מינון מקסימלי
        JLabel maxDosageLabel = new JLabel("Max Dosage:");
        maxDosageLabel.setBounds(10, 60, 100, 25);
        panel.add(maxDosageLabel);

        JTextField maxDosageInput = new JTextField(20);
        maxDosageInput.setBounds(120, 60, 165, 25);
        panel.add(maxDosageInput);

        // כפתור לבדוק כמות התרופות
        JButton checkMedicationsButton = new JButton("Check Medications");
        checkMedicationsButton.setBounds(10, 100, 200, 25);
        panel.add(checkMedicationsButton);

        // תווית לתוצאה
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 140, 400, 25);
        panel.add(resultLabel);

        // הוספת מאזין לכפתור
        checkMedicationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // קריאת ערכים מהשדות
                    double minDosage = Double.parseDouble(minDosageInput.getText());
                    double maxDosage = Double.parseDouble(maxDosageInput.getText());

                    // קריאה למתודה ב-Hospital לקבלת מספר התרופות לפי המינון
                    int count = hospital.countMedications(minDosage, maxDosage);

                    // הצגת התוצאה על המסך
                    resultLabel.setText("Number of medications with dosage between " + minDosage + " and " + maxDosage + ": " + count);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid dosage format. Please enter valid numbers.");
                }
            }
        });
    }
}
