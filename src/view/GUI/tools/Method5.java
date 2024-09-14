package view.GUI.tools;
// method 44

	import view.GUI.dto.DepartmentOptionDTO;
	import control.Hospital;
	import model.Department; // ייבוא המחלקה Department
	import model.Doctor;

	import javax.swing.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

public class Method5 {
	private static JLabel departmentLabel;
	private static JComboBox<DepartmentOptionDTO> departmentField;
	private static JButton appointManagerButton;
	private static JLabel resultArea;
	    public static void placeComponents(JPanel panel, Hospital hospital) {
			GroupLayout layout = new GroupLayout(panel);// שימוש במיקום אבסולוטי
			panel.setLayout(layout);

	        // יצירת תיבה להזנת מחלקה
	        departmentLabel = new JLabel("Enter Department:");
	        panel.add(departmentLabel);

	        departmentField = new JComboBox<>();
			for(Department item: hospital.getDepartments().values()){
				departmentField.addItem(DepartmentOptionDTO.map(item));
			}
	        panel.add(departmentField);

	        // כפתור למינוי מנהל חדש למחלקה
			appointManagerButton = new JButton("Appoint New Manager");
	        panel.add(appointManagerButton);

	        // תווית לתוצאות
	        resultArea = new JLabel();
	        panel.add(resultArea);

	        // מאזין לכפתור מינוי מנהל חדש למחלקה
	        appointManagerButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                Department department = (Department) departmentField.getSelectedItem();
	                if (department == null) {
	                    resultArea.setText("Please enter a department name.");
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
			compose(layout);
	    }

	private static void compose(GroupLayout layout) {

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(
				layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addComponent(departmentLabel)
								.addComponent(departmentField)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(appointManagerButton)
						)
						.addGroup(layout.createSequentialGroup()
								.addComponent(resultArea)
						)
		);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(departmentLabel)
								.addComponent(departmentField)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(appointManagerButton)
						)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(resultArea)
						)
		);
	}
	}
