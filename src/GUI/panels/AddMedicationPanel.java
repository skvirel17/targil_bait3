package GUI.panels;

import GUI.actions.OpenPanelAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.panels.DashBoardPanel.DASHBOARD_PANEL;

public class AddMedicationPanel extends JPanel {

    public static final String ADD_MEDICATION_PANEL = "ADD_MEDICATION_PANEL";
    public  AddMedicationPanel(){
        super();
        // פאנל להוספת תרופה
        this.setLayout(new GridLayout(4, 2));

        JLabel medicationNameLabel = new JLabel("Medication Name:");
        JTextField medicationNameText = new JTextField();

        JLabel dosageLabel = new JLabel("Dosage:");
        JTextField dosageText = new JTextField();

        JLabel frequencyLabel = new JLabel("Frequency:");
        JTextField frequencyText = new JTextField();

        JButton saveMedicationButton = new JButton("Add Medication");
        saveMedicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:добавить обработку данных
                JOptionPane.showMessageDialog(null, "added successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()).actionPerformed(e);
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()));

        this.add(medicationNameLabel);
        this.add(medicationNameText);
        this.add(dosageLabel);
        this.add(dosageText);
        this.add(frequencyLabel);
        this.add(frequencyText);
        this.add(new JLabel());
        this.add(backButton);// מקום ריק ליישור
        this.add(saveMedicationButton);

    }
}
