package GUI.panels;

import GUI.actions.OpenPanelAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.*;

public class AllPanel extends JPanel{
    public  AllPanel(){
        super();

        this.setLayout(new GridLayout(6, 2));

        JLabel allMedicationNameLabel = new JLabel("Medication Name:");
        JTextField allMedicationNameText = new JTextField();

        JLabel allIssueNameLabel = new JLabel("Medical Issue Name:");
        JTextField allIssueNameText = new JTextField();

        JLabel allTreatmentNameLabel = new JLabel("Treatment Name:");
        JTextField allTreatmentNameText = new JTextField();

        JButton backButton = new JButton("back");
        backButton.addActionListener(new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()));


        JButton saveAllButton = new JButton("Add All");
        saveAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:добавить обработку данных
                JOptionPane.showMessageDialog(null, "saved successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()).actionPerformed(e);
            }
        });

        this.add(allMedicationNameLabel);
        this.add(allMedicationNameText);
        this.add(allIssueNameLabel);
        this.add(allIssueNameText);
        this.add(allTreatmentNameLabel);
        this.add(allTreatmentNameText);
        this.add(new JLabel());
        this.add(backButton);// מקום ריק ליישור
        this.add(saveAllButton);

    }
}
