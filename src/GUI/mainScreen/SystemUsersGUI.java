package GUI.mainScreen;

import GUI.menu.*;
import GUI.panels.*;
import GUI.panels.table_panels.*;
import GUI.panels.table_panels.edit_panels.EditDepartmentPanel;
import control.Hospital;
import model.StaffMember;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;


public class SystemUsersGUI {
    private static final String FRAME_NAME = "Doctor Dashboard";
    public static final String LOGIN_PANEL = "LOGIN_PANEL";
    public static final String EDIT_PROFILE_PANEL = "EDIT_PROFILE_PANEL";
    public static final String ADD_ALL_PANEL = "ADD_ALL_PANEL";
    public static final String ADD_MEDICATION_PANEL = "ADD_MEDICATION_PANEL";
    public static final String ADD_TREATMENT_PANEL = "ADD_TREATMENT_PANEL";
    public static final String ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL = "ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL";
    public static final String VIEW_DATA_PANEL = "VIEW_DATA_PANEL";
    public static final String DASHBOARD_PANEL = "DASHBOARD_PANEL";
    public static final String DEPARTMENTS_PANEL = "DEPARTMENTS_PANEL";
    public static final String MEDICAL_PROBLEMS_PANEL = "MEDICAL_PROBLEMS_PANEL";
    public static final String STAFF_MEMBERS_PANEL = "STAFF_MEMBERS_PANEL";
    public static final String MEDICATION_PANEL = "MEDICATION_PANEL";
    public static final String PATIENTS_PANEL = "PATIENTS_PANEL";
    public static final String TREATMENTS_PANEL = "TREATMENTS_PANEL";
    public static final String VISITS_PANEL = "VISITS_PANEL";

    public static final String MEDICATION_CALCULATOR_PANEL = "MEDICATION_CALCULATOR_PANEL";
    public static final String HOW_MANY_VISIT_BEFORE_PANEL = "HOW_MANY_VISIT_BEFORE_PANEL";
    public static final String GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL = "GET_NUMBER_OF_DOCTOR_BY_SPECIALIZATION_PANEL";
    public static final String STANDARD_CHECK_PANEL = "STANDARD_CHECK_PANEL";
    public static final String APPOINT_NEW_MANAGER_PANEL = "APPOINT_NEW_MANAGER_PANEL";
    public static final String EDIT_DEPARTMENT_PANEL = "EDIT_DEPARTMENT_PANEL";
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

        //hospital = Hospital.deserialize("hospital.ser");
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
        JPanel loginPanel = new LoginPanel();
        DepartmentsPanel departmentsPanel = new DepartmentsPanel(hospital.getDepartments());
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
        JPanel editDepartmentPanel = new EditDepartmentPanel(departmentsPanel);

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
        mainScreen.add(addAllPanel,ADD_ALL_PANEL);
        mainScreen.add(addMedicationPanel, ADD_MEDICATION_PANEL);
        mainScreen.add(addTreatmentPanel, ADD_TREATMENT_PANEL);
        mainScreen.add(addIssueAndTreatmentToVisitPanel, ADD_ISSUE_AND_TREATMENT_TO_VISIT_PANEL);
        mainScreen.add(viewDataPanel, VIEW_DATA_PANEL);
        mainScreen.add(dashboardPanel, DASHBOARD_PANEL);
        mainScreen.add(departmentsPanel, departmentsPanel.getPanelStringKey());
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

        mainFrame.setContentPane(mainScreen);

        cardLayout.show(mainScreen, LOGIN_PANEL);
        // הצגת החלון
        mainFrame.setVisible(true);


    }
}
