package GUI.panels;

import GUI.actions.OpenPanelAction;
import model.StaffMember;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.DASHBOARD_PANEL;
import static GUI.mainScreen.SystemUsersGUI.hospital;

public class LoginPanel extends BasePanel {

    public LoginPanel() {
        super();

        // פאנל לכניסה למערכת
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel panelLabel = new JLabel("AUTHORIZATION");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 15;
        this.add(panelLabel, gbc);
        gbc.gridwidth = 1;

        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(userLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(userText, gbc);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passText = new JPasswordField(10);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(passLabel, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        this.add(passText, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        this.add(loginButton, gbc);

        // מאזין לכפתור התחברות
        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            if (isUserValid(username, password)){
                JOptionPane.showMessageDialog(null, "You logged in successfully!", "", JOptionPane.INFORMATION_MESSAGE);
                new OpenPanelAction(base, DASHBOARD_PANEL, cardLayout).actionPerformed(e);
            }
        });
    }

    public static boolean isUserValid(String login, String passText) {
        for(Map.Entry<Integer, StaffMember> entry: hospital.getStaffMembers().entrySet()){
            if(entry.getValue().getEmail()!= null && entry.getValue().getEmail().equals(login) ){
                if(entry.getValue().getPassWord() != null && entry.getValue().getPassWord().equals(passText)){
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password!", "", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found!", "", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return false;
    }
}
