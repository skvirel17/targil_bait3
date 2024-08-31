package GUI.login;

import control.Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    private static JFrame frame;
    private static JTextField userText;
    private static JPasswordField passwordText;

    public static void main(String[] args) {
        Hospital hospital = Hospital.deserialize("hospital.ser");
        // Создание и отображение окна
        SwingUtilities.invokeLater(Login::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Создаем основное окно
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2));

        // Метки и поля для ввода имени пользователя и пароля
        JLabel userLabel = new JLabel("Username:");
        JTextField userText = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordText = new JPasswordField();

        // Кнопка для входа
        JButton loginButton = new JButton("Entry");

        // Добавляем компоненты на окно
        frame.add(userLabel);
        frame.add(userText);
        frame.add(passwordLabel);
        frame.add(passwordText);
        frame.add(new JLabel());  // Пустая метка для выравнивания кнопки
        frame.add(loginButton);

        // Центрируем окно на экране
        frame.setLocationRelativeTo(null);

        // Отображаем окно
        frame.setVisible(true);

        // Обработчик нажатия на кнопку "Войти"
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                // Проверка имени пользователя и пароля
                if (checkCredentials(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Entry success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Закрытие окна после успешного входа
                    frame.dispose();
                    showMainScreen(); // Вызов главного экрана
                } else {
                    JOptionPane.showMessageDialog(frame, "Wrong password or username.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }

    // Метод для проверки учетных данных
    private static boolean checkCredentials(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("hospital.ser"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    String storedUsername = credentials[0];
                    String storedPassword = credentials[1];
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true; // Имя пользователя и пароль совпали
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Имя пользователя и пароль не совпали
    }

    // Метод для отображения главного экрана после успешного входа
    private static void showMainScreen() {
        JFrame mainFrame = new JFrame("Main Screen");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Main Screen!");
        welcomeLabel.setBounds(50, 50, 300, 25);
        mainFrame.add(welcomeLabel);

        mainFrame.setVisible(true);
    }
}
