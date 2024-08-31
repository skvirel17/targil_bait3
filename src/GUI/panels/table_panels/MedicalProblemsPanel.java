package GUI.panels.table_panels;

import model.MedicalProblem;

import java.util.Map;

public class MedicalProblemsPanel extends TablePanel {

    private static final Object[] columns = {"CODE", "NAME", "DEPARTMENT"};

    public MedicalProblemsPanel(Map<String, MedicalProblem> problems) {
        super(mapData(problems), columns, "");
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
}
