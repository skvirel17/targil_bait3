package GUI.mainScreen;

import GUI.menu.*;
import GUI.panels.*;
import GUI.panels.table_panels.*;
import GUI.panels.table_panels.edit_panels.*;
import control.Hospital;
import model.StaffMember;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

import static GUI.panels.AddIssueAndTreatmentToVisitPanel.ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL;
import static GUI.panels.AddMedicationPanel.ADD_MEDICATION_PANEL;
import static GUI.panels.AddTreatmentPanel.ADD_TREATMENT_PANEL;
import static GUI.panels.AllPanel.ADD_ALL_PANEL;
import static GUI.panels.AppointNewManagerPanel.APPOINT_NEW_MANAGER_PANEL;
import static GUI.panels.DashBoardPanel.DASHBOARD_PANEL;
import static GUI.panels.EditProfilePanel.EDIT_PROFILE_PANEL;
import static GUI.panels.GetNumberOfDoctorsBySpecializationPanel.GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL;
import static GUI.panels.HowManyVisitBeforePanel.HOW_MANY_VISIT_BEFORE_PANEL;
import static GUI.panels.LoginPanel.LOGIN_PANEL;
import static GUI.panels.MedicationCalculatorPanel.MEDICATION_CALCULATOR_PANEL;
import static GUI.panels.StandardCheckPanel.STANDARD_CHECK_PANEL;
import static GUI.panels.ViewDataPanel.VIEW_DATA_PANEL;
import static GUI.panels.table_panels.DepartmentsPanel.DEPARTMENTS_PANEL;
import static GUI.panels.table_panels.MedicalProblemsPanel.MEDICAL_PROBLEMS_PANEL;
import static GUI.panels.table_panels.MedicationsPanel.MEDICATION_PANEL;
import static GUI.panels.table_panels.PatientsPanel.PATIENTS_PANEL;
import static GUI.panels.table_panels.StaffMembersPanel.STAFF_MEMBERS_PANEL;
import static GUI.panels.table_panels.TreatmentsPanel.TREATMENTS_PANEL;
import static GUI.panels.table_panels.VisitsPanel.VISITS_PANEL;
import static GUI.panels.table_panels.edit_panels.EditDepartmentPanel.EDIT_DEPARTMENT_PANEL;
import static GUI.panels.table_panels.edit_panels.EditMedicalProblemPanel.EDIT_MEDICAL_PROBLEM_PANEL;
import static GUI.panels.table_panels.edit_panels.EditMedicationPanel.EDIT_MEDICATION_PANEL;
import static GUI.panels.table_panels.edit_panels.EditPatientsPanel.EDIT_PATIENT_PANEL;
import static GUI.panels.table_panels.edit_panels.EditStaffMembersPanel.EDIT_STAFF_MEMBERS_PANEL;
import static GUI.panels.table_panels.edit_panels.EditTreatmentsPanel.EDIT_TREATMENT_PANEL;
import static GUI.panels.table_panels.edit_panels.EditVisitsPanel.EDIT_VISIT_PANEL;


public class SystemUsersGUI {

    private static final JFrame mainFrame = new MainScreenPanel();
    private static final JPanel mainScreen = new JPanel();
    private static final CardLayout cardLayout = new CardLayout();

    public static Hospital hospital;

    public static JFrame getMainFrame() {
        return mainFrame;
    }

    public static JPanel getMainScreen() {
        return mainScreen;
    }

    public static CardLayout getCardLayout() {
        return cardLayout;
    }


    public static void main(String[] args) {
        File file = new File("hospital.ser");

        if (file.exists()) {
            hospital = Hospital.deserialize("hospital.ser");
            for(Map.Entry<Integer, StaffMember> member : hospital.getStaffMembers().entrySet()){
                member.getValue().setPassWord("");
            }
        } else {
            hospital =  Hospital.getInstance();
            System.out.println("File hospital.ser does not exist.");
        }


        JPanel editProfilePanel = new EditProfilePanel();
        JPanel addMedicationPanel = new AddMedicationPanel();
        JPanel addIssueAndTreatmentToVisitPanel = new AddIssueAndTreatmentToVisitPanel();
        JPanel addAllPanel = new AllPanel();
        JPanel viewDataPanel = new ViewDataPanel();
        JPanel addTreatmentPanel = new AddTreatmentPanel();
        JPanel dashboardPanel = new DashBoardPanel();
        LoginPanel loginPanel = new LoginPanel();
        JPanel departmentsPanel = new DepartmentsPanel(hospital.getDepartments());
        JPanel medicalProblemsPanel = new MedicalProblemsPanel(hospital.getMedicalProblems());
        JPanel staffMembersPanel = new StaffMembersPanel(hospital.getStaffMembers());
        JPanel medicationPanel = new MedicationsPanel(hospital.getMedications());
        JPanel patientsPanel = new PatientsPanel(hospital.getPatients());
        JPanel treatmentsPanel = new TreatmentsPanel(hospital.getTreatments());
        JPanel visitsPanel = new VisitsPanel(hospital.getVisits());
        JPanel medicationCalculator = new MedicationCalculatorPanel();
        JPanel howManyVisitBeforePanel = new HowManyVisitBeforePanel();
        JPanel getNumberOfDoctorsBySpecializationPanel = new GetNumberOfDoctorsBySpecializationPanel();
        JPanel standardCheckPanel = new StandardCheckPanel();
        JPanel appointNewManagerPanel = new AppointNewManagerPanel();
        JPanel editDepartmentPanel = new EditDepartmentPanel((BasePanel) departmentsPanel);
        JPanel editMedicalProblemPanel = new EditMedicalProblemPanel((BasePanel) medicalProblemsPanel);
        JPanel editPatientsPanel = new EditPatientsPanel((BasePanel) patientsPanel);
        JPanel editMedicationPanel = new EditMedicationPanel((BasePanel) medicationPanel);
        JPanel editStaffMembersPanel = new EditStaffMembersPanel((BasePanel) staffMembersPanel);
        JPanel editTreatmentPanel = new EditTreatmentsPanel((BasePanel) treatmentsPanel);
        JPanel editVisitsPanel = new EditVisitsPanel((BasePanel) visitsPanel);
        ((DepartmentsPanel) departmentsPanel).itemPanel = editDepartmentPanel;
        ((MedicalProblemsPanel) medicalProblemsPanel).itemPanel = editMedicalProblemPanel;
        ((MedicationsPanel) medicationPanel).itemPanel = editMedicationPanel;
        ((PatientsPanel) patientsPanel).itemPanel = editPatientsPanel;
        ((StaffMembersPanel) staffMembersPanel).itemPanel = editStaffMembersPanel;
        ((TreatmentsPanel) treatmentsPanel).itemPanel = editTreatmentPanel;
        ((VisitsPanel) visitsPanel).itemPanel = editVisitsPanel;

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new BaseMenu());
        menuBar.add(new AccountMenu());
        menuBar.add(new ServiceMenu());
        menuBar.add(new DataMenu());
        menuBar.add(new ToolsMenu());
        mainFrame.setJMenuBar(menuBar);

        mainScreen.setLayout(cardLayout);
        mainScreen.add(loginPanel, LOGIN_PANEL);
        mainScreen.add(editProfilePanel, EDIT_PROFILE_PANEL);
        mainScreen.add(addAllPanel, ADD_ALL_PANEL);
        mainScreen.add(addMedicationPanel, ADD_MEDICATION_PANEL);
        mainScreen.add(addTreatmentPanel, ADD_TREATMENT_PANEL);
        mainScreen.add(addIssueAndTreatmentToVisitPanel, ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL);
        mainScreen.add(viewDataPanel, VIEW_DATA_PANEL);
        mainScreen.add(dashboardPanel, DASHBOARD_PANEL);
        mainScreen.add(departmentsPanel, DEPARTMENTS_PANEL);
        mainScreen.add(medicalProblemsPanel, MEDICAL_PROBLEMS_PANEL);
        mainScreen.add(staffMembersPanel, STAFF_MEMBERS_PANEL);
        mainScreen.add(medicationPanel, MEDICATION_PANEL);
        mainScreen.add(patientsPanel, PATIENTS_PANEL);
        mainScreen.add(treatmentsPanel, TREATMENTS_PANEL);
        mainScreen.add(visitsPanel, VISITS_PANEL);
        mainScreen.add(medicationCalculator, MEDICATION_CALCULATOR_PANEL);
        mainScreen.add(howManyVisitBeforePanel, HOW_MANY_VISIT_BEFORE_PANEL);
        mainScreen.add(getNumberOfDoctorsBySpecializationPanel, GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL);
        mainScreen.add(standardCheckPanel, STANDARD_CHECK_PANEL);
        mainScreen.add(appointNewManagerPanel, APPOINT_NEW_MANAGER_PANEL);
        mainScreen.add(editDepartmentPanel, EDIT_DEPARTMENT_PANEL);
        mainScreen.add(editMedicalProblemPanel, EDIT_MEDICAL_PROBLEM_PANEL);
        mainScreen.add(editPatientsPanel, EDIT_PATIENT_PANEL);
        mainScreen.add(editMedicationPanel, EDIT_MEDICATION_PANEL);
        mainScreen.add(editStaffMembersPanel, EDIT_STAFF_MEMBERS_PANEL);
        mainScreen.add(editTreatmentPanel, EDIT_TREATMENT_PANEL);
        mainScreen.add(editVisitsPanel, EDIT_VISIT_PANEL);

        mainFrame.setContentPane(mainScreen);

        cardLayout.show(mainScreen, LOGIN_PANEL);
        // הצגת החלון
        mainFrame.setVisible(true);


    }
}
