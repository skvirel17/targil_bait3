package GUI.panels;

import GUI.actions.OpenPanelAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.panels.DashBoardPanel.DASHBOARD_PANEL;

public class AddTreatmentPanel extends JPanel {
    public static final String ADD_TREATMENT_PANEL = "ADD_TREATMENT_PANEL";
    public  AddTreatmentPanel(){
        super();
        // פאנל להוספת טיפול
        this.setLayout(new GridLayout(4, 2));

        JLabel treatmentNameLabel = new JLabel("Treatment Name:");
        JTextField treatmentNameText = new JTextField();

        JLabel treatmentDescriptionLabel = new JLabel("Description:");
        JTextField treatmentDescriptionText = new JTextField();

        JButton saveTreatmentButton = new JButton("Add Treatment");
        saveTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:добавить обработку данных
                JOptionPane.showMessageDialog(null, "saved successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()).actionPerformed(e);
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()));


        this.add(treatmentNameLabel);
        this.add(treatmentNameText);
        this.add(treatmentDescriptionLabel);
        this.add(treatmentDescriptionText);
        this.add(new JLabel());
        this.add(backButton);// מקום ריק ליישור
        this.add(saveTreatmentButton);

    }
}
