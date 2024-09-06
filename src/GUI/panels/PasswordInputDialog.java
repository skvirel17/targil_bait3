package GUI.panels;

import model.StaffMember;

import javax.swing.*;
import java.awt.*;

public class PasswordInputDialog extends JPanel {
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public PasswordInputDialog() {
        setLayout(new GridLayout(0, 2));

        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        add(new JLabel("Enter New Password:"));
        add(passwordField);
        add(new JLabel("Confirm Password:"));
        add(confirmPasswordField);
    }

    public String[] showPasswordDialog(Component parentComponent) {
        String[] options = {"OK", "Cancel"};

        int result = JOptionPane.showOptionDialog(parentComponent, this, "Change Password", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        if (result == JOptionPane.OK_OPTION) {
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            return new String[]{password, confirmPassword};
        }

        return null;
    }

    public static boolean confirmPassword(StaffMember staffMember) {
        PasswordInputDialog passwordDialog = new PasswordInputDialog();
        String[] passwords = passwordDialog.showPasswordDialog(null);

        while (passwords == null) {
            return false;
        }

        while (passwords[0].equals("") || passwords[1].equals("")) {
            JOptionPane.showMessageDialog(null, "New password cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            passwords = passwordDialog.showPasswordDialog(null);
        }

        String password = passwords[0];
        String confirmPassword = passwords[1];

        while (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            passwords = passwordDialog.showPasswordDialog(null);
            password = passwords[0];
            confirmPassword = passwords[1];
        }
        JOptionPane.showMessageDialog(null, "Password changed successfully!");
        staffMember.setPassWord(password);
        return true;
    }
}
