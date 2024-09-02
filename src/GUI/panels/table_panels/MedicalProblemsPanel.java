package GUI.panels.table_panels;

import GUI.actions.OpenPanelAction;
import GUI.mainScreen.SystemUsersGUI;
import GUI.panels.table_panels.edit_panels.EditDepartmentPanel;
import GUI.panels.table_panels.edit_panels.EditMedicalProblemPanel;
import model.Department;
import model.MedicalProblem;

import javax.swing.*;
import java.util.Map;

import static GUI.mainScreen.SystemUsersGUI.getCardLayout;
import static GUI.mainScreen.SystemUsersGUI.hospital;
import static GUI.panels.table_panels.edit_panels.EditMedicalProblemPanel.EDIT_MEDICAL_PROBLEM_PANEL;

public class MedicalProblemsPanel extends TablePanel {
    public static final String MEDICAL_PROBLEMS_PANEL = "MEDICAL_PROBLEMS_PANEL";

    private static final Object[] columns = {"CODE", "NAME", "DEPARTMENT"};
    public JPanel itemPanel;

    public MedicalProblemsPanel(Map<String, MedicalProblem> problems) {
        super(mapData(problems), columns, EDIT_MEDICAL_PROBLEM_PANEL);

        //Adding Edit button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[1]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        (new OpenPanelAction(SystemUsersGUI.getMainScreen(), this.getItemInfoPanel(), getCardLayout())).actionPerformed(e);
                        String code = (String) this.getContent().getModel().
                                getValueAt(this.getContent().getSelectedRow(), 0);
                        MedicalProblem editMedicalProblem = hospital.getMedicalProblem(code);
                        ((EditMedicalProblemPanel)itemPanel).fillFromObject(editMedicalProblem);
                    }

                }
        );

        //Adding Delete button action
        ((JButton)((JPanel) this.getComponents()[1]).getComponents()[2]).addActionListener(e ->
                {
                    if (this.getContent().getSelectedRow() != -1) {
                        String code = (String) this.getContent().getModel().
                            getValueAt(this.getContent().getSelectedRow(), 0);

                        hospital.removeMedicalProblem(hospital.getMedicalProblem(code));
                        this.reloadData(hospital.getMedicalProblems());
                    }
                }
        );
    }

    private static Object[][] mapData(Map<String, MedicalProblem> problems) {
        Object[][] data = new Object[problems.size()][columns.length];

        int i = 0;

        for (Map.Entry<String, MedicalProblem> problemsEntry : problems.entrySet()){
            data[i][0] = problemsEntry.getKey();
            data[i][1] = problemsEntry.getValue().getName();
            data[i][2] = problemsEntry.getValue().getDepartment().getName();
            i++;
        }

        return data;
    }

    @Override
    public String getPanelStringKey() {
        return MEDICAL_PROBLEMS_PANEL;
    }

    public void reloadData(Map<String, MedicalProblem> medicalProblemMap) {
        reloadData(mapData(medicalProblemMap), columns);
    }
}
