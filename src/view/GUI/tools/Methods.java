package view.GUI.tools; // ייבוא המחלקה Hospital

import control.Hospital;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static view.GUI.mainScreen.SystemUsersGUI.hospital;

public class Methods {

	public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // יצירת מופע של המחלקה Hospital
        //Hospital hospital = new Hospital();

        // יצירת פאנל
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, hospital);

        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel, Hospital hospital) {
        panel.setLayout(null); // שימוש במיקום אבסולוטי

        // תיבת תאריך
        JLabel dateLabel = new JLabel("Enter date (dd-MM-yyyy):");
        dateLabel.setBounds(10, 20, 160, 25);
        panel.add(dateLabel);

        JTextField dateInput = new JTextField(20);
        dateInput.setBounds(180, 20, 165, 25);
        panel.add(dateInput);

        // כפתור לבדוק ביקורים לפני תאריך
        JButton checkVisitsButton = new JButton("Check Visits Before Date");
        checkVisitsButton.setBounds(10, 60, 200, 25);
        panel.add(checkVisitsButton);

        // תווית לתוצאה
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 100, 300, 25);
        panel.add(resultLabel);

        // הוספת מאזין לכפתור
        checkVisitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateString = dateInput.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                dateFormat.setLenient(false);  // פורמט קשיח

                try {
                    // המרה של התאריך
                    Date date = dateFormat.parse(dateString);

                    // קריאה למתודה ב-Hospital לקבלת מספר ביקורים לפני התאריך
                    int count = hospital.howManyVisitBefore(date);
                    resultLabel.setText("Number of patients with visits before the date: " + count);
                } catch (ParseException ex) {
                    resultLabel.setText("Invalid date format. Please use the format dd-MM-yyyy.");
                }
            }
        });
    }
}