package GUI.tools;

//method 4.1  4.2  4.3

import control.Hospital; // ייבוא המחלקה Hospital
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.hospital;

public class Method4 {
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

        // כפתור 41: חישוב כמות אנשי צוות שמוכשרים לטיפול נמרץ
        JButton intensiveCareStaffButton = new JButton("Intensive Care Staff Members");
        intensiveCareStaffButton.setBounds(10, 20, 250, 25);
        panel.add(intensiveCareStaffButton);

        // כפתור 42: חישוב השכר הממוצע של אנשי הצוות
        JButton avgSalaryButton = new JButton("Average Salary");
        avgSalaryButton.setBounds(10, 60, 250, 25);
        panel.add(avgSalaryButton);

        // כפתור 43: בדיקת עמידה בסטנדרט של משרד הבריאות
        JButton healthStandardButton = new JButton("Health Ministry Standard");
        healthStandardButton.setBounds(10, 100, 250, 25);
        panel.add(healthStandardButton);

        // תווית לתוצאות
        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(10, 150, 450, 200);
        resultArea.setEditable(false); // השדה לא ניתן לעריכה
        panel.add(resultArea);

        // מאזין לכפתור 41: אנשי צוות מוכשרים לטיפול נמרץ
        intensiveCareStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = hospital.howManyIntensiveCareStaffMembers();
                resultArea.setText("Number of Intensive Care Staff Members: " + count);
            }
        });

        // מאזין לכפתור 42: השכר הממוצע של אנשי הצוות
        avgSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double avgSalary = hospital.avgSalary();
                resultArea.setText("Average Salary of Staff Members: " + avgSalary);
            }
        });

        // מאזין לכפתור 43: עמידה בסטנדרט משרד הבריאות
        healthStandardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isCompliant = hospital.isCompliesWithTheMinistryOfHealthStandard();
                if (isCompliant) {
                    resultArea.setText("The hospital complies with the Ministry of Health standard.");
                } else {
                    resultArea.setText("The hospital does not comply with the Ministry of Health standard.");
                }
            }
        });
    }
}
