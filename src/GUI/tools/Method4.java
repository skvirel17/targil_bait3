package GUI.tools;

//method 4.1  4.2  4.3

import control.Hospital; // ייבוא המחלקה Hospital
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.hospital;

public class Method4 {
    private static JLabel resultArea;
    private static JButton healthStandardButton;
    private static JButton avgSalaryButton;
    private static JButton intensiveCareStaffButton;

    public static void placeComponents(JPanel panel, Hospital hospital) {
        GroupLayout layout = new GroupLayout(panel);// שימוש במיקום אבסולוטי
        panel.setLayout(layout); // שימוש במיקום אבסולוטי

        // כפתור 41: חישוב כמות אנשי צוות שמוכשרים לטיפול נמרץ
        intensiveCareStaffButton = new JButton("Intensive Care Staff Members");
        panel.add(intensiveCareStaffButton);

        // כפתור 42: חישוב השכר הממוצע של אנשי הצוות
        avgSalaryButton = new JButton("Average Salary                  ");
        panel.add(avgSalaryButton);

        // כפתור 43: בדיקת עמידה בסטנדרט של משרד הבריאות
        healthStandardButton = new JButton("Health Ministry Standard");
        panel.add(healthStandardButton);

        // תווית לתוצאות
        resultArea = new JLabel();
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
        compose(layout);
    }

        private static void compose(GroupLayout layout) {

            layout.setAutoCreateGaps(true);
            layout.setAutoCreateContainerGaps(true);

            layout.setHorizontalGroup(
                    layout.createParallelGroup()
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(healthStandardButton))
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(avgSalaryButton))
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(intensiveCareStaffButton))
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(resultArea))
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(healthStandardButton))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(avgSalaryButton))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(intensiveCareStaffButton))
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(resultArea))
            );
    }
}
