package view.GUI.tools;

//method 4.1  4.2  4.3

import control.Hospital; // ייבוא המחלקה Hospital
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.GUI.mainScreen.SystemUsersGUI.hospital;

public class Method4 {

    private static JButton intensiveCareStaffButton;
    private static JButton avgSalaryButton;
    private static JButton healthStandardButton;
    private static JLabel resultArea;


    public static void placeComponents(JPanel panel, Hospital hospital) {
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        // שימוש במיקום אבסולוטי

        intensiveCareStaffButton = new JButton("Intensive Care Staff Members");
        panel.add(intensiveCareStaffButton);

        avgSalaryButton = new JButton("Average Salary");
        panel.add(avgSalaryButton);

        healthStandardButton = new JButton("Health Ministry Standard");
        panel.add(healthStandardButton);


        resultArea = new JLabel();
        panel.add(resultArea);

        intensiveCareStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = hospital.howManyIntensiveCareStaffMembers();
                resultArea.setText("Number of Intensive Care Staff Members: " + count);
            }
        });

        avgSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double avgSalary = hospital.avgSalary();
                resultArea.setText("Average Salary of Staff Members: " + avgSalary);
            }
        });

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
                                    .addComponent(intensiveCareStaffButton)
                            )
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(avgSalaryButton)
                            )
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(healthStandardButton)
                            )
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(resultArea)
                            )
            );

            layout.setVerticalGroup(
                    layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(intensiveCareStaffButton)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(avgSalaryButton)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(healthStandardButton)
                            )
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(resultArea)
                            )
            );
        }
    }
