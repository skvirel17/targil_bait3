package GUI.panels.table_panels;

import model.Visit;

import java.util.Map;

public class VisitsPanel extends TablePanel {
    public static final String VISITS_PANEL = "VISITS_PANEL";

    private static final Object[] columns = {"NUMBER", "PATIENT", "START_DATE", "END_DATE"};

    public VisitsPanel(Map<Integer, Visit> visits) {
        super(mapData(visits), columns, "");
    }

    private static Object[][] mapData(Map<Integer, Visit> map) {
        Object[][] data = new Object[map.size()][columns.length];

        int i = 0;

        for (Map.Entry<Integer, Visit> entry : map.entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = (entry.getValue().getPatient() == null) ? "" :
                    entry.getValue().getPatient().getLastName() + " " + entry.getValue().getPatient().getFirstName();
            data[i][2] = entry.getValue().getStartDate();
            data[i][3] = entry.getValue().getEndDate();
            i++;
        }

        return data;
    }
}
