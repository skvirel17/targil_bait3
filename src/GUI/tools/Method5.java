package GUI.tools;
// method 44

	import control.Hospital;
	import model.Department; // ייבוא המחלקה Department
	import model.Doctor;

	import javax.swing.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

    import static GUI.mainScreen.SystemUsersGUI.hospital;

public class Method5 {
	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Hospital System");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(500, 400);

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

	        // יצירת תיבה להזנת מחלקה
	        JLabel departmentLabel = new JLabel("Enter Department:");
	        departmentLabel.setBounds(10, 20, 200, 25);
	        panel.add(departmentLabel);

	        JTextField departmentField = new JTextField(20);
	        departmentField.setBounds(150, 20, 165, 25);
	        panel.add(departmentField);

	        // כפתור למינוי מנהל חדש למחלקה
	        JButton appointManagerButton = new JButton("Appoint New Manager");
	        appointManagerButton.setBounds(10, 60, 250, 25);
	        panel.add(appointManagerButton);

	        // תווית לתוצאות
	        JTextArea resultArea = new JTextArea();
	        resultArea.setBounds(10, 100, 450, 250);
	        resultArea.setEditable(false); // השדה לא ניתן לעריכה
	        panel.add(resultArea);

	        // מאזין לכפתור מינוי מנהל חדש למחלקה
	        appointManagerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String departmentName = departmentField.getText();
	                if (departmentName.isEmpty()) {
	                    resultArea.setText("Please enter a department name.");
	                    return;
	                }

	                // מחפשים את המחלקה לפי השם או מזהה (משתנה לפי איך שהמערכת פועלת)
	                Department department = hospital.getDepartmentByName(departmentName); // המתודה הזו צריכה להיות קיימת
	                if (department == null) {
	                    resultArea.setText("Department not found.");
	                    return;
	                }

	                // מינוי מנהל חדש למחלקה
	                Doctor newManager = hospital.AppointANewManager(department);
	                if (newManager != null) {
	                    resultArea.setText("New Manager Appointed: " + newManager.getFirstName() + "\n"
	                            + "Specialization: " + newManager.getSpecialization() + "\n"
	                            + "New Salary: " + newManager.getSalary());
	                } else {
	                    resultArea.setText("No eligible doctor found for this department.");
	                }
	            }
	        });
	    }
	}
