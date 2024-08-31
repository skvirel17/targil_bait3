package GUI.panels;

import GUI.actions.OpenPanelAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.panels.DashBoardPanel.DASHBOARD_PANEL;

public class EditProfilePanel extends JPanel {
    public static final String EDIT_PROFILE_PANEL = "EDIT_PROFILE_PANEL";
    public EditProfilePanel(){
        super();
        // פאנל עריכת פרופיל
        this.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameText = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageText = new JTextField();

        JLabel specializationLabel = new JLabel("Specialization:");
        JTextField specializationText = new JTextField();

        JLabel icuLabel = new JLabel("ICU Doctor:");
        JCheckBox icuCheckBox = new JCheckBox();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:добавить обработку данных
                JOptionPane.showMessageDialog(null, "saved successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()).actionPerformed(e);
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()));

        this.setLayout(new GridLayout(6, 2));

        this.add(nameLabel);
        this.add(nameText);
        this.add(ageLabel);
        this.add(ageText);
        this.add(specializationLabel);
        this.add(specializationText);
        this.add(icuLabel);
        this.add(icuCheckBox);
        this.add(new JLabel()); // מקום ריק ליישור
        this.add(saveButton);
        this.add(backButton);

    }
}
