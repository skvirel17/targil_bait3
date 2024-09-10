package GUI.panels;

import GUI.actions.OpenPanelAction;
import GUI.menu.menu_by_user_role.AdminMenu;
import GUI.menu.menu_by_user_role.DoctorMenu;
import GUI.menu.menu_by_user_role.NurseMenu;
import model.Doctor;
import model.Nurse;
import model.StaffMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


import static GUI.mainScreen.SystemUsersGUI.*;
import static GUI.panels.DashBoardPanel.DASHBOARD_PANEL;

public class LoginPanel extends BasePanel {

    public static final String LOGIN_PANEL = "LOGIN_PANEL";

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

        InputMap inputMap = loginButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = loginButton.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        inputMap.put(enterKey, "clickButton");
        actionMap.put("clickButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });
    }

    public static boolean isUserValid(String login, String passText) {
        if (login.equals("admin") && passText.equals("admin")) {
            getMainFrame().setJMenuBar(AdminMenu.getInstance());
            return true;
        }
        for(StaffMember staffMember: hospital.getStaffMembers().values()){
            if(staffMember.getEmail() != null && staffMember.getEmail().equals(login)){
                if(staffMember.getPassWord() != null && staffMember.getPassWord().equals(passText)){
                    if (passText.equals("")) {
                        if (!PasswordInputDialog.confirmPassword(staffMember)) {
                             return false;
                        }
                    }
                    if (staffMember instanceof Doctor) {
                        getMainFrame().setJMenuBar(DoctorMenu.getInstance());
                        createSession(staffMember.getId());
                    } else if (staffMember instanceof Nurse) {
                        getMainFrame().setJMenuBar(NurseMenu.getInstance());
                        createSession(staffMember.getId());
                    } else {
                        JOptionPane.showMessageDialog(null, "User is not defined!", "", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password!", "", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "User not found!", "", JOptionPane.WARNING_MESSAGE);
        return false;
    }
}
