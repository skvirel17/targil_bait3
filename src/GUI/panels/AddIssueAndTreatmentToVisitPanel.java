package GUI.panels;

import GUI.actions.OpenPanelAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.panels.DashBoardPanel.DASHBOARD_PANEL;

public class AddIssueAndTreatmentToVisitPanel extends JPanel{
    public static final String ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL = "ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL";
    public AddIssueAndTreatmentToVisitPanel(){
        super();
        this.setLayout(new GridLayout(4, 2));

        JLabel issueNameLabel = new JLabel("Medical Issue Name:");
        JTextField issueNameText = new JTextField();

        JLabel visitTreatmentNameLabel = new JLabel("Treatment Name:");
        JTextField visitTreatmentNameText = new JTextField();

        JButton saveIssueAndTreatmentButton = new JButton("Add Issue and Treatment");
        saveIssueAndTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:добавить обработку данных
                JOptionPane.showMessageDialog(null, "saved successfully!", " ", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()).actionPerformed(e);
            }
        });

        JButton backButton = new JButton("back");
        backButton.addActionListener(new OpenPanelAction(getMainScreen(), DASHBOARD_PANEL, getCardLayout()));


        this.add(issueNameLabel);
        this.add(issueNameText);
        this.add(visitTreatmentNameLabel);
        this.add(visitTreatmentNameText);
        this.add(new JLabel());
        this.add(backButton);// מקום ריק ליישור
        this.add(saveIssueAndTreatmentButton);

    }
}
